package com.myapps.rds;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class LargePdfGenerator {
    public static void main(String[] args) {
        int fileSizeMB = 400;
        int dummyDataLength = fileSizeMB * 1024 * 1024; // 1 MB = 1024 KB, 1 KB = 1024 bytes

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Generate dummy content (random text) for the whole file size
            StringBuilder dummyText = new StringBuilder(dummyDataLength);
            Random random = new Random();
            for (int i = 0; i < dummyDataLength; i++) {
                char randomChar = (char) (random.nextInt(26) + 'a');
                dummyText.append(randomChar);
            }

            // Write the dummy content to the PDF
            contentStream.setFont(PDType1Font.COURIER, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText(dummyText.toString());
            contentStream.endText();

            contentStream.close();

            // Save the PDF to a file
            String filePath = "large_dummy_pdf.pdf";
            document.save(new File(filePath));
            document.close();

            System.out.println("Large dummy PDF file generated successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
