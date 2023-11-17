package comp3607project;

public class App {
    public static void main(String[] args) {

        ReportContent content = new ReportContent();

        content.addFeedback(new Feedback("Flight", "Test 1", 10, "Nice!"));
        content.addFeedback(new Feedback("Flight", "Test 2", 10, "Good job!"));
        content.addFeedback(new Feedback("Passenger", "Test 3", 10, "Well done!"));
        content.addFeedback(new Feedback("Passenger", "Test 4", 10, "Excellent!"));
        content.addFeedback(new Feedback("Luggage", "Test 5", 10, "Great!"));
        content.addFeedback(new Feedback("Luggage", "Test 6", 10, "Awesome!"));
        
        FeedbackReport report = new FeedbackReport(content);
        report.generateDocument();
    }
}
