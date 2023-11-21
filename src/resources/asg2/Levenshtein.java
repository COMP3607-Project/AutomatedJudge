package com.asg2;
// 816031597
public class Levenshtein implements SimilarityMetric{

    public double measure (String s1, String s2){
    // if either string is empty, difference is inserting all chars from the other
    if (s1.length() == 0)
        return s2.length();
    if (s2.length() == 0)
        return s1.length();

    // if first letters are the same, the difference is whatever is required to edit the rest of the strings
    if (s1.charAt(0) == s2.charAt(0))
        return measure(s1.substring(1), s2.substring(1));

    /* else try:
        *      changing first letter of s to that of t,
        *      remove first letter of s, or
        *      remove first letter of t
        */

    //begin at index 1 
    double a = measure(s1.substring(1), s2.substring(1));
    double b = measure(s1, s2.substring(1));
    double c = measure(s1.substring(1), s2);

    if (a > b)
        a = b;
    if (a > c)
        a = c;

    //any of which is 1 edit plus editing the rest of the strings
    return a + 1;
}
}
