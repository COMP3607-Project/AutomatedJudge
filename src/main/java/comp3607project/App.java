package comp3607project;

public class App {
    public static void main(String[] args) {
        PdfDocument report = new FeedbackReport();
        report.generateDocument();
    }
}
