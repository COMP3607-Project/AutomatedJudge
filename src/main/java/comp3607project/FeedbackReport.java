package comp3607project;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FeedbackReport{
    private Document document;
    private ReportHeader headers;
    private ReportContent content; 

    public FeedbackReport(ReportContent content) {
        this.document = new Document();
        this.headers = new ReportHeader();
        this.content = content;
    }

    public void generateDocument() {    
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Report.pdf"));
            document.open();
            headers.addHeader(document);
            content.addTables(document);
            document.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}