package dev.shingi.services;

import java.util.*;

import dev.shingi.models.*;

public class AccountComparator {

    public Map<String, List<Customer>> compareAccounts(List<Customer> customers) {
        // Assuming findUniformAccounts returns Map<String, List<Customer>> instead
        Map<LedgerAccount, List<Customer>> uniformAccounts = findUniformAccounts(customers);
        Map<String, Map<Integer, List<Customer>>> mismatchedAccounts = findMismatchedAccounts(customers);
        Map<Customer, List<LedgerAccount>> uniqueAccounts = findUniqueAccounts(customers);

        // Logic to handle uniform accounts
        Map<String, List<Customer>> uniformResults = new HashMap<>();
        for (Map.Entry<LedgerAccount, List<Customer>> uniformEntry : uniformAccounts.entrySet()) {
            LedgerAccount account = uniformEntry.getKey();
            String uniqueKey = account.getOmschrijving() + "#" + account.getNummer();
            uniformResults.put(uniqueKey, uniformEntry.getValue());
        }

        // Logic to handle mismatched accounts
        Map<String, List<Customer>> mismatchedResults = new HashMap<>();
        for (Map.Entry<String, Map<Integer, List<Customer>>> mismatchedEntry : mismatchedAccounts.entrySet()) {
            String accountName = mismatchedEntry.getKey();
            for (Map.Entry<Integer, List<Customer>> entry : mismatchedEntry.getValue().entrySet()) {
                Integer accountNumber = entry.getKey();
                List<Customer> customersList = entry.getValue();
                String uniqueKey = accountName + "#" + accountNumber; // Create a unique key for each mismatched account
                mismatchedResults.put(uniqueKey, customersList);
            }
        }

        // Logic to handle unique accounts
        Map<String, List<Customer>> uniqueResults = new HashMap<>();
        for (Map.Entry<Customer, List<LedgerAccount>> uniqueEntry : uniqueAccounts.entrySet()) {
            Customer customer = uniqueEntry.getKey();
            for (LedgerAccount account : uniqueEntry.getValue()) {
                String uniqueKey = account.getOmschrijving() + "#" + account.getNummer(); // Create a unique key for each unique account
                uniqueResults.computeIfAbsent(uniqueKey, k -> new ArrayList<>()).add(customer);
            }
        }

        // Combine all the results into a single map
        Map<String, List<Customer>> comparisonResults = new HashMap<>();
        comparisonResults.putAll(uniformResults);
        comparisonResults.putAll(mismatchedResults);
        comparisonResults.putAll(uniqueResults);

        return comparisonResults;
    }

    public Map<LedgerAccount, List<Customer>> findUniformAccounts(List<Customer> customers) {
        Map<LedgerAccount, List<Customer>> uniformAccounts = new HashMap<>();
        Map<String, Integer> accountFrequency = new HashMap<>();

        // First pass: build a frequency map of ledger accounts across all customers
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                accountFrequency.put(account.getOmschrijving(), accountFrequency.getOrDefault(account.getOmschrijving(), 0) + 1);
                // System.out.println("Adding " + account.getOmschrijving() + " to accountfrequency list. Amount: " + accountFrequency.get(account.getOmschrijving()));
            }
        }

        // Second pass: identify uniform accounts - those that appear more than once with the same details
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                if (accountFrequency.get(account.getOmschrijving()) > 1) {
                    uniformAccounts.computeIfAbsent(account, k -> new ArrayList<>()).add(customer);
                    // System.out.println(account.getOmschrijving() + " is present for both customers.");
                }
            }
        }

        return uniformAccounts;
    }

    public Map<String, Map<Integer, List<Customer>>> findMismatchedAccounts(List<Customer> customers) {
        Map<String, Map<Integer, List<Customer>>> mismatchedAccounts = new HashMap<>();

        // Iterate over each customer
        for (Customer customer : customers) {
            // Then iterate over each ledger account of the customer
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                // If the account name is not in the map, initialize it
                mismatchedAccounts.computeIfAbsent(account.getOmschrijving(), k -> new HashMap<>());

                // Now, get the map of account numbers to customers for this account name
                Map<Integer, List<Customer>> accountsByNumber = mismatchedAccounts.get(account.getOmschrijving());

                // If the account number is not in the map, initialize the customer list
                accountsByNumber.computeIfAbsent(account.getNummer(), k -> new ArrayList<>());

                // Finally, add the customer to the list for this account number
                accountsByNumber.get(account.getNummer()).add(customer);
            }
        }

        // Now we need to remove entries where account numbers are not mismatched, i.e., where only one unique number exists for an account name
        mismatchedAccounts.entrySet().removeIf(entry -> entry.getValue().size() == 1);

        return mismatchedAccounts;
    }

    public Map<Customer, List<LedgerAccount>> findUniqueAccounts(List<Customer> customers) {
        Map<Customer, List<LedgerAccount>> uniqueAccounts = new HashMap<>();
        Map<String, Map<Integer, List<Customer>>> allAccounts = new HashMap<>();

        // Build a map of all accounts across all customers
        for (Customer customer : customers) {
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                allAccounts.computeIfAbsent(account.getOmschrijving(), k -> new HashMap<>())
                           .computeIfAbsent(account.getNummer(), k -> new ArrayList<>())
                           .add(customer);
            }
        }

        // Iterate over the map to identify unique accounts
        for (Map.Entry<String, Map<Integer, List<Customer>>> accountEntry : allAccounts.entrySet()) {
            for (Map.Entry<Integer, List<Customer>> numberEntry : accountEntry.getValue().entrySet()) {
                if (numberEntry.getValue().size() == 1) {
                    // This account number is unique to a single customer
                    Customer uniqueCustomer = numberEntry.getValue().get(0);
                    LedgerAccount uniqueLedgerAccount = new LedgerAccount(accountEntry.getKey(), numberEntry.getKey());
                    
                    // Add this unique ledger account to the map for the customer
                    uniqueAccounts.computeIfAbsent(uniqueCustomer, k -> new ArrayList<>())
                                  .add(uniqueLedgerAccount);
                }
            }
        }

        return uniqueAccounts;
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