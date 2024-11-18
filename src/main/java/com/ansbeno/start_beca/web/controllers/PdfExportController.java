package com.ansbeno.start_beca.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ansbeno.start_beca.domain.exports.pdf.HtmlToPdfServiceImpl;
import com.ansbeno.start_beca.domain.product.ProductService;
import com.ansbeno.start_beca.dtos.ProductDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PdfExportController {

      private final HtmlToPdfServiceImpl htmlToPdfService;
      private final ProductService productService;

      @GetMapping("/generate-products-table-pdf")
      ResponseEntity<byte[]> generateProductsTablePdf() {
            List<ProductDto> products = productService.findAllNoPagination();
            Map<String, Object> data = new HashMap<>();

            data.put("products", products);

            // Path to the template (ensure it's in `src/main/resources/templates`)
            String templateName = "views/pdf/products";

            // Generate PDF as a byte array
            byte[] pdfBytes = htmlToPdfService.generatePdf(templateName, data);

            // Set response headers for file download
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "products-table.pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
      }

}
