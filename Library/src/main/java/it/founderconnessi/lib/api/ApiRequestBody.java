package it.founderconnessi.lib.api;

public class ApiRequestBody {
    private final ApiFields fields;
    private final ApiFilters filters;

    public ApiRequestBody(ApiFields fields, ApiFilters filters) {
        this.fields = fields;
        this.filters = filters;
    }

    public ApiFields getFields() {
        return fields;
    }

    public ApiFilters getFilters() {
        return filters;
    }

    @Override
    public String toString() {
        return "{" + fields +
                "," +
                filters +
                "}";
    }
}
