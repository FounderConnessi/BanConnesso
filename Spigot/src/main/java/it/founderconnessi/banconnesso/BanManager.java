package it.founderconnessi.banconnesso;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.founderconnessi.banconnesso.api.Api;
import it.founderconnessi.lib.BanList;
import it.founderconnessi.lib.BanUserFields;
import it.founderconnessi.lib.Gravity;
import it.founderconnessi.lib.api.ApiFields;
import it.founderconnessi.lib.api.ApiFilters;
import it.founderconnessi.lib.api.ApiRequestBody;
import org.bukkit.Bukkit;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class BanManager {
    private final BanList banList;
    private String hashCode;
    private ApiRequestBody requestBody;

    public BanManager() {
        banList = new BanList();
        updateRequestBody();
        refreshTask();
    }

    public void loadBannedUsers() {
        JsonObject rawData = Api.fetchUsers(requestBody);
        if (Objects.isNull(rawData)) {
            try {
                rawData = (JsonObject) new JsonParser().parse(
                        new FileReader(BanConnesso.getInstance().getDataFolder() + "/save.json")
                );
            } catch (FileNotFoundException e) {
                BanConnesso.getInstance().getLogger().warning(e.getMessage());
                return;
            }
        } else {
            saveToJson(rawData);
        }
        String newHashCode = rawData.get("hashCode").getAsString();
        if (Objects.nonNull(hashCode) && hashCode.equalsIgnoreCase(newHashCode))
            return;
        hashCode = newHashCode;
        banList.clear();
        JsonArray rawUsers = rawData.getAsJsonArray("users");
        String uniqueField = requestBody.getFields().isUuid() ? "uuid" : "nickname";
        for (JsonElement element : rawUsers) {
            JsonObject rawUser = element.getAsJsonObject();
            JsonElement startDate = rawUser.get("startDate");
            JsonElement reason = rawUser.get("reason");
            JsonElement gravity = rawUser.get("gravity");
            banList.addUser(
                    rawUser.get(uniqueField).getAsString(),
                    Objects.nonNull(startDate) ? startDate.getAsString() : null,
                    Objects.nonNull(reason) ? reason.getAsString() : null,
                    Objects.nonNull(gravity) ? Gravity.valueOf(gravity.getAsString()) : null
            );
        }
        checkOnlinePlayers();
    }

    public void checkOnlinePlayers() {
        BanConnesso.getInstance()
                .getServer()
                .getOnlinePlayers()
                .stream()
                .filter(player -> isBanned(
                        player.getName(),
                        player.getUniqueId())
                ).forEach(player -> {
                            BanUserFields user = BanConnesso.getInstance().getBanManager().getUser(
                                    player.getName(),
                                    player.getUniqueId()
                            );
                            player.kickPlayer(
                                    user.replacePlaceholders(
                                            BanConnesso
                                                    .getInstance()
                                                    .getConfig()
                                                    .getString("kick-message")
                                    )
                            );
                        }
                );
    }


    public void updateRequestBody() {
        ApiFields fields = new ApiFields(
                BanConnesso
                        .getInstance()
                        .getConfig()
                        .getBoolean("online-uuid"),
                !BanConnesso
                        .getInstance()
                        .getConfig()
                        .getBoolean("online-uuid"),
                false,
                true,
                false
        );
        ApiFilters filters = new ApiFilters(
                BanConnesso
                        .getInstance()
                        .getConfig()
                        .getStringList("gravities")
                        .stream()
                        .map(gravity -> Gravity.valueOf(gravity.toUpperCase()))
                        .toArray(Gravity[]::new)
        );
        requestBody = new ApiRequestBody(fields, filters);
    }

    public boolean isBanned(String nickname, UUID uuid) {
        if (BanConnesso.getInstance().getConfig().getBoolean("online-uuid"))
            return banList.contains(uuid);
        else
            return banList.contains(nickname.toLowerCase());
    }

    public BanUserFields getUser(String nickname, UUID uuid) {
        if (BanConnesso.getInstance().getConfig().getBoolean("online-uuid"))
            return banList.getUser(uuid.toString());
        else
            return banList.getUser(nickname.toLowerCase());
    }

    private void refreshTask() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(
                BanConnesso.getInstance(),
                this::loadBannedUsers,
                1,
                BanConnesso.getInstance().getConfig().getInt("refresh-rate") * 1200L
        );
    }

    private void saveToJson(JsonObject jsonObject) {
        FileWriter file;
        try {
            file = new FileWriter(BanConnesso.getInstance().getDataFolder() + "/save.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
