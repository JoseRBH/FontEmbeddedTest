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

public class Main {
    public static void main (String[] args) throws Exception {

        ExportPDF exportPDF = new ExportPDF();
        PDFA3File pdfa3File = new PDFA3File();

        exportPDF.exportPdf();
        pdfa3File.doIt("report.pdf","Teste.pdf");
    }
}