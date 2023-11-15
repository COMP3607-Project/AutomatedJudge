package comp3607project;

public class Feedback{
    private String className;
    private String test;
    private int mark;
    private String response;


    public Feedback(String className, String test, int mark, String response) {
        this.className = className;
        this.test = test;
        this.mark = mark;
        this.response = response;
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