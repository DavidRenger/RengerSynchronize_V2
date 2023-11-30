package dev.shingi.models;

import java.util.List;

public class Customer {
    
    private String name;
    private List<LedgerAccount> ledgerAccounts;

    public Customer(String name, List<LedgerAccount> ledgerAccounts) {
        this.name = name;
        this.ledgerAccounts = ledgerAccounts;
    }
    
    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<LedgerAccount> getLedgerAccounts() {
        return ledgerAccounts;
    }
    public void setLedgerAccount(List<LedgerAccount> ledgerAccounts) {
        this.ledgerAccounts = ledgerAccounts;
    }
}