package it.founderconnessi.lib;

import java.util.HashMap;
import java.util.UUID;

public class BanList {

    private final HashMap<String, BanUserFields> users;

    public BanList() {
        users = new HashMap<>();
    }

    public void addUser(String nickname, String startDate, String reason, Gravity gravity) {
        if (!contains(nickname))
            users.put(nickname.toLowerCase(), new BanUserFields(startDate, reason, gravity));
    }

    public void addUser(UUID uuid, String startDate, String reason, Gravity gravity) {
        addUser(uuid.toString(), startDate, reason, gravity);
    }

    public void removeUser(String nickname) {
        users.remove(nickname.toLowerCase());
    }

    public void removeUser(UUID uuid) {
        removeUser(uuid.toString());
    }

    public BanUserFields getUser(String nickname) {
        return users.get(nickname.toLowerCase());
    }

    public BanUserFields getUser(UUID uuid) {
        return users.get(uuid.toString());
    }

    public boolean contains(String nickname) {
        return users.containsKey(nickname.toLowerCase());
    }

    public boolean contains(UUID uuid) {
        return contains(uuid.toString());
    }

    public void clear(){
        users.clear();
    }
}
