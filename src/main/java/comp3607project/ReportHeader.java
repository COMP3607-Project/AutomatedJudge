package comp3607project;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;

public class ReportHeader {

    private Font bold;

    public ReportHeader(){
        this.bold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    }

    public void addHeader(Document document) {
        try {
            addLogo(document);
            addUniversityName(document);
            addStudentInfo(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addLogo(Document document) throws Exception{
        Image image = Image.getInstance("https://i0.wp.com/www.caribbeanclimate.bz/wp-content/uploads/2018/01/UWI-logo.jpg?ssl=1");
        image.scaleAbsolute(50f, 50f); 
        image.setAlignment(Image.ALIGN_CENTER); 
        document.add(image);
    }

    private void addUniversityName(Document document) throws DocumentException {
        Paragraph universityName = new Paragraph("The University of the West Indies, St. Augustine", bold);
        universityName.setAlignment(Paragraph.ALIGN_CENTER); 
        document.add(universityName);
    }

    public void addStudentInfo(Document document){
        String fileName = Feedback.getFileName();
        String[] parts = fileName.split("_");

        if (parts.length < 3) {
            throw new IllegalArgumentException("File name must be in the format '<studentId>_A_<assignmentNumber>.zip'");
        }

        String studentId = parts[0];
        String assignmentNumber = parts[2].replace(".zip", "").replace("A", "");

        Paragraph studentInfo = new Paragraph("Student ID: " + studentId + "\nAssignment Number: " + assignmentNumber +"\nFeedback Report", bold);
        studentInfo.setAlignment(Paragraph.ALIGN_CENTER); 
        try {
            document.add(studentInfo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
