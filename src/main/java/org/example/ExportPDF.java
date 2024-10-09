package org.example;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

public class ExportPDF {
    String fileName = "report.pdf";
    File file = new File(fileName);
    InputStream resource = Main.class.getResourceAsStream("/reports/jasperReport.jrxml");
    private JasperPrint jasperPrint;

    public void exportPdf() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JREmptyDataSource());
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            SimpleOutputStreamExporterOutput simpleOutputStreamExporterOutput = new SimpleOutputStreamExporterOutput(bos);
            exporter.setExporterOutput(simpleOutputStreamExporterOutput);
            SimplePdfExporterConfiguration simplePdfExporterConfiguration = new SimplePdfExporterConfiguration();
//            simplePdfExporterConfiguration.setMetadataAuthor("TheGeekyAsian");
            simplePdfExporterConfiguration.setTagged(true); // Para PDF/A, o documento deve ser marcado
            exporter.setConfiguration(simplePdfExporterConfiguration);

            setDefaultPdfFontEmbedded();
            exporter.exportReport();
            FileUtils.writeByteArrayToFile(file, bos.toByteArray());
            simpleOutputStreamExporterOutput.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDefaultPdfFontEmbedded() {
        JasperReportsContext jasperReportsContext = DefaultJasperReportsContext.getInstance();
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.font.name", "src/main/resources/fonts/arial/arial.ttf");
        jasperReportsContext.setProperty("net.sf.jasperreports.default.pdf.embedded", "true");
        String propriedades = jasperReportsContext.getProperty("net.sf.jasperreports.default.pdf.font.name");
        System.out.println(propriedades);
    }
}
