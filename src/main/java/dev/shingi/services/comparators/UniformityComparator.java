package dev.shingi.services.comparators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import dev.shingi.models.Customer;
import dev.shingi.models.LedgerAccount;

public class UniformityComparator {
    public Map<LedgerAccount, List<Customer>> findUniformAccounts(Map<LedgerAccount, Set<Customer>> allAccounts) {
        Map<LedgerAccount, List<Customer>> uniformAccounts = new TreeMap<>();

        for (Map.Entry<LedgerAccount, Set<Customer>> entry : allAccounts.entrySet()) {
            if (entry.getValue().size() > 1) { // Account description appears in multiple customers
                LedgerAccount account = entry.getKey();
                uniformAccounts.put(account, new ArrayList<>(entry.getValue()));
            }
        }
    
        return uniformAccounts;
    }
}
