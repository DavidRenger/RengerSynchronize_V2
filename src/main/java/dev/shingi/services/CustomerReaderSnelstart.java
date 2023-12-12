package dev.shingi.services;

import java.io.FileNotFoundException;
import dev.shingi.models.Customer;
import dev.shingi.models.CustomerList;
import dev.shingi.utils.SnelstartAuthentication;
import dev.shingi.utils.SnelstartReader;
import dev.shingi.utils.SnelstartUtils;

public class CustomerReaderSnelstart extends AbstractReader {
    
    public CustomerList readCustomers() throws FileNotFoundException {

        CustomerList customerList = getCustomerList();

        int count = 0;

        // Read info for customers
        for (Customer customer : customerList.getCustomers()) {
            
            if (customer.getClientKey() != null) {
                // Get bearer token
                String bearerToken = SnelstartAuthentication.getBearerToken(customer.getClientKey());

                // set customer ledger accounts
                customer.setLedgerAccounts(SnelstartUtils.convertGrootboekenToLedgerAccounts(SnelstartReader.readGrootboeken(bearerToken)));

                System.out.println("Read " + ++count + " of " + customerList.getCustomers().size() + " customers.");
            } else {
                System.out.println("Skipped " + customer + " - has no key.");
            }
            
            System.out.println("Done reading customers " + customerList.getCustomers().size() + " of " + customerList.getCustomers().size() + ".");
        }

        return customerList;
    }
}
