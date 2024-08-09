package tracker;

import java.util.ArrayList;

public class SchoolClass {
    //The following Collections hold the scores that students get in each class
    private ArrayList<Integer> java = new ArrayList<>();
    private ArrayList<Integer> dsa = new ArrayList<>();
    private ArrayList<Integer> databases = new ArrayList<>();
    private ArrayList<Integer> spring = new ArrayList<>();

    public void addPoint(int[] points) {
        if (points[0] != 0 ) {
            java.add(points[0]);
        }
        if (points[1] != 0 ) {
            dsa.add(points[1]);
        }
        if (points[2] != 0 ) {
            databases.add(points[2]);
        }
        if (points[3] != 0 ) {
            spring.add(points[3]);
        }
    }

    public String mostPopular(){
        if (java.size() == 0 && dsa.size() == 0 && databases.size() == 0 && spring.size() == 0) {
            return "n/a";
        }
        String mostPopular;
        if (java.size() > dsa.size()) {
            if (java.size() > databases.size()) {
                if (java.size() > spring.size()) {
                    mostPopular = "Java";
                } else {
                    mostPopular = "Spring";
                }
            } else if (databases.size() > spring.size()) {
                mostPopular = "Databases";
            } else {
                mostPopular = "Spring";
            }
        } else if (dsa.size() > databases.size()) {
            if (dsa.size() > spring.size()) {
                mostPopular = "DSA";
            } else {
                mostPopular = "Spring";
            }
        } else if (databases.size() > spring.size()) {
            mostPopular = "Databases";
        } else {
            mostPopular = "Spring";
        }
        return "Most Popular: " + mostPopular;
    }





}
