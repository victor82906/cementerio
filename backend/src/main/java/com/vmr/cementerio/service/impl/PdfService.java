package com.vmr.cementerio.service.impl;

import org.thymeleaf.spring6.SpringTemplateEngine;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.thymeleaf.context.Context;
import java.io.ByteArrayOutputStream;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final SpringTemplateEngine templateEngine;

    public byte[] generarPdf(String templateName, Map<String, Object> datos) {
        Context context = new Context();
        context.setVariables(datos);

        String html = templateEngine.process(templateName, context);

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, "");
            builder.toStream(outputStream);
            builder.run();
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF", e);
        }
    }

}
