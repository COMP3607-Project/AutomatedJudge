package com.asg2;
// 816031597
import java.util.*;
import java.util.Map.Entry;

public class Cosine implements SimilarityMetric{

    public double measure (String s1, String s2){
        HashMap<String, int[]> map = new HashMap<String, int[]>();
        // String[] word_seq_text1 = s1.split(" ");
        // String[] word_seq_text2 = s2.split(" ");

        for (int i = 0; i < s1.length(); i++) {
            char t = s1.charAt(i); //toLowerCase(); //String t = s1[i].toLowerCase();
            String s = String.valueOf(t).toLowerCase();
            if (!map.containsKey(s)) {
                map.put(s, new int[2]);
            }
            map.get(s)[0]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            char t = s2.charAt(i);
            String s = String.valueOf(t).toLowerCase();
            if (!map.containsKey(s)) {
                map.put(s, new int[2]);
            }
            map.get(s)[1]++;
        }

        //calculate score
        double dot = 0;
        double norma = 0;
        double normb = 0;

        for (Entry<String, int[]> e : map.entrySet()) {
            int[] v = e.getValue();
            dot += v[0] * v[1];
            norma += v[0] * v[0];
            normb += v[1] * v[1];
        }
        norma = Math.sqrt(norma);
        normb = Math.sqrt(normb);

        if (dot == 0) {
            return 0;
        } else {
            return dot / (norma * normb);
        }
    }  
}

