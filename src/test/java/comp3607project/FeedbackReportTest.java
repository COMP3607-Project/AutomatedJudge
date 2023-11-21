package comp3607project;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FeedbackReportTest {

    @Mock
    private ReportContent content;

    private FeedbackReport feedbackReport;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        feedbackReport = spy(new FeedbackReport(content));
        Feedback.setFileName("816033413_JasonBalroop_A1.zip");
    }

    @Test
    public void testGenerateDocument() {
        feedbackReport.generateDocument();
        try {
            verify(feedbackReport, times(1)).generateDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}