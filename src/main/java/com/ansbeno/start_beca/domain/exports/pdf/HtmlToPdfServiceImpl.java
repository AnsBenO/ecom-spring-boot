package com.ansbeno.start_beca.domain.exports.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HtmlToPdfServiceImpl implements HtmlToPdfService {
      private final TemplateEngine templateEngine;

      /**
       * Renders the Thymeleaf template with provided data as HTML.
       */
      private String renderHtml(String template, Map<String, Object> data) {
            Context context = new Context();
            context.setVariables(data);
            return templateEngine.process(template, context);
      }

      @Override
      public byte[] generatePdf(String template, Map<String, Object> data) {
            try {
                  String renderedHtml = renderHtml(template, data);

                  ITextRenderer renderer = new ITextRenderer();
                  renderer.setDocumentFromString(renderedHtml);
                  renderer.layout();

                  try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        renderer.createPDF(outputStream);
                        return outputStream.toByteArray();
                  }
            } catch (Exception e) {
                  throw new RuntimeException("Error generating PDF", e);
            }
      }
}