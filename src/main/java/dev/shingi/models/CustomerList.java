package dev.shingi.models;

import java.util.*;

public class CustomerList {

    private List<Customer> customers;

    public CustomerList() {
        customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Identifies duplicate LedgerAccounts within each Customer's accounts based on description.
     * 
     * @param customers The CustomerList to be analyzed.
     * @return A Map<Customer, Map<String, List<LedgerAccount>>>, where each Customer is mapped to a Map of 
     *         LedgerAccount descriptions and Lists of LedgerAccounts with those descriptions.
     *         Only includes Customers and descriptions with duplicates.
     */
    public Map<Customer, Map<String, List<LedgerAccount>>> identifyDuplicateLedgerAccounts() {
        Map<Customer, Map<String, List<LedgerAccount>>> internalDuplicates = new HashMap<>();
    
        // Loop through all customers
        for (Customer customer : this.customers) {
            // First store all unique LedgerAccount descriptions in the accountMap key and all the LedgerAccounts with the same description the the value list
            Map<String, List<LedgerAccount>> accountMap = new HashMap<>();
            try { // Some customers do not have ledger accounts, so this will throw a NullPointerException
                for (LedgerAccount account : customer.getLedgerAccounts()) {
                    accountMap.computeIfAbsent(account.getOmschrijving(), k -> new ArrayList<>()).add(account);
                }
        
                // If the list contains more than one LedgerAccount, you have a duplicate. Add to internalDuplicates.
                for (Map.Entry<String, List<LedgerAccount>> entry : accountMap.entrySet()) {
                    if (entry.getValue().size() > 1) { // More than one account with the same description
                        internalDuplicates.computeIfAbsent(customer, k -> new HashMap<>()).put(entry.getKey(), entry.getValue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        return internalDuplicates;
    }
}