package dev.shingi.services;

import java.util.*;

import dev.shingi.models.*;

public class AccountComparator {private CustomerList customers;
    private Map<Customer, Map<String, List<LedgerAccount>>> duplicateLedgerAccounts;
    private List<LedgerAccount> uniqueLedgerAccounts;
    private Map<String, List<LedgerAccount>> mismatchedLedgerAccounts;
    private List<LedgerAccount> uniformLedgerAccounts;

    public AccountComparator(CustomerList customers) {
        this.customers = customers;

        // Get all duplicate ledger accounts in the same customer for all customers
        this.duplicateLedgerAccounts = customers.identifyDuplicateLedgerAccounts();
        
        // Get all ledger accounts, including setting the list of customers that has each ledger account
        List<LedgerAccount> allLedgerAccounts = getAllLedgerAccounts();

        // Identify all ledger account with only one customer. Remove these from allLedgerAccounts and add it to uniqueLedgerAccounts
        this.uniqueLedgerAccounts = identifyUniqueLedgerAccounts(allLedgerAccounts);

        // Identify all ledger accounts with the same description but different number. Remove these from allLedgerAccounts and add them to mismatchedLedgerAccounts
        this.mismatchedLedgerAccounts = identifyMismatchedLedgerAccounts(allLedgerAccounts);

        // The remaining accounts in allLedgerAccounts are at least relatively uniform, having at least two associated customers
        this.uniformLedgerAccounts = allLedgerAccounts;

        computeUniformityPercentages(uniformLedgerAccounts);

        // identifyUniformLedgerAccounts(allLedgerAccounts);
    }

    private List<LedgerAccount> getAllLedgerAccounts() {
        List<LedgerAccount> allLedgerAccounts = new ArrayList<>();

        try { // Some customers do not have ledger accounts, so this will throw a NullPointerException
            for (Customer customer : customers.getCustomers()) {
                // For each customer, loop through all their ledger accounts
                for (LedgerAccount account : customer.getLedgerAccounts()) {
                    if (!allLedgerAccounts.contains(account)) {
                        allLedgerAccounts.add(account);
                    }
                    allLedgerAccounts.get(allLedgerAccounts.indexOf(account)).getCustomers().add(customer);
                }
            }
        } catch (Exception e) {

        }
        
        return allLedgerAccounts;
    }

    public List<LedgerAccount> identifyUniqueLedgerAccounts(List<LedgerAccount> allLedgerAccounts) {
        List<LedgerAccount> uniqueLedgerAccounts = new ArrayList<>();
        for (LedgerAccount ledgerAccount : allLedgerAccounts) {
            if (ledgerAccount.getCustomers() != null && ledgerAccount.getCustomers().size() == 1) {
                uniqueLedgerAccounts.add(ledgerAccount);
                allLedgerAccounts.remove(ledgerAccount);
            }
        }

        return uniqueLedgerAccounts;
    }

    private Map<String, List<LedgerAccount>> identifyMismatchedLedgerAccounts(List<LedgerAccount> allLedgerAccounts) {
        Map<String, List<LedgerAccount>> mismatchedLedgerAccounts = new HashMap<>();
    
        // Create a map with LedgerAccount descriptions and corresponding LedgerAccounts
        Map<String, List<LedgerAccount>> accountMap = new HashMap<>();
        for (LedgerAccount account : allLedgerAccounts) {
            accountMap.computeIfAbsent(account.getOmschrijving(), k -> new ArrayList<>()).add(account);
        }
    
        // Add only those descriptions to mismatchedLedgerAccounts that have more than one LedgerAccount
        for (Map.Entry<String, List<LedgerAccount>> entry : accountMap.entrySet()) {
            if (entry.getValue().size() > 1) { // More than one account with the same description
                mismatchedLedgerAccounts.put(entry.getKey(), entry.getValue());
                for (LedgerAccount account : entry.getValue()) {
                    allLedgerAccounts.remove(account); // Remove all mismatched instances from allLedgerAccounts list
                }
            }
        }
    
        return mismatchedLedgerAccounts;
    }

    // public void identifyUniformLedgerAccounts(List<LedgerAccount> allLedgerAccounts) {
    //     this.uniformLedgerAccounts = new ArrayList<>();

    //     for (LedgerAccount ledgerAccount : allLedgerAccounts) {
    //         if (ledgerAccount.getCustomers().size() >= customers.getCustomers().size()*uniformityStandard) { // Ledger account matches or exceeds the uniformity standard
    //             this.uniformLedgerAccounts.add(ledgerAccount);
    //         }
    //     }
    // }

