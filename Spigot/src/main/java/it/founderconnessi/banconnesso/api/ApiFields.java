package it.founderconnessi.banconnesso.api;

public class ApiFields {
    private final boolean uuid;
    private final boolean nickname;
    private final boolean startDate;
    private final boolean reason;
    private final boolean gravity;

    public ApiFields(boolean uuid, boolean nickname, boolean startDate, boolean reason, boolean gravity) {
        this.uuid = uuid;
        this.nickname = nickname;
        this.startDate = startDate;
        this.reason = reason;
        this.gravity = gravity;
    }

    public boolean isUuid() {
        return uuid;
    }

    public boolean isNickname() {
        return nickname;
    }

    public boolean isStartDate() {
        return startDate;
    }

    public boolean isReason() {
        return reason;
    }

    public boolean isGravity() {
        return gravity;
    }

    @Override
    public String toString() {
        return "\"fields\": {\"nickname\":" + nickname +
                ",\"uuid\":" +
                uuid +
                ", \"reason\":" +
                reason +
                ", \"startDate\":" +
                startDate +
                ",\"gravity\": " +
                reason +
                "}";
    }
}
