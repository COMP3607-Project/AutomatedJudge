package com.asg2;
// 816031597
import java.util.HashSet;
import java.util.Set;

public class Jaccard implements SimilarityMetric{
    //Jaccard Similarity Code from: https://commons.apache.org/

    public double measure(String s1, String s2) {
        Set<String> intersectionSet = new HashSet<String>();
        Set<String> unionSet = new HashSet<String>();
        boolean unionFilled = false;
        int leftLength = s1.length();
        int rightLength = s2.length();

        if (leftLength == 0 || rightLength == 0) {
            return 0d;
        }

        for (int leftIndex = 0; leftIndex < leftLength; leftIndex++) {
            unionSet.add(String.valueOf(s1.charAt(leftIndex)));

            for (int rightIndex = 0; rightIndex < rightLength; rightIndex++) {
                if (!unionFilled) {
                    unionSet.add(String.valueOf(s2.charAt(rightIndex)));
                }
                
                if (s1.charAt(leftIndex) == s2.charAt(rightIndex)) {
                    intersectionSet.add(String.valueOf(s1.charAt(leftIndex)));
                }
            }
            unionFilled = true;
        }

        return Double.valueOf(intersectionSet.size()) / Double.valueOf(unionSet.size());
    }
}

