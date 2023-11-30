package dev.shingi.services;

import java.util.*;

import dev.shingi.models.*;

public class AccountComparator {

    public Map<LedgerAccount, List<Customer>> findUniformAccounts(List<Customer> customers) {
        Map<LedgerAccount, List<Customer>> uniformAccounts = new TreeMap<>();
        Map<String, Set<Customer>> accountFrequency = new HashMap<>();
        Map<String, LedgerAccount> accountMapping = new HashMap<>();
    
        // First, build a set of customers for each account description
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                Set<Customer> customerSet = accountFrequency.computeIfAbsent(account.getOmschrijving(), k -> new HashSet<>());
                customerSet.add(customer);
    
                // Keep a reference to one of the LedgerAccounts for each description
                if (!accountMapping.containsKey(account.getOmschrijving())) {
                    accountMapping.put(account.getOmschrijving(), account);
                }
            }
        }
    
        // Now, add accounts to uniformAccounts only if they appear in more than one customer
        for (Map.Entry<String, Set<Customer>> entry : accountFrequency.entrySet()) {
            if (entry.getValue().size() > 1) { // Account description appears in multiple customers
                LedgerAccount account = accountMapping.get(entry.getKey());
                uniformAccounts.put(account, new ArrayList<>(entry.getValue()));
            }
        }
    
        return uniformAccounts;
    }
    

    public Map<LedgerAccount, Map<Integer, List<Customer>>> findMismatchedAccounts(List<Customer> customers) {
        Map<LedgerAccount, Map<Integer, Set<Customer>>> accountTracking = new HashMap<>();
    
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                // Using LedgerAccount with only description for the key
                LedgerAccount keyAccount = new LedgerAccount(account.getOmschrijving());
    
                accountTracking.computeIfAbsent(keyAccount, k -> new TreeMap<>())
                               .computeIfAbsent(account.getNummer(), k -> new HashSet<>())
                               .add(customer);
            }
        }
    
        // Filter to keep only those entries where an account description is associated with different numbers across customers
        Map<LedgerAccount, Map<Integer, List<Customer>>> mismatchedAccounts = new HashMap<>();
        for (Map.Entry<LedgerAccount, Map<Integer, Set<Customer>>> entry : accountTracking.entrySet()) {
            if (entry.getValue().size() > 1) {
                // Convert Set of Customers to List for final output
                Map<Integer, List<Customer>> mismatchMap = new TreeMap<>();
                for (Map.Entry<Integer, Set<Customer>> subEntry : entry.getValue().entrySet()) {
                    mismatchMap.put(subEntry.getKey(), new ArrayList<>(subEntry.getValue()));
                }
                mismatchedAccounts.put(entry.getKey(), mismatchMap);
            }
        }
    
        return mismatchedAccounts;
    }
    

    public Map<Customer, List<LedgerAccount>> findUniqueAccounts(List<Customer> customers) {
        // HashMap to store unique ledger accounts per customer.
        Map<Customer, List<LedgerAccount>> uniqueAccounts = new HashMap<>();
        // Map to store all ledger accounts across all customers.
        Map<String, Map<Integer, List<Customer>>> allAccounts = new HashMap<>();
    
        // Build a map of all ledger accounts across all customers.
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                // Create nested TreeMap entries for each account description and number.
                allAccounts.computeIfAbsent(account.getOmschrijving(), k -> new TreeMap<>())
                           .computeIfAbsent(account.getNummer(), k -> new ArrayList<>())
                           .add(customer);
            }
        }
    
        // Iterate over the map to identify unique accounts.
        for (Map.Entry<String, Map<Integer, List<Customer>>> accountEntry : allAccounts.entrySet()) {
            for (Map.Entry<Integer, List<Customer>> numberEntry : accountEntry.getValue().entrySet()) {
                // If this account number is unique to a single customer, identify it as a unique account.
                if (numberEntry.getValue().size() == 1) {
                    Customer uniqueCustomer = numberEntry.getValue().get(0);
                    LedgerAccount uniqueLedgerAccount = new LedgerAccount(accountEntry.getKey(), numberEntry.getKey());
                    
                    // Add this unique ledger account to the map for the customer.
                    uniqueAccounts.computeIfAbsent(uniqueCustomer, k -> new ArrayList<>())
                                  .add(uniqueLedgerAccount);
                }
            }
        }
    
        return uniqueAccounts;
    }
    
    public Map<Customer, Map<String, List<LedgerAccount>>> findInternalDuplicates(List<Customer> customers) {
        Map<Customer, Map<String, List<LedgerAccount>>> internalDuplicates = new HashMap<>();
    
        for (Customer customer : customers) {
            Map<String, List<LedgerAccount>> accountMap = new HashMap<>();
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                accountMap.computeIfAbsent(account.getOmschrijving(), k -> new ArrayList<>()).add(account);
            }
    
            // Check for duplicates in accountMap and add to internalDuplicates if any found
            for (Map.Entry<String, List<LedgerAccount>> entry : accountMap.entrySet()) {
                if (entry.getValue().size() > 1) { // More than one account with the same description
                    internalDuplicates.computeIfAbsent(customer, k -> new HashMap<>()).put(entry.getKey(), entry.getValue());
                }
            }
        }
    
        return internalDuplicates;
    }

    public Map<String, Integer> findMostFrequentCodingPerAccount(Map<String, Map<Integer, List<Customer>>> mismatchedAccounts) {
        Map<String, Integer> mostFrequentCoding = new HashMap<>();

        // Iterate over each account name
        for (Map.Entry<String, Map<Integer, List<Customer>>> accountEntry : mismatchedAccounts.entrySet()) {
            String accountName = accountEntry.getKey();
            Map<Integer, List<Customer>> accountsByNumber = accountEntry.getValue();

            // Stream over the entry set of accounts by number to find the most frequent coding
            Map.Entry<Integer, Integer> mostFrequentEntry = accountsByNumber.entrySet().stream()
                    .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                    .map(entry -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().size()))
                    .orElse(null);

            // If there's a result, put it in the map, otherwise handle the case where there's no clear "most frequent"
            if (mostFrequentEntry != null) {
                mostFrequentCoding.put(accountName, mostFrequentEntry.getKey());
            } else {
                // Handle the case where there is no most frequent coding (could be all unique)
                // For example, you might put a placeholder or decide on a policy for this case
            }
        }

        return mostFrequentCoding;
    }

    public Map<String, Integer> findMostFrequentCodingPerAccount(List<Customer> customers) {
        // This map will hold the account name as key, and a map of account number to their frequency count as value
        Map<String, Map<Integer, Integer>> codingFrequencyMap = new HashMap<>();

        // First, build the frequency map
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                Map<Integer, Integer> frequencyMap = codingFrequencyMap.computeIfAbsent(account.getOmschrijving(), k -> new HashMap<>());
                frequencyMap.put(account.getNummer(), frequencyMap.getOrDefault(account.getNummer(), 0) + 1);
            }
        }

        // This map will hold the account name and the most frequently used account number
        Map<String, Integer> mostFrequentCoding = new HashMap<>();

        // Now, determine the most frequent account number for each account name
        for (Map.Entry<String, Map<Integer, Integer>> entry : codingFrequencyMap.entrySet()) {
            String accountName = entry.getKey();
            Map<Integer, Integer> frequencyMap = entry.getValue();

            // Determine the account number with the highest frequency for this account name
            int mostFrequentNumber = Collections.max(frequencyMap.entrySet(), Map.Entry.comparingByValue()).getKey();
            mostFrequentCoding.put(accountName, mostFrequentNumber);
        }

        return mostFrequentCoding;
    }
}