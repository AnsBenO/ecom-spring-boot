package com.ansbeno.start_beca.web.controllers;

import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ansbeno.start_beca.exports.spreadsheet.ProductsSpreadSheetService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SpreadsheetExportController {

      private final ProductsSpreadSheetService productsSpreadSheetService;

      @GetMapping("/generate-products-table-spreadsheet")
      public void generateProductsTableSpreadsheet(HttpServletResponse response) {
            try (Workbook workbook = productsSpreadSheetService.generateProductsWorkbook();
                        OutputStream outputStream = response.getOutputStream()) {

                  // Set response headers for file download
                  response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                  response.setHeader("Content-Disposition", "attachment; filename=products.xlsx");

                  // Write workbook to the response output stream
                  workbook.write(outputStream);

            } catch (Exception e) {
                  throw new RuntimeException("Failed to generate Excel spreadsheet", e);
            }
      }
}
