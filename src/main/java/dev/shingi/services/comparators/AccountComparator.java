package dev.shingi.services.comparators;

import java.util.*;

import dev.shingi.models.*;

public class AccountComparator {
    private final double uniformityStandard = .8; // percentage of customers that should have this ledger account for it to be considered uniform

    private CustomerList customers;
    private List<LedgerAccount> allLedgerAccounts;
    private List<LedgerAccount> uniformLedgerAccounts;
    private List<LedgerAccount> mismatchedLedgerAccounts;
    private List<LedgerAccount> uniqueLedgerAccounts;

    public AccountComparator(CustomerList customers) {
        this.customers = customers;
        getAllLedgerAccounts();
        findUniformLedgerAccounts();

    }

    private void getAllLedgerAccounts() {
        allLedgerAccounts = new ArrayList<>();
        for (Customer customer : customers.getCustomers()) {
            // For each customer, loop through all their ledger accounts
            for (LedgerAccount account : customer.getLedgerAccounts()) {
                if (!this.allLedgerAccounts.contains(account)) {
                    this.allLedgerAccounts.add(account);
                }
                this.allLedgerAccounts.get(this.allLedgerAccounts.indexOf(account)).getCustomers().add(customer);
            }
        }
    }

    public void findUniformLedgerAccounts() {
        this.uniformLedgerAccounts = new ArrayList<>();

        for (LedgerAccount ledgerAccount : allLedgerAccounts) {
            if (ledgerAccount.getCustomers().size() >= customers.getCustomers().size()*uniformityStandard) { // Ledger account matches or exceeds the uniformity standard
                this.uniformLedgerAccounts.add(ledgerAccount);
            }
        }
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