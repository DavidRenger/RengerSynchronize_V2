package dev.shingi.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dev.shingi.models.Customer;
import dev.shingi.models.LedgerAccount;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelExporter {

    public void exportComparisonsToExcel(
        Map<LedgerAccount, List<Customer>> uniformAccounts,
        Map<String, Map<Integer, List<Customer>>> mismatchedAccounts,
        Map<Customer, List<LedgerAccount>> uniqueAccounts,
        Map<String, List<Customer>> comparisonResults,
        String filePath) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {
            // Create sheets
            Sheet uniformSheet = workbook.createSheet("Uniform Accounts");
            Sheet mismatchedSheet = workbook.createSheet("Mismatched Accounts");
            Sheet uniqueSheet = workbook.createSheet("Unique Accounts");
            Sheet resultsSheet = workbook.createSheet("Comparison Results");

            // Populate the Uniform Accounts sheet
            populateUniformSheet(uniformSheet, uniformAccounts);

            // Populate the Mismatched Accounts sheet
            populateMismatchedSheet(mismatchedSheet, mismatchedAccounts);

            // Populate the Unique Accounts sheet
            populateUniqueSheet(uniqueSheet, uniqueAccounts);

            // Populate the Comparison Results sheet
            populateResultsSheet(resultsSheet, comparisonResults);

            // Write the workbook to a file
            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        }
    }

    private void populateUniformSheet(Sheet sheet, Map<LedgerAccount, List<Customer>> uniformAccounts) {
        int rowNum = 0;
        for (Map.Entry<LedgerAccount, List<Customer>> entry : uniformAccounts.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey().getNummer());
            row.createCell(1).setCellValue(entry.getKey().getOmschrijving());
            row.createCell(2).setCellValue(customersToString(entry.getValue()));
        }
    }

    private void populateMismatchedSheet(Sheet sheet, Map<String, Map<Integer, List<Customer>>> mismatchedAccounts) {
        int rowNum = 0;
        for (Map.Entry<String, Map<Integer, List<Customer>>> entry : mismatchedAccounts.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey());

            Map<Integer, List<Customer>> subMap = entry.getValue();
            int cellNum = 1;
            for (Map.Entry<Integer, List<Customer>> subEntry : subMap.entrySet()) {
                row.createCell(cellNum++).setCellValue(subEntry.getKey());
                row.createCell(cellNum++).setCellValue(customersToString(subEntry.getValue()));
            }
        }
    }

    private void populateUniqueSheet(Sheet sheet, Map<Customer, List<LedgerAccount>> uniqueAccounts) {
        int rowNum = 0;
        for (Map.Entry<Customer, List<LedgerAccount>> entry : uniqueAccounts.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey().toString());
            for (LedgerAccount ledgerAccount : entry.getValue()) {
                row = sheet.createRow(rowNum++);
                row.createCell(1).setCellValue(ledgerAccount.getNummer());
                row.createCell(2).setCellValue(ledgerAccount.getOmschrijving());
            }
        }
    }

    private void populateResultsSheet(Sheet sheet, Map<String, List<Customer>> resultsAccounts) {
        int rowNum = 0;
        for (Map.Entry<String, List<Customer>> entry : resultsAccounts.entrySet()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(entry.getKey().toString());
            for (Customer customer : entry.getValue()) {
                row = sheet.createRow(rowNum++);
                row.createCell(1).setCellValue(customer.getName());
            }
        }
    }

    // Helper method to convert customer list to string
    private String customersToString(List<Customer> customers) {
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customers) {
            sb.append(customer.toString()).append("; ");
        }
        return sb.toString();
    }
}
