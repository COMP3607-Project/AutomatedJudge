package comp3607project;

public class App {
    public static void main(String[] args) {

        ReportContent content = new ReportContent();

        ReportContent.addFeedback(new Feedback("Flight", "Test 1", 10, "Nice!"));
        ReportContent.addFeedback(new Feedback("Flight", "Test 2", 10, "Good job!"));
        ReportContent.addFeedback(new Feedback("Passenger", "Test 3", 10, "Well done!"));
        ReportContent.addFeedback(new Feedback("Passenger", "Test 4", 10, "Excellent!"));
        ReportContent.addFeedback(new Feedback("Luggage", "Test 5", 10, "Great!"));
        ReportContent.addFeedback(new Feedback("Luggage", "Test 6", 10, "Awesome!"));
        
        FeedbackReport report = new FeedbackReport(content);
        report.generateDocument();
    }
}
