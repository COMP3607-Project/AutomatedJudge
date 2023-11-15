package comp3607project;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Header;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FeedbackReport implements PdfDocument {
    private Document document;
    private Font bold;

    public FeedbackReport() {
        this.document = new Document();
        this.bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    }

    @Override
    public void addLogo() {
        try {
            Image image = Image.getInstance("https://i0.wp.com/www.caribbeanclimate.bz/wp-content/uploads/2018/01/UWI-logo.jpg?ssl=1");
            image.scaleAbsolute(50f, 50f); // Adjust image size
            image.setAlignment(Image.ALIGN_CENTER); // Center the image
            document.add(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUniversityName() {
        Font bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Paragraph universityName = new Paragraph("The University of the West Indies, St. Augustine", bold);
            universityName.setAlignment(Paragraph.ALIGN_CENTER); // Center the text
            try {
                document.add(universityName);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
    }


    public void addContent() {
        try {
            // Add student info
            Paragraph studentInfo = new Paragraph("Student ID: " /*+ studentId*/ + "\nAssignment Number: " /*+ assignmentNumber*/ +"\nFeedback Report", bold);
            studentInfo.setAlignment(Paragraph.ALIGN_CENTER); 
            document.add(studentInfo);

            // Add class label above the table
            Paragraph classLabel = new Paragraph("Class");
            document.add(classLabel);

            // Create a table with 4 columns
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100); // Full Width
            table.setSpacingBefore(0f); // Space before table
            table.setSpacingAfter(10f); // Space after table

            // Add Column headers
            PdfPCell cell;
            cell = new PdfPCell(new Phrase("Class Name"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Test"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Mark"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Response"));
            cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
            table.addCell(cell);

            /*// Add data
            for (int i = 0; i < marks.size(); i++) {
                table.addCell("Class Name " + i);
                table.addCell("Test " + i);
                table.addCell(marks.get(i).toString());
                table.addCell(responses.get(i).toString());
            }*/

            // Add placeholder data
            for (int i = 0; i < 5; i++) {
                table.addCell("Class Name " + i);
                table.addCell("Test " + i);
                table.addCell("Mark " + i);
                table.addCell("Response " + i);
            }

            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateDocument() {    
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));
            document.open();
            addLogo();
            addUniversityName();
            addContent();
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}