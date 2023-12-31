package comp3607project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.ArrayList;

public class ReportContentTest {

    @Mock
    private Document document;

    private ReportContent reportContent;
    private ArrayList<Feedback> feedbackList;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this) ;

        Feedback.setFileName("816033413_JasonBalroop_A1.zip");
        reportContent = new ReportContent();
        feedbackList = new ArrayList<>();
        
        feedbackList.add(new Feedback("Class1", "Test1", 90, "Response1"));
        feedbackList.add(new Feedback("Class2", "Test2", 85, "Response2"));
        feedbackList.add(new Feedback("Class1", "Test3", 88, "Response3"));
    }

    @Test
    public void testAddFeedback() {
        for (Feedback f : feedbackList) {
            ReportContent.addFeedback(f);
        }

        assertEquals(feedbackList, ReportContent.getStudentFeedback());
    }

    @Test
    public void testAddTableHeaders() {
        try {
            reportContent.addTableHeaders(document);
            verify(document, times(1)).add(any(PdfPTable.class));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddTables() {
        for (Feedback f : feedbackList) {
            ReportContent.addFeedback(f);
        }

        try {
            reportContent.addTables(document);
            verify(document, times(4)).add(any(PdfPTable.class));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}