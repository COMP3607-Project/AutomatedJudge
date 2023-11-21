package comp3607project;

public class Feedback{
    private String className;
    private String test;
    private int mark;
    private String response;
    private static String fileName = "";

    public Feedback(String className, String test, int mark, String response) {
        this.className = className;
        this.test = test;
        this.mark = mark;
        this.response = response;
    }

    public static void setFileName(String fileName) {
        Feedback.fileName = fileName;
    }

    public static String getFileName() {
        return Feedback.fileName;
    }

    public String getClassName(){
        return this.className;
    }

    public String getTest() {
        return this.test;
    }

    public int getMark() {
        return this.mark;
    }

    public String getResponse() {
        return this.response;
    }

}
