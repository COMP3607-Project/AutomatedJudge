package comp3607project;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ReportHeaderTest {

    @Mock
    private Document document;

    private ReportHeader reportHeader;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reportHeader = new ReportHeader();
    }

    @Test
    public void testAddHeader() {
        reportHeader.addHeader(document);

        // Verify that the add method was called 3 times: once for the logo, once for the university name, and once for the student info
        try {
            verify(document, times(3)).add(any());
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}