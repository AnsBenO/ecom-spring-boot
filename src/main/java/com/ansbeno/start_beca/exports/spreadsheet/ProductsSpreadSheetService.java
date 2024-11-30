package com.ansbeno.start_beca.exports.spreadsheet;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.ansbeno.start_beca.domain.product.ProductService;
import com.ansbeno.start_beca.dtos.ProductDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductsSpreadSheetService {

      private final ProductService productService;

      /**
       * Generates an Excel workbook for the products data.
       *
       * @return a populated Workbook containing the products data.
       */
      public Workbook generateProductsWorkbook() {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Products");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = { "Id", "Name", "Description", "Price", "Category" };
            for (int i = 0; i < headers.length; i++) {
                  Cell cell = headerRow.createCell(i);
                  cell.setCellValue(headers[i]);
            }

            // Fetch product data
            List<ProductDto> products = productService.findAllNoPagination();

            // Populate rows with product data
            for (int i = 0; i < products.size(); i++) {
                  ProductDto product = products.get(i);
                  Row row = sheet.createRow(i + 1);
                  row.createCell(0).setCellValue(product.getId());
                  row.createCell(1).setCellValue(product.getName());
                  row.createCell(2).setCellValue(product.getDescription());
                  row.createCell(3).setCellValue(product.getPrice());
                  row.createCell(4).setCellValue(product.getCategoryName());
            }

            // Adjust column widths
            for (int i = 0; i < headers.length; i++) {
                  sheet.autoSizeColumn(i);
            }

            return workbook;
      }
}
