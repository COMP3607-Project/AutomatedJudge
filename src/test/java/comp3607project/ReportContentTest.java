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
        reportContent = new ReportContent();
        feedbackList = new ArrayList<>();

        // Add some feedback to the list
        feedbackList.add(new Feedback("Class1", "Test1", 90, "Response1"));
        feedbackList.add(new Feedback("Class2", "Test2", 85, "Response2"));
        feedbackList.add(new Feedback("Class1", "Test3", 88, "Response3"));
    }

    @Test
    public void testAddFeedback() {
        for (Feedback f : feedbackList) {
            reportContent.addFeedback(f);
        }

        // Verify that the feedback was added to the reportContent
        // This assumes that you have a getStudentFeedback method in your ReportContent class
        assertEquals(feedbackList, reportContent.getStudentFeedback());
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
            reportContent.addFeedback(f);
        }

        try {
            reportContent.addTables(document);
            verify(document, times(4)).add(any(PdfPTable.class));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}