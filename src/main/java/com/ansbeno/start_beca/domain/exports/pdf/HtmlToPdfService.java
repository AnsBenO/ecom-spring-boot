package com.ansbeno.start_beca.domain.exports.pdf;

import java.util.Map;

public interface HtmlToPdfService {
      byte[] generatePdf(String template, Map<String, Object> data);
}
