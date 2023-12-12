package dev.shingi;

import dev.shingi.models.*;
import dev.shingi.services.CustomerReaderExcel;
import dev.shingi.services.CustomerReaderSnelstart;
import java.io.*;

public class Main {

    private static CustomerList customerList;

    private static final String fileOutputPath = "C:\\Users\\damar\\OneDrive\\Documents\\Programmeren\\Visual Studio Code";
    
    public static void main(String[] args) throws IOException {
        /*
         * Uncomment the two lines below if reading customers through Snelstart API
         */
        CustomerReaderSnelstart snelstartCustomerReader = new CustomerReaderSnelstart(); 
        customerList = snelstartCustomerReader.readCustomers();

        /*
         * Uncomment the two lines below if reading customers from Excel file
         */
        // CustomerReaderExcel customerReaderExcel = new CustomerReaderExcel();
        // customerList = customerReaderExcel.readCustomers();

        /*
         * Call the account comparison functionality of the program
         */
        // Compare ledger accounts
        // AccountComparator accountComparator = new AccountComparator();

        // ExcelExporter excelExporter = new ExcelExporter();
        // excelExporter.exportComparisonsToExcel(
        //     accountComparator.findUniformAccounts(customerList.getCustomers()), 
        //     accountComparator.findMismatchedAccounts(customerList.getCustomers()), 
        //     accountComparator.findUniqueAccounts(customerList.getCustomers()), 
        //     accountComparator.findInternalDuplicates(customerList.getCustomers()), 
        //     fileOutputPath + "\\Comparison results.xlsx");
    }
}