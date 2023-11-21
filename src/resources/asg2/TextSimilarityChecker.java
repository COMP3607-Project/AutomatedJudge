package com.asg2;
// 816031597
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class TextSimilarityChecker{
    protected int metric;

    public String readFile(String file1) throws FileNotFoundException{
        String line = "";
        File file = new File(file1);
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) 
            line = scan.nextLine();
        
        scan.close();  
        return line;
    }

    public int getMetric(){
        return metric;
    }

    public void setMetric(int metric){
        this.metric = metric;
    }

    //call respective constructor based on metric
    public abstract double determineScore(String file1, String file2); 
 
    public double evaluate(String file1, String file2) throws Exception{
        double score;

        //convert file to string
        String fileA = readFile(file1);
        String fileB = readFile(file2);

        score = determineScore(fileA, fileB);
        
        return score;
    }
}
