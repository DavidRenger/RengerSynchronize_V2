package dev.shingi.utils;

import java.util.Collections;
import java.util.List;

import dev.shingi.endpoints.Models.Administratie;
import dev.shingi.endpoints.Models.Grootboek;
import dev.shingi.endpoints.Models.Inkoopfactuur;
import dev.shingi.endpoints.Models.Relatie;
import dev.shingi.endpoints.Regels.KolommenBalansRegel;

public class SnelstartReader {
    public static List<Grootboek> readGrootboeken(String bearerToken) {
        // Fetch logic
        List<Grootboek> grootboeken = SnelstartUtils.getModelHttpRequest(bearerToken, Grootboek.class, "https://b2bapi.snelstart.nl/v2/grootboeken");

        // Sort list
        Collections.sort(grootboeken);

        // Printing logic
        System.out.println("\nGrootboeken:");
        for (Grootboek grootboek : grootboeken) {
            System.out.println(grootboek);
        }

        // Convert Grootboek objects to LedgerAccount objects via a util function in SnelstartUtils and return the list.
        return grootboeken;
    }
    
    public static void readInkoopFacturen(String bearerToken) {
        // Fetch logic
        List<Inkoopfactuur> inkoopfacturen = SnelstartUtils.getModelHttpRequest(bearerToken, Inkoopfactuur.class, "https://b2bapi.snelstart.nl/v2/inkoopfacturen");

        // Printing logic
        System.out.println("\nOpenstaande inkoopfacturen:");
        for (Inkoopfactuur inkoopfactuur : inkoopfacturen) {
            if (inkoopfactuur.getOpenstaandSaldo() > 0 || inkoopfactuur.getOpenstaandSaldo() < 0) { 
                System.out.println(inkoopfactuur);
            }
        }
        System.out.println();
    }

    public static void readKolommenBalans(String bearerToken, String startDate, String endDate) {
        // Fetch logic
        List<KolommenBalansRegel> kolommenBalansList = SnelstartUtils.getModelHttpRequest(bearerToken, KolommenBalansRegel.class, "https://b2bapi.snelstart.nl/v2/rapportages/kolommenbalans?start=" + startDate + "&end=" + endDate);

        // Sort list
        Collections.sort(kolommenBalansList);

        // Printing logic
        System.out.println("\nKolommenbalans:");
        for (KolommenBalansRegel regel : kolommenBalansList) {
            System.out.println(regel + "\n");
        }
        System.out.println("Debet Winst&Verlies, Credit Winst&Verlies, Debet Balans, Credit Balans\n");
    }

    public static void readCompanyInfo(String bearerToken) {
        // Fetch logic
        Administratie companyInfo = (Administratie) SnelstartUtils.getObjectHttpRequest(bearerToken, Administratie.class, "https://b2bapi.snelstart.nl/v2/companyInfo");

        // Printing logic
        try {
            System.out.println(companyInfo.getVrijeTekst1());
            System.out.println(companyInfo.getVrijeTekst2());
            System.out.println(companyInfo.getVrijeTekst3());
            System.out.println(companyInfo.getVrijeTekst4());

            // Print names of customers with KNAB account:
            if (companyInfo.getIban().contains("KNAB")) {
                System.out.println(companyInfo.getAdministratieNaam() + ": " + companyInfo.getIban());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Relatie> readRelatieInfo(String bearerToken) {
        // Fetch logic
        List<Relatie> relaties = SnelstartUtils.getModelHttpRequest(bearerToken, Relatie.class, "https://b2bapi.snelstart.nl/v2/relaties");

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
}
