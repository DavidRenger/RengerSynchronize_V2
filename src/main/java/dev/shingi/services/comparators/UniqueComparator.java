package dev.shingi.services.comparators;

import java.util.*;

import dev.shingi.models.Customer;
import dev.shingi.models.LedgerAccount;

public class UniqueComparator {
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
}
