package dev.shingi.services;

import java.util.*;

import dev.shingi.models.*;

public class AccountComparator {
    private CustomerList customers;
    private Map<Customer, Map<String, List<LedgerAccount>>> duplicateLedgerAccounts;
    private List<LedgerAccount> uniqueLedgerAccounts;
    private Map<String, List<LedgerAccount>> mismatchedLedgerAccounts;
    private List<LedgerAccount> uniformLedgerAccounts;

    public AccountComparator(CustomerList customers) {
        System.out.println("Initializing AccountComparator");

        this.customers = customers;

        // Get all duplicate ledger accounts in the same customer for all customers
        this.duplicateLedgerAccounts = customers.identifyDuplicateLedgerAccounts();
        System.out.println("Duplicate ledger accounts identified");

        // Get all ledger accounts, including setting the list of customers that has each ledger account
        List<LedgerAccount> allLedgerAccounts = getAllLedgerAccounts();
        System.out.println("All ledger accounts retrieved");

        // Identify all ledger account with only one customer. Remove these from allLedgerAccounts and add it to uniqueLedgerAccounts
        this.uniqueLedgerAccounts = identifyUniqueLedgerAccounts(allLedgerAccounts);
        System.out.println("Unique ledger accounts identified");

        // Identify all ledger accounts with the same description but different number. Remove these from allLedgerAccounts and add them to mismatchedLedgerAccounts
        this.mismatchedLedgerAccounts = identifyMismatchedLedgerAccounts(allLedgerAccounts);
        System.out.println("Mismatched ledger accounts identified");

        // The remaining accounts in allLedgerAccounts are at least relatively uniform, having at least two associated customers
        this.uniformLedgerAccounts = allLedgerAccounts;
        System.out.println("Uniform ledger accounts identified");

        computeUniformityPercentages(uniformLedgerAccounts);
        System.out.println("Uniformity percentages computed");
    }

    private List<LedgerAccount> getAllLedgerAccounts() {
        System.out.println("Getting all ledger accounts");
        List<LedgerAccount> allLedgerAccounts = new ArrayList<>();
    
        for (Customer customer : customers.getCustomers()) {
            System.out.println("Processing customer: " + customer);
            if (customer.getLedgerAccounts() != null) {
                for (LedgerAccount account : customer.getLedgerAccounts()) {
                    if (account != null) {
                        System.out.println("Processing account: " + account);
                        if (!allLedgerAccounts.contains(account)) {
                            allLedgerAccounts.add(account);
                            System.out.println("Added new account: " + account);
                        }
                        allLedgerAccounts.get(allLedgerAccounts.indexOf(account)).getCustomers().add(customer);
                        System.out.println("Added customer to account: " + account);
                    }
                }
            }
        }
        
        return allLedgerAccounts;
    }    

    public List<LedgerAccount> identifyUniqueLedgerAccounts(List<LedgerAccount> allLedgerAccounts) {
        System.out.println("Identifying unique ledger accounts");
        List<LedgerAccount> uniqueLedgerAccounts = new ArrayList<>();
        Iterator<LedgerAccount> iterator = allLedgerAccounts.iterator();
        while (iterator.hasNext()) {
            LedgerAccount ledgerAccount = iterator.next();
            System.out.println("Checking account: " + ledgerAccount);
            if (ledgerAccount.getCustomers() != null && ledgerAccount.getCustomers().size() == 1) {
                uniqueLedgerAccounts.add(ledgerAccount);
                iterator.remove();
                System.out.println("Added to unique accounts and removed from allLedgerAccounts: " + ledgerAccount);
            }
        }
    
        return uniqueLedgerAccounts;
    }    

    private Map<String, List<LedgerAccount>> identifyMismatchedLedgerAccounts(List<LedgerAccount> allLedgerAccounts) {
        System.out.println("Identifying mismatched ledger accounts");
        Map<String, List<LedgerAccount>> mismatchedLedgerAccounts = new HashMap<>();
    
        Map<String, List<LedgerAccount>> accountMap = new HashMap<>();
        for (LedgerAccount account : allLedgerAccounts) {
            System.out.println("Processing account: " + account);
            accountMap.computeIfAbsent(account.getOmschrijving(), k -> new ArrayList<>()).add(account);
            System.out.println("Added to accountMap: " + account);
        }
    
        Set<LedgerAccount> accountsToRemove = new HashSet<>();
    
        for (Map.Entry<String, List<LedgerAccount>> entry : accountMap.entrySet()) {
            if (entry.getValue().size() > 1) {
                mismatchedLedgerAccounts.put(entry.getKey(), entry.getValue());
                accountsToRemove.addAll(entry.getValue());
                System.out.println("Added mismatched accounts to removal list: " + entry.getValue());
            }
        }
    
        Iterator<LedgerAccount> iterator = allLedgerAccounts.iterator();
        while (iterator.hasNext()) {
            LedgerAccount currentAccount = iterator.next();
            if (accountsToRemove.contains(currentAccount)) {
                iterator.remove();
                System.out.println("Removed mismatched account from allLedgerAccounts: " + currentAccount);
            }
        }
    
        return mismatchedLedgerAccounts;
    }    

    private void computeUniformityPercentages(List<LedgerAccount> ledgerAccounts) {
        System.out.println("Computing uniformity percentages");
        for (LedgerAccount ledgerAccount : ledgerAccounts) {
            if (ledgerAccount.getCustomers() != null) {
                double uniformityPercentage = ((double) ledgerAccount.getCustomers().size() / customers.getCustomers().size()) * 100;
                ledgerAccount.setUniformityPercentage(uniformityPercentage);
                System.out.println("Set uniformity percentage for account: " + ledgerAccount + " to " + uniformityPercentage + "%");
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
}