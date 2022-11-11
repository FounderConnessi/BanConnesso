package it.founderconnessi.lib.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.founderconnessi.lib.LoggerInt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Classe utile a interfacciarsi con le API esterne di FounderConnessi.<p>
 * Quest'ultima permette di recuperare gli utenti banditi dalla lista.
 */
public class Api {

    /**
     * Endpoint a cui effettuare le richieste API.
     */
    private final static String ENDPOINT = "https://api.founderconnessi.it/ban/users";

    /**
     * Metodo utile per prelevare la lista di utenti banditi, dato il corpo della richiesta.
     * @param requestBody corpo della richiesta.
     * @param logger logger del plugin.
     * @return oggetto di tipo {@link JsonObject}.
     */
    public static JsonObject fetchUsers(ApiRequestBody requestBody, LoggerInt logger) {
        String bodyData = requestBody.toString();
        try {
            URL url = new URL(ENDPOINT);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Content-Length", String.valueOf(bodyData.length()));
            conn.setUseCaches(false);

            try (DataOutputStream dos = new DataOutputStream(conn.getOutputStream())) {
                dos.writeBytes(bodyData);
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String line = br.readLine();
                return JsonParser.parseString(line).getAsJsonObject();
            }
        } catch (IOException exception) {
            logger.warning(exception.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