    private void computeUniformityPercentages(List<LedgerAccount> ledgerAccounts) {
        for (LedgerAccount ledgerAccount : ledgerAccounts) {
            if (ledgerAccount.getCustomers() != null) {
                ledgerAccount.setUniformityPercentage(ledgerAccount.getCustomers().size() / customers.getCustomers().size() * 100);
            }
        }
    }

    public Map<Customer, Map<String, List<LedgerAccount>>> getDuplicateLedgerAccounts() {
        return this.duplicateLedgerAccounts;
    }

    public void setDuplicateLedgerAccounts(Map<Customer, Map<String, List<LedgerAccount>>> duplicateLedgerAccounts) {
        this.duplicateLedgerAccounts = duplicateLedgerAccounts;
    }

    public List<LedgerAccount> getUniqueLedgerAccounts() {
        return uniqueLedgerAccounts;
    }

    public void setUniqueLedgerAccounts(List<LedgerAccount> uniqueLedgerAccounts) {
        this.uniqueLedgerAccounts = uniqueLedgerAccounts;
    }

    public Map<String, List<LedgerAccount>> getMismatchedLedgerAccounts() {
        return mismatchedLedgerAccounts;
    }

    public void setMismatchedLedgerAccounts(Map<String, List<LedgerAccount>> mismatchedLedgerAccounts) {
        this.mismatchedLedgerAccounts = mismatchedLedgerAccounts;
    }

    public List<LedgerAccount> getUniformLedgerAccounts() {
        return uniformLedgerAccounts;
    }

    public void setUniformLedgerAccounts(List<LedgerAccount> uniformLedgerAccounts) {
        this.uniformLedgerAccounts = uniformLedgerAccounts;
    }

    // public Map<String, Integer> identifyMostFrequentCodingPerAccount(Map<String, Map<Integer, List<Customer>>> mismatchedAccounts) {
    //     Map<String, Integer> mostFrequentCoding = new HashMap<>();

    //     // Iterate over each account name
    //     for (Map.Entry<String, Map<Integer, List<Customer>>> accountEntry : mismatchedAccounts.entrySet()) {
    //         String accountName = accountEntry.getKey();
    //         Map<Integer, List<Customer>> accountsByNumber = accountEntry.getValue();

    //         // Stream over the entry set of accounts by number to identify the most frequent coding
    //         Map.Entry<Integer, Integer> mostFrequentEntry = accountsByNumber.entrySet().stream()
    //                 .max(Comparator.comparingInt(entry -> entry.getValue().size()))
    //                 .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().size()))
    //                 .orElse(null);

    //         // If there's a result, put it in the map, otherwise handle the case where there's no clear "most frequent"
    //         if (mostFrequentEntry != null) {
    //             mostFrequentCoding.put(accountName, mostFrequentEntry.getKey());
    //         } else {
    //             // Handle the case where there is no most frequent coding (could be all unique)
    //             // For example, you might put a placeholder or decide on a policy for this case
    //         }
    //     }

    //     return mostFrequentCoding;
    // }

    // public Map<String, Integer> identifyMostFrequentCodingPerAccount(List<Customer> customers) {
    //     // This map will hold the account name as key, and a map of account number to their frequency count as value
    //     Map<String, Map<Integer, Integer>> codingFrequencyMap = new HashMap<>();

    //     // First, build the frequency map
    //     for (Customer customer : customers) {
    //         for (LedgerAccount account : customer.getLedgerAccounts()) {
    //             Map<Integer, Integer> frequencyMap = codingFrequencyMap.computeIfAbsent(account.getOmschrijving(), k -> new HashMap<>());
    //             frequencyMap.put(account.getNummer(), frequencyMap.getOrDefault(account.getNummer(), 0) + 1);
    //         }
    //     }

    //     // This map will hold the account name and the most frequently used account number
    //     Map<String, Integer> mostFrequentCoding = new HashMap<>();

    //     // Now, determine the most frequent account number for each account name
    //     for (Map.Entry<String, Map<Integer, Integer>> entry : codingFrequencyMap.entrySet()) {
    //         String accountName = entry.getKey();
    //         Map<Integer, Integer> frequencyMap = entry.getValue();

    //         // Determine the account number with the highest frequency for this account name
    //         int mostFrequentNumber = Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    //         mostFrequentCoding.put(accountName, mostFrequentNumber);
    //     }

    //     return mostFrequentCoding;
    // }

    
}