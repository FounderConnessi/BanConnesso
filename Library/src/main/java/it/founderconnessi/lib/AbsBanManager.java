package it.founderconnessi.lib;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.founderconnessi.lib.api.Api;
import it.founderconnessi.lib.api.ApiFields;
import it.founderconnessi.lib.api.ApiFilters;
import it.founderconnessi.lib.api.ApiRequestBody;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public abstract class AbsBanManager {
    protected final BanList banList;
    protected final LoggerInt logger;
    protected final ConfigInt config;
    protected final String pluginFolder;
    protected String hashCode;
    protected ApiRequestBody requestBody;

    public AbsBanManager(LoggerInt logger, ConfigInt config, String pluginFolder) {
        this.logger = logger;
        this.config = config;
        this.pluginFolder = pluginFolder;
        banList = new BanList();
        updateRequestBody();
        refreshTask();
    }

    public void loadBannedUsers() {
        JsonObject rawData = Api.fetchUsers(requestBody, logger);
        if (Objects.isNull(rawData))
            rawData = loadJson();
        String newHashCode = rawData.get("hashCode").getAsString();
        if (Objects.nonNull(hashCode) && hashCode.equalsIgnoreCase(newHashCode))
            return;
        saveToJson(rawData);
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

    public void updateRequestBody() {
        ApiFields fields = new ApiFields(
                config.getBoolean("online-uuid"),
                !config.getBoolean("online-uuid"),
                false,
                true,
                false
        );
        ApiFilters filters = new ApiFilters(
                config
                        .getStringList("gravities")
                        .stream()
                        .map(gravity -> Gravity.valueOf(gravity.toUpperCase()))
                        .toArray(Gravity[]::new)
        );
        requestBody = new ApiRequestBody(fields, filters);
    }

    public boolean isBanned(String nickname, UUID uuid) {
        if (config.getBoolean("online-uuid"))
            return banList.contains(uuid);
        else
            return banList.contains(nickname.toLowerCase());
    }

    public BanUserFields getUser(String nickname, UUID uuid) {
        if (config.getBoolean("online-uuid"))
            return banList.getUser(uuid.toString());
        else
            return banList.getUser(nickname.toLowerCase());
    }

    private void saveToJson(JsonObject jsonObject) {
        FileWriter file;
        try {
            file = new FileWriter(pluginFolder + "/save.json");
            file.write(jsonObject.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JsonObject loadJson() {
        try {
            return (JsonObject) JsonParser.parseReader(
                    new FileReader(pluginFolder + "/save.json")
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void checkOnlinePlayers();

    protected abstract void refreshTask();
}
