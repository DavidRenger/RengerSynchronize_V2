package dev.shingi;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import dev.shingi.endpoints.models.*;
import dev.shingi.endpoints.regels.*;
import dev.shingi.models.*;
import dev.shingi.services.AccountComparator;
import dev.shingi.utils.ExcelExporter;
import dev.shingi.utils.ExcelUtils;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Main {
    
    private static final Gson gson = new Gson();
    private static final Main test = new Main();

    private static String bearerToken;
    private static long tokenExpirationTime = 0; // Timestamp of when the token expires

    private static final String SUBSCRIPTION_KEY = "328669a875374b17925cbe5e726ef89e";

    // client keys
    private static final String CREATEYOUROWNROAD = "UGdTdExGdllpUlRnY2FrcUllZ0NaQ3ZyWE03cVJGREtPZnB0LzhFclFwSDkzdnplUkpWK2g3dm8wUlRiMFFHa1ZoYXJRaXV0enNqNVBVbDFEampUK3RNQzZ1WUVkNnZGSUlndTdYVGY5V3hqMnhjQU1IbzRySWpMRm5kbGR1MmUxdnVHcjd5MUsveVdndWsrNjRJck9GU09QQ3NGSVByaEh0QVM5b0h6dkwrc3RFeUtGbGJac29oQ0tYR3lmSUhTRG45YzNvY2x5eWdPWDNvK1lIWDEwNi9nSFdTNUJCclJnTEtCekJNb0FlRE5jSG5NOTVtYUE0Qi9QRDhmc2JWRTpsSWhMaktzT1RqaWQzdXU2YUw4WVVlOUMwUzU2QVZ0eURFQkh1LzhpOFhqcVMvOTJqV2pEamRMSDBYTXZWc0IyUkNBc1k2ZEdsMUQ0aGxRN29zMmN5N3c3dG5xZTY0Y3lRQmpLdm90a1ZMOHhNVEpxcFozb3pldUUxVXpxcTI3SjMvcnVPc3VUcGJnajRYdllkMlJjVm9xeERWZHZVNVlabVNRUExKcXhQRWt4NE04SWxXcjdwb2Uxb0xwSld3Y0tudTNVUmEzRTRUOUdsWStSTjVuT1FoUUNRbjZJL0ErcEowNXdTUXFycGl0NWdETDljT1hBMXhlR0Yxckp6YXZj";
    private static final String JR_ELEKTRA_BV = "ZkFOY0FmUDNia2tnYzhMMTlXTGtQbzlqWmR0Z3J1a0JjbHp0ek5Nckh3dDk2V1dhR3diWkdFZXpuKzBITC9RbjRwV1Uxbi9NRFY4SnkxRzdyYWZlRlR3MWJobVFEdHBoSjdwWk9OSEdMZ2NEYkpONjMvNk1PMzhyRDFPcEtnNUdGOWVrTW9mSFhPUmp5VTUxVHBKZkYzbnVJNHpadHZMZDZVNmVzekRKNkVpclJaem5rZE9MY1RKbmpySUI1dU1pSkpkTFc3cnIrMlRnTlN3Lzl3SjhKbmwyVlBpTXBMSklxYmlWdjIwd3hVaXY3d25kTGF4L01LVFF0Q2F0Y1lnbDpvcXdYNUQ0K0ZPV0IxYmg5bFpPWkZydFhvVW1GVzQ3ZDlBNGNjandPMno0QzNISUpUbjJTclYrS1psamI5dzhCaC9DNVhzRkc5akNWMUZwYm42SEpEWVB0aDBHTzdWNjdtY0tNeGhabEpkek1zM0pUQVJ0WXNlcUI3OStCU1k5QW9nQURWOEEwSUlrZy80M2IzSEhoN0lnNU9ERXpyQ2VQRHllMjI0b2RvN2g5aU9hWm5iSlE4SHdHV0grNm1xd1I4Rk52VU4xZzRxMURMbVpUNDhodVlBdXMyTGNpY0l6REdUTzEvb0NlQWdGLzJ5VlNMNi8zMzVlRG9CbkVYc0VJ";
    private static final String RENGER_FINANCE = "Ym9JNkRUUGRKZkJDVGovRVZva2VGd2pUQXhGK1RVdWJ1WTRvZjVGb3lLVFFhbG1LY0ZJaUE1KzVsVXl1VU0zWnBDdHlLNGs4QndrQ29xM1VMZ1djQ25WSU4wdzl6Wit1cnhtZmJNQll3SXh6Wk4vemx6U29weitNN1lmdUQvcnJoMXhvY1pacmdBVldrWk9lWkZwUDhzRVcxbnhHU0N5dHpYOFd3bXRMaHZDaFEwa1ZRaWhKUWkxTEpOUlliUGh1a1NPcGdxb1VyUG5wNjZBY2dVU1FTSWtXNmVpVzJQbGxETTR3Y3hFdllKUVJrQnQzdTFLQ1hWM1pLWkNSYVplYzpMMXJqMlR1L2p1azBFeExudXJvak9GUzNlTWJnZThzb3RNKzhEOThKbzBVblFjVlAzRklWTTlueXJ6VTg4ZzdVVFNyQ1pzRlE5Z3l5RU5vbHdqemRQUDFFdFJEUXJHVlVOMW9zYU52dEVrbGRRL2kzYjJ3c0kwTGlNRmQyem83OTBDa0hTNXJkM1BTVzJaS1lJRGIyMW1RZ3ZFRHc5REsxZGpZN2Z3RUYzUWE3MVBrc050eDd6SEs5cVpKVDRzQzJUZTdLeFlDSldIeG8rQzRhVk5SN0YrOUVvdjlCV2RybjR4L1k2eGNhUHlWQTdIZFpFbHZ4OE8wTGhESVhxYlBL";
    // private static final String CLIENT_KEY = JR_ELEKTRA_BV;

    private static final String fileOutputPath = "C:\\Users\\damar\\OneDrive\\Documents\\Programmeren\\Visual Studio Code";
    
    public static void main(String[] args) throws IOException {
        bearerToken = getBearerToken(CREATEYOUROWNROAD);
        // Print methods
        // printInkoopFacturen();
        // printKolommenBalans("2023-01-01", "2023-12-31");
        // printGrootboeken();
        // printCompanyInfo();

        // Get memos
        // List<Relatie> relaties = printRelatieInfo();
        // ExcelUtils.writeRelatieMemosToExcel(relaties);

        // Compare ledger accounts
        Customer c1 = new Customer("Createyourownroad", convertGrootboekenToLedgerAccounts(printGrootboeken()));
        bearerToken = getBearerToken(JR_ELEKTRA_BV);
        Customer c2 = new Customer("JR Elektra B.V.", convertGrootboekenToLedgerAccounts(printGrootboeken()));

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);

        AccountComparator accountComparator = new AccountComparator();

        ExcelExporter excelExporter = new ExcelExporter();
        excelExporter.exportComparisonsToExcel(accountComparator.findUniformAccounts(customers), accountComparator.findMismatchedAccounts(customers), accountComparator.findUniqueAccounts(customers), accountComparator.compareAccounts(customers), fileOutputPath + "\\Comparison results.xlsx");
    }

    public static List<LedgerAccount> convertGrootboekenToLedgerAccounts(List<Grootboek> grootboeken) {
        List<LedgerAccount> ledgerAccounts = new ArrayList<>();

        for (Grootboek grootboek : grootboeken) {
            ledgerAccounts.add(new LedgerAccount(grootboek.getOmschrijving(), grootboek.getNummer()));
        }

        return ledgerAccounts;
    }

    private static void printInkoopFacturen() {
        // Fetch logic
        List<Inkoopfactuur> inkoopfacturen = test.getModelHttpRequest(bearerToken, Inkoopfactuur.class, "https://b2bapi.snelstart.nl/v2/inkoopfacturen");

        // Printing logic
        System.out.println("\nOpenstaande inkoopfacturen:");
        for (Inkoopfactuur inkoopfactuur : inkoopfacturen) {
            if (inkoopfactuur.getOpenstaandSaldo() > 0 || inkoopfactuur.getOpenstaandSaldo() < 0) { 
                System.out.println(inkoopfactuur);
            }
        }
        System.out.println();
    }

    private static void printKolommenBalans(String startDate, String endDate) {
        // Fetch logic
        List<KolommenBalansRegel> kolommenBalansList = test.getModelHttpRequest(bearerToken, KolommenBalansRegel.class, "https://b2bapi.snelstart.nl/v2/rapportages/kolommenbalans?start=" + startDate + "&end=" + endDate);

        // Sort list
        Collections.sort(kolommenBalansList);

        // Printing logic
        System.out.println("\nKolommenbalans:");
        for (KolommenBalansRegel regel : kolommenBalansList) {
            System.out.println(regel + "\n");
        }
        System.out.println("Debet Winst&Verlies, Credit Winst&Verlies, Debet Balans, Credit Balans\n");
    }

    private static List<Grootboek> printGrootboeken() {
        // Fetch logic
        List<Grootboek> grootboeken = test.getModelHttpRequest(bearerToken, Grootboek.class, "https://b2bapi.snelstart.nl/v2/grootboeken");

        // Sort list
        Collections.sort(grootboeken);

        // Printing logic
        // System.out.println("\nGrootboeken:");
        // for (Grootboek grootboek : grootboeken) {
        //     System.out.println(grootboek);
        // }

        return grootboeken;
    }

    private static void printCompanyInfo() {
        // Fetch logic
        Administratie companyInfo = (Administratie) test.getObjectHttpRequest(bearerToken, Administratie.class, "https://b2bapi.snelstart.nl/v2/companyInfo");

        // Printing logic
        try {
            System.out.println(companyInfo.getVrijeTekst1());
            System.out.println(companyInfo.getVrijeTekst2());
            System.out.println(companyInfo.getVrijeTekst3());
            System.out.println(companyInfo.getVrijeTekst4());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Relatie> printRelatieInfo() {
        // Fetch logic
        List<Relatie> relaties = test.getModelHttpRequest(bearerToken, Relatie.class, "https://b2bapi.snelstart.nl/v2/relaties");

        // Printing logic
        try {
            for (Relatie relatie : relaties) {
                if (relatie.getRelatiesoort().get(0).equals("Klant")) {
                    System.out.println("\nNaam klant: " + relatie.getNaam());
                    if (relatie.getMemo() != null) {
                        if (!relatie.getMemo().isEmpty()) {
                            System.out.println("Memo\n" + relatie.getMemo() + "\n");
                        } else {
                            System.out.println("Heeft geen memo.\n");
                        }
                    } else {
                        System.out.println("Heeft geen memo.\n");
                    }
                }
            }
            return relaties;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private <T> List<T> getModelHttpRequest(String bearerToken, Class<T> clazz, String uri) {
        // Refresh the token if it's expired
        if (System.currentTimeMillis() > tokenExpirationTime) {
            bearerToken = getBearerToken("your_client_key");
        }

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

    private <T> Object getObjectHttpRequest(String bearerToken, Class<T> clazz, String uri) {
        // Refresh the token if it's expired
        if (System.currentTimeMillis() > tokenExpirationTime) {
            bearerToken = getBearerToken("your_client_key");
        }

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

    private static String getBearerToken(String clientKey) {
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

    public static <T> List<T> parseListJsonResponse(String jsonResponse, Class<T> clazz) {
        Type listType = TypeToken.getParameterized(List.class, clazz).getType();
        return gson.fromJson(jsonResponse, listType);
    }

    public static <T> Object parseObjectJsonResponse(String jsonResponse, Class<T> clazz) {
        return gson.fromJson(jsonResponse, clazz);
    }
}