package comp3607project;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Font;

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

    @Override
    public void addStudentInfo() {
        // Add student ID and assignment number under the university name
        Paragraph studentInfo = new Paragraph("Student ID: " /*+ studentId*/ + "\nAssignment Number: " /*+ assignmentNumber*/ +"\nFeedback Report", bold);
        studentInfo.setAlignment(Paragraph.ALIGN_CENTER); 
        try {
            document.add(studentInfo);
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
            addStudentInfo();
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}