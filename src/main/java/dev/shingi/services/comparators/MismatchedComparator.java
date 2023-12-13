package dev.shingi.services.comparators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import dev.shingi.models.Customer;
import dev.shingi.models.LedgerAccount;

public class MismatchedComparator {
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
}
