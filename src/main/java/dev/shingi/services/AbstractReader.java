package dev.shingi.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import dev.shingi.models.Customer;
import dev.shingi.models.CustomerList;
import dev.shingi.models.LedgerAccount;

public abstract class AbstractReader {

    /**
     * @return a CustomerList object.
     * @throws FileNotFoundException
     */
    public abstract CustomerList readCustomers() throws FileNotFoundException;

    public void validateAccounts(List<LedgerAccount> accounts) {
        // Implementation for validation
    }

    private CustomerList readClientNamesAndKeys() throws FileNotFoundException {
        CustomerList customerList = new CustomerList();
        
        Scanner sc = new Scanner(new File("RengerConnect/Client keys.txt"));
        Scanner lineScanner;

        while (sc.hasNextLine()) {
            String customerName;
            String clientKey = null;
            lineScanner = new Scanner(sc.nextLine());
            lineScanner.useDelimiter(" = ");

            customerName = lineScanner.next();
            if (lineScanner.hasNext()) {
                clientKey = lineScanner.next();
            }

            Customer customer = new Customer(customerName, clientKey);
            System.out.println(customer);
            customerList.getCustomers().add(customer);
        }

        return customerList;
    }

    // Protected or public method to provide access to the CustomerList
    protected CustomerList getCustomerList() throws FileNotFoundException {
        return readClientNamesAndKeys();
    }
}