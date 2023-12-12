package dev.shingi.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SnelstartAuthentication {
    private static long tokenExpirationTime = 0; // Timestamp of when the token expires
    
    public static String getBearerToken(String clientKey) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // Http request type and uri
            HttpPost post = new HttpPost("https://auth.snelstart.nl/b2b/token");

            // Request body
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("grant_type", "clientkey"));
            urlParameters.add(new BasicNameValuePair("clientkey", clientKey));

            // Add body to Http request
            post.setEntity(new UrlEncodedFormEntity(urlParameters));

            // Execute the request and save the response as json
            org.apache.http.HttpResponse response = httpClient.execute(post);
            String json = EntityUtils.toString(response.getEntity());
            
            // Parse the json respone and return it as a String
            JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
            String newToken = jsonObject.get("access_token").getAsString();
            int expiresIn = jsonObject.get("expires_in").getAsInt();

            // Set the token expiration time
            tokenExpirationTime = System.currentTimeMillis() + (expiresIn * 1000L) - (5 * 60 * 1000L); // Subtract 5 minutes to account for potential time drift

            return newToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public static void setTokenExpirationTime(long tokenExpirationTime) {
        SnelstartAuthentication.tokenExpirationTime = tokenExpirationTime;
    }
}
