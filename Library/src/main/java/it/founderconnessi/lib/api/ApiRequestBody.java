package it.founderconnessi.lib.api;

import org.jetbrains.annotations.NotNull;

/**
 * Classe concreta immutabile che rappresenta il corpo della richiesta API.<p>
 * La classe contiene {@link #fields}, ovvero i campi da mostrare e
 * {@link #filters}, cioè i filtri da applicare.
 */
public class ApiRequestBody {

    /**
     * L'oggetto che contiene i campi da mostrare.
     */
    private final ApiFields fields;

    /**
     * L'oggetto che contiene i filtri da applicare.
     */
    private final ApiFilters filters;

    /**
     * Costruisce un oggetto di tipo {@link ApiRequestBody}.
     * @param fields contiene i campi da mostrare.
     * @param filters contiene i filtri da applicare.
     */
    public ApiRequestBody(@NotNull ApiFields fields, @NotNull ApiFilters filters) {
        this.fields = fields;
        this.filters = filters;
    }

    /**
     * Metodo che restuisce i campi da mostrare.
     * @return campi da mostrare.
     */
    @NotNull
    public ApiFields getFields() {
        return fields;
    }

    /**
     * Metodo che restuisce i filtri applicati.
     * @return filtri applicati
     */
    @NotNull
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
