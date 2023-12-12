package dev.shingi.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dev.shingi.endpoints.Models.Grootboek;
import dev.shingi.models.LedgerAccount;

public class SnelstartUtils {
    private static final String SUBSCRIPTION_KEY = "328669a875374b17925cbe5e726ef89e";
    private static final Gson gson = new Gson();
    
    public static <T> List<T> getModelHttpRequest(String bearerToken, Class<T> clazz, String uri) {
        // Refresh the token if it's expired
        // if (System.currentTimeMillis() > SnelstartAuthentication.getTokenExpirationTime()) {
        //     bearerToken = SnelstartAuthentication.getBearerToken("your_client_key");
        // }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Authorization", "Bearer " + bearerToken);
            httpGet.setHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY);

            org.apache.http.HttpResponse response = httpClient.execute(httpGet);

            // Check for the HTTP status code and handle it appropriately
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = EntityUtils.toString(response.getEntity());
                List<T> modelList = parseListJsonResponse(jsonResponse, clazz);
                return modelList;
            } else {
                // Handle other status codes appropriately
                System.out.println("Error: Received status code " + statusCode);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Object getObjectHttpRequest(String bearerToken, Class<T> clazz, String uri) {
        // Refresh the token if it's expired
        // if (System.currentTimeMillis() > SnelstartAuthentication.getTokenExpirationTime()) {
        //     bearerToken = SnelstartAuthentication.getBearerToken("your_client_key");
        // }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(uri);
            httpGet.setHeader("Authorization", "Bearer " + bearerToken);
            httpGet.setHeader("Ocp-Apim-Subscription-Key", SUBSCRIPTION_KEY);

            org.apache.http.HttpResponse response = httpClient.execute(httpGet);

            // Check for the HTTP status code and handle it appropriately
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getStatusLine().getStatusCode() == 200) {
                // Parse the JSON response
                String jsonResponse = EntityUtils.toString(response.getEntity());
                Object modelObject = parseObjectJsonResponse(jsonResponse, clazz);
                return modelObject;
            } else {
                // Handle other status codes appropriately
                System.out.println("Error: Received status code " + statusCode);
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static <T> List<T> parseListJsonResponse(String jsonResponse, Class<T> clazz) {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public static <T> Object parseObjectJsonResponse(String jsonResponse, Class<T> clazz) {
        return gson.fromJson(jsonResponse, clazz);
    }

    public static List<LedgerAccount> convertGrootboekenToLedgerAccounts(List<Grootboek> grootboeken) {
        List<LedgerAccount> ledgerAccounts = new ArrayList<>();

        for (Grootboek grootboek : grootboeken) {
            ledgerAccounts.add(new LedgerAccount(grootboek.getOmschrijving(), grootboek.getNummer()));
        }

        return ledgerAccounts;
    }
}
