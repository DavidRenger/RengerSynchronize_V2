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

        int customerCount = 0;

        // Read info for customers
        for (Customer customer : customerList.getCustomers()) {
            customerCount++;
            
            if (customer.getClientKey() != null) {
                // Get bearer token
                String bearerToken = SnelstartAuthentication.getBearerToken(customer.getClientKey());

                // set customer ledger accounts
                customer.setLedgerAccounts(SnelstartUtils.convertGrootboekenToLedgerAccounts(SnelstartReader.readGrootboeken(bearerToken)));

                // read customer info
                // SnelstartReader.readCompanyInfo(bearerToken);

                // read ovk memo's
                // if (customer.getName().equals("Renger Finance")) {
                //     SnelstartReader.readRelatieInfo(bearerToken);
                // }

            } else {
                System.out.println("Skipped " + customer + " - has no key.");
            }

            System.out.println("Read " + customerCount + " of " + customerList.getCustomers().size() + " customers.");
            
        }

        System.out.println("Finished reading " + customerCount + " of " + customerList.getCustomers().size() + " had keys to read ledger accounts.");
        return customerList;
    }
}
