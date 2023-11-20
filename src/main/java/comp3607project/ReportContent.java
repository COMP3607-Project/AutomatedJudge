package comp3607project;

import java.util.ArrayList;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.HashMap;

public class ReportContent {

    private static ArrayList<Feedback> studentFeedback = new ArrayList<>();

    public static void addFeedback(Feedback f) {
        studentFeedback.add(f);
    }

    public static ArrayList<Feedback> getStudentFeedback() {
        return studentFeedback;
    }

    public void addTableHeaders(Document document){
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

    public void addTables(Document document) {
        try {
            HashMap<String, PdfPTable> tables = new HashMap<>();

            for (Feedback f : studentFeedback){

                String className = f.getClassName();

                // Check if the class name already exists in the HashMap
                if (!tables.containsKey(className)) {
                    // If it doesn't exist, create a new table and add it to the HashMap
                    PdfPTable table = new PdfPTable(3);
                    table.setWidthPercentage(100);
                    tables.put(className, table);
                }

                PdfPTable table = tables.get(className);

                // Add the data from the Feedback object to the table
                table.addCell(f.getTest());
                table.addCell(Integer.toString(f.getMark()));
                table.addCell(f.getResponse());
            }

            // adding all the tables to the document
            for (Map.Entry<String, PdfPTable> entry : tables.entrySet()) {
                // adding label for each table
                Paragraph classNameParagraph = new Paragraph("Class: " + entry.getKey());
                document.add(classNameParagraph);

                addTableHeaders(document);

                document.add(entry.getValue());
            }

            studentFeedback.clear();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}