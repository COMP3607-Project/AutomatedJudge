package com.asg2;
// 816031597
public class SpellChecker extends TextSimilarityChecker{

    public SpellChecker() { 
        setMetric(2);
    }

    public SpellChecker(int a) { 
        if (a == 1)
            setMetric(1);
        else 
            if (a == 3)
                setMetric(3);
    }

    public double determineScore(String file1, String file2){
        double score = 0;
        switch (super.getMetric()) {
            case 1:
                {
                    Cosine cos = new Cosine();
                    score = cos.measure(file1, file2);
                }
            break;

            case 2:
                {
                    Levenshtein lev = new Levenshtein();
                    score = lev.measure(file1, file2);
                }
            break;

            case 3:    
                {
                    Jaccard jac = new Jaccard();
                    score = jac.measure(file1, file2);
                }
            }

        return score;    
    }
}