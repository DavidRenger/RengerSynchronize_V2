package dev.shingi;

import dev.shingi.models.*;
import dev.shingi.services.*;
import dev.shingi.utils.*;

import java.io.*;

public class Main {

    // IF YOU WANT ANY INFO IN PARTICULAR FROM AN ADMINISTRATION, CHANGE THE PRINT STATEMENTS IN THE UTIL CLASS: SnelstartReader
    // AS WELL AS THE SNELSTARTREADER METHOD THAT IS CALLED IN THE SERVICE CLASS: CustomerSnelstartReader

    // THE OPDRACHTOVEREENKOMST INFORMATION IS IN THE RENGERFINANCE ADIMINSTRATION, MEMO FIELDS OF ITS RELATIONS
    
    private static CustomerList customerList;

    private static final String fileOutputPath = "";
    
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
        // AccountComparator accountComparator = new AccountComparator(customerList);

        // ExcelExporter excelExporter = new ExcelExporter();
        // excelExporter.exportComparisonsToExcel(
        //     accountComparator.getDuplicateLedgerAccounts(), 
        //     accountComparator.getUniqueLedgerAccounts(), 
        //     accountComparator.getMismatchedLedgerAccounts(),
        //     accountComparator.getUniformLedgerAccounts(), 
        //     fileOutputPath + "\\Comparison results.xlsx");
    }
}