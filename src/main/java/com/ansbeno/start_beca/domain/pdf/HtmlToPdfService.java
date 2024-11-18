package com.ansbeno.start_beca.domain.pdf;

import java.util.Map;

public interface HtmlToPdfService {
      byte[] generatePdf(String template, Map<String, Object> data);
}
