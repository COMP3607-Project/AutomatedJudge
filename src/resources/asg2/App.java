package com.asg2;
// 816031597
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
        ArrayList<TextSimilarityChecker> checkers = new ArrayList<TextSimilarityChecker>( );
        checkers.add(new SpellChecker( )); // uses Levenshtein as default
        checkers.add(new SpellChecker(1)); // uses Cosine
        checkers.add(new SpellChecker(3)); // uses Jaccard
        checkers.add(new LessonRecommender( )); // uses Cosine as default
        checkers.add(new LessonRecommender(2)); // uses Levenshtein
        checkers.add(new LessonRecommender(3)); // uses Jaccard

        for(TextSimilarityChecker checker: checkers)
            System.out.println(checker.evaluate("C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\comp3607\\comp3607\\src\\main\\java\\com\\asg2\\file1.txt", "C:\\Users\\vanes\\OneDrive - The University of the West Indies, St. Augustine\\Documents\\Year 3\\COMP 3607\\comp3607\\comp3607\\src\\main\\java\\com\\asg2\\file2.txt"));
    }
}
