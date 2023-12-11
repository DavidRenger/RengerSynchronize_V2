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

import dev.shingi.endpoints.Models.*;
import dev.shingi.endpoints.Regels.*;
import dev.shingi.models.*;
import dev.shingi.services.AccountComparator;
import dev.shingi.utils.ExcelExporter;
import dev.shingi.utils.ExcelUtils;

import org.apache.http.NameValuePair;

import java.io.*;
import java.util.*;
import java.lang.reflect.Type;

public class Main {
    
    private static final Gson gson = new Gson();
    private static final Main test = new Main();

    private static String bearerToken;
    private static long tokenExpirationTime = 0; // Timestamp of when the token expires

    private static final String SUBSCRIPTION_KEY = "328669a875374b17925cbe5e726ef89e";

    private static CustomerList customerList;

    private static final String fileOutputPath = "C:\\Users\\damar\\OneDrive\\Documents\\Programmeren\\Visual Studio Code";

    // Print methods
    // printInkoopFacturen();
    // printKolommenBalans("2023-01-01", "2023-12-31");
    // printGrootboeken();
    // printCompanyInfo();

    // Get memos
    // List<Relatie> relaties = printRelatieInfo();
    // ExcelUtils.writeRelatieMemosToExcel(relaties);
    
    public static void main(String[] args) throws IOException {
        readCustomers();

        int count = 0;

        // Get ledger accounts for all customers with a client key
        for (Customer customer : customerList.getCustomers()) {
            if (customer.getClientKey() != null) {
                bearerToken = getBearerToken(customer.getClientKey());
                customer.setLedgerAccounts(convertGrootboekenToLedgerAccounts(printGrootboeken()));
                System.out.println("Read " + ++count + " of " + customerList.getCustomers().size() + " customers.");
            } else {
                System.out.println("Skipped " + customer + " - has no key.");
            }
            System.out.println("Done reading customers " + customerList.getCustomers().size() + " of " + customerList.getCustomers().size() + ".");
        }

        // Compare ledger accounts
        // AccountComparator accountComparator = new AccountComparator();

        // ExcelExporter excelExporter = new ExcelExporter();
        // excelExporter.exportComparisonsToExcel(
        //     accountComparator.findUniformAccounts(customerList.getCustomers()), 
        //     accountComparator.findMismatchedAccounts(customerList.getCustomers()), 
        //     accountComparator.findUniqueAccounts(customerList.getCustomers()), 
        //     accountComparator.findInternalDuplicates(customerList.getCustomers()), 
        //     fileOutputPath + "\\Comparison results.xlsx");
    }

    private static void readCustomers() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("RengerConnect/Client keys.txt"));
        Scanner lineScanner;

        customerList = new CustomerList();

        while (sc.hasNextLine()) {
            String customerName;
            String clientKey = null;
            lineScanner = new Scanner(sc.nextLine());
            lineScanner.useDelimiter(" = ");

            customerName = lineScanner.next();
            if (lineScanner.hasNext()) {
                clientKey = lineScanner.next();
            }

            Customer customer = new Customer(customerName, clientKey);
            System.out.println(customer);
            customerList.getCustomers().add(customer);
        }
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
        System.out.println("\nGrootboeken:");
        for (Grootboek grootboek : grootboeken) {
            System.out.println(grootboek);
        }

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