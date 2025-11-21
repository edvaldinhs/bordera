package com.example.bordera.network;

import android.util.Log;
import org.json.JSONObject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkSender {

    private static final String TAG = "NetworkSender";

    private static final String API_BASE_URL = "https://bordera-api.vercel.app";
    private static final String TRIGGER_ENDPOINT = "/api/trigger-game";

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public boolean sendGameTrigger(String gameId, String action) {
        try {
            JSONObject json = new JSONObject();
            json.put("app_id", "BORDERA_SENDER");
            json.put("game_id", gameId);
            json.put("action", action);

            String jsonPayload = json.toString();

            Log.d(TAG, "Request URL: " + API_BASE_URL + TRIGGER_ENDPOINT);

            Log.i(TAG, "Payload JSON being sent: " + jsonPayload);

            RequestBody body = RequestBody.create(jsonPayload, JSON);

            String url = API_BASE_URL + TRIGGER_ENDPOINT;
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {

                Log.i(TAG, "HTTP Response Code: " + response.code());

                if (!response.isSuccessful()) {
                    String responseBody = response.body() != null ? response.body().string() : "No body";
                    Log.e(TAG, "HTTP Failure Body: " + responseBody);
                }
                return response.isSuccessful();
            }

        } catch (Exception e) {
            Log.e(TAG, "Error sending trigger", e);
            return false;
        }
    }
}