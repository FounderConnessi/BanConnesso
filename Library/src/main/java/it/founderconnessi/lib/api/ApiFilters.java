package it.founderconnessi.lib.api;

import it.founderconnessi.lib.Gravity;

public class ApiFilters {
    private final Gravity[] gravities;

    public ApiFilters(Gravity[] gravities) {
        this.gravities = gravities.clone();
    }

    public Gravity[] getGravities() {
        return gravities;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("\"filters\": {\"gravities\": [");
        for (Gravity gravity : gravities) {
            builder.append("\"")
                    .append(gravity)
                    .append("\",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]}");
        return builder.toString();
    }

}
