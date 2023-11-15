package comp3607project;

public class App {
    public static void main(String[] args) {
        PdfDocument report = new FeedbackReport();

        report.addFeedback(new Feedback("Flight", "Test 1", 10, "Nice!"));
        report.addFeedback(new Feedback("Flight", "Test 2", 10, "Good job!"));
        report.addFeedback(new Feedback("Passenger", "Test 3", 10, "Well done!"));
        report.addFeedback(new Feedback("Passenger", "Test 4", 10, "Excellent!"));
        report.addFeedback(new Feedback("Luggage", "Test 5", 10, "Great!"));
        report.addFeedback(new Feedback("Luggage", "Test 6", 10, "Awesome!"));

        report.generateDocument();
    }
}
