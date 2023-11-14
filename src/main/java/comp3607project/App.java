package comp3607project;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import java.io.FileOutputStream;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
            document.open();

            // Create a table with 4 columns
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100); // Full Width
            table.setSpacingBefore(10f); // Space before table
            table.setSpacingAfter(10f); // Space after table

            // Add Column headers
            PdfPTable headerTable = new PdfPTable(4);
            headerTable.setWidthPercentage(100); // Full Width
            headerTable.setSpacingBefore(10f); // Space before table

            headerTable.addCell(new PdfPCell(new Phrase("Class Name")));
            headerTable.addCell(new PdfPCell(new Phrase("Test")));
            headerTable.addCell(new PdfPCell(new Phrase("Mark")));
            headerTable.addCell(new PdfPCell(new Phrase("Response")));

            document.add(headerTable);

            // Add placeholder data
            for (int i = 0; i < 5; i++) {
                table.addCell("Class Name " + i);
                table.addCell("Test " + i);
                table.addCell("Mark " + i);
                table.addCell("Response " + i);
            }

            document.add(table);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
