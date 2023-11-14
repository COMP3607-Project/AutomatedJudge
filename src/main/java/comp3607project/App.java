package comp3607project;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Header;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;
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

            //add image logo at the top of the page
            try {
                Image image = Image.getInstance("https://i0.wp.com/www.caribbeanclimate.bz/wp-content/uploads/2018/01/UWI-logo.jpg?ssl=1");
                image.scaleAbsolute(50f, 50f); // Adjust image size
                image.setAlignment(Image.ALIGN_CENTER); // Center the image
                document.add(image);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add university name under the logo
            Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Paragraph universityName = new Paragraph("The University of the West Indies, St. Augustine", bold);
            universityName.setAlignment(Paragraph.ALIGN_CENTER); // Center the text
            document.add(universityName);

            // Add student ID and assignment number under the university name
            Paragraph studentInfo = new Paragraph("Student ID: " /*+ studentId*/ + "\nAssignment Number: " /*+ assignmentNumber*/ +"\nFeedback Report", bold);
            studentInfo.setAlignment(Paragraph.ALIGN_CENTER); 
            document.add(studentInfo);

            // Add a class label above the table
            Paragraph paragraph = new Paragraph();
            paragraph.add("Class");
            document.add(paragraph);

            // Create a table with 4 columns
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100); // Full Width
            table.setSpacingBefore(0f); // Space before table
            table.setSpacingAfter(10f); // Space after table

            // Add Column headers
            PdfPCell cell;
            PdfPTable headerTable = new PdfPTable(4);
            headerTable.setWidthPercentage(100); // Full Width
            headerTable.setSpacingBefore(10f); // Space before table


            cell = new PdfPCell(new Phrase("Class Name"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            headerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Test"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            headerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Mark"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            headerTable.addCell(cell);

            cell = new PdfPCell(new Phrase("Response"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            headerTable.addCell(cell);

            document.add(headerTable);

            // Add placeholder data
            for (int i = 0; i < 5; i++) {
                table.addCell("Class Name " + i);
                table.addCell("Test " + i);
                table.addCell("Mark " + i);
                table.addCell("Response " + i);
            }

            document.add(table);

            Header h1 = new Header("test","one");
            document.add(h1);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
