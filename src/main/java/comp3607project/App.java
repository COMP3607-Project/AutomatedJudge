package comp3607project;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

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
            document.add(new Paragraph("Hello World!"));
            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
