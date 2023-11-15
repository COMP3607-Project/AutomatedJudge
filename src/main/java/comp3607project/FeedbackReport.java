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
//import com.itextpdf.text.Header;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class FeedbackReport implements PdfDocument {
    private Document document;
    private Font bold;
    ArrayList<Feedback> studentFeedback = new ArrayList<Feedback>();

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

    public void addTableHeaders(){
        // Create a table with 3 columns
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100); // Full Width
        table.setSpacingBefore(4f); // Space before table

        // Add Column headers
        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Test"));
        cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Mark"));
        cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Response"));
        cell.setBackgroundColor(new BaseColor(255, 182, 193)); 
        table.addCell(cell);
        
        try {
            document.add(table);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void addStudentInfo(){
        // Add student info
        Paragraph studentInfo = new Paragraph("Student ID: " /*+ studentId*/ + "\nAssignment Number: " /*+ assignmentNumber*/ +"\nFeedback Report", bold);
        studentInfo.setAlignment(Paragraph.ALIGN_CENTER); 
        try {
            document.add(studentInfo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public void addTables() {
        try {

            // Create a HashMap to store each class name and its corresponding table
            HashMap<String, PdfPTable> tables = new HashMap<>();

            for (Feedback f : studentFeedback){
                // Get the class name
                String className = f.getClassName();

                // Check if the class name already exists in the HashMap
                if (!tables.containsKey(className)) {
                    // If it doesn't exist, create a new table and add it to the HashMap
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(100); // Full Width
                    tables.put(className, table);
                }

                // Get the table for this class name
                PdfPTable table = tables.get(className);

                // Add the data from the Feedback object to the table
                table.addCell(f.getTest());
                table.addCell(Integer.toString(f.getMark()));
                table.addCell(f.getResponse());
            }

            // Add all the tables to the document
            for (Map.Entry<String, PdfPTable> entry : tables.entrySet()) {
                // Add a paragraph with the class name before each table
                Paragraph classNameParagraph = new Paragraph("Class: " + entry.getKey());
                document.add(classNameParagraph);

                addTableHeaders();

                // Add the table
                document.add(entry.getValue());
            }
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
            addTables();
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addFeedback(Feedback feedback) {
        studentFeedback.add(feedback);
    }

}