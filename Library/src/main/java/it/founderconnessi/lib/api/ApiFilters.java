package it.founderconnessi.lib.api;

import it.founderconnessi.lib.Gravity;

/**
 * Classe concreta immutabile che descrive i filtri applicabili a una richiesta API.<p>
 * La classe ha lo scopo di filtrare i dati forniti dalla API, scartando gli utenti che non soddisfano il filtro.<p>
 * Ipotizzando che il vettore {@link #gravities} contenga {@code ['HIGH']}, gli utenti banditi con gravità
 * {@code 'MEDIUM'} e {@code 'LOW'} non saranno presi in considerazione.<p>
 * L'oggetto viene usato insieme a {@link ApiFields} per comporre il corpo della richiesta ({@link ApiRequestBody}).
 */
public class ApiFilters {

    /**
     * Vettore delle gravità da filtrare.<p>
     * Tutti gli utenti banditi con gravità non presente nel vettore non saranno presi in considerazione.
     */
    private final Gravity[] gravities;

    /**
     * Costruisce un oggetto di tipo {@link ApiFilters}.<p>
     * @param gravities Vettore delle gravità da filtrare.
     */
    public ApiFilters(Gravity[] gravities) {
        this.gravities = gravities.clone();
    }

    /**
     * Metodo che restituisce il vettore della gravità.
     * @return vettore della gravità.
     */
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
