package it.founderconnessi.banconnesso.api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import it.founderconnessi.banconnesso.BanConnesso;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {

    private final static String ENDPOINT = "http://localhost:3000/ban/users";

    public static JsonObject fetchUsers(ApiRequestBody requestBody) {
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
            BanConnesso.getInstance().getLogger().warning(exception.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
