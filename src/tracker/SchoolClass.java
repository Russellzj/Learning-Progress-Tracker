package tracker;

import java.util.ArrayList;

public class SchoolClass {
    //The following Collections hold the scores that students get in each class
    private ArrayList<Integer> java = new ArrayList<>();
    private ArrayList<Integer> dsa = new ArrayList<>();
    private ArrayList<Integer> databases = new ArrayList<>();
    private ArrayList<Integer> spring = new ArrayList<>();

    public void addPoint(int[] points) {
        java.add(points[0]);
        dsa.add(points[1]);
        databases.add(points[2]);
        spring.add(points[3]);
    }

    



}
