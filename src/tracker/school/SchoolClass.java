package tracker.school;

import java.util.ArrayList;
import java.util.function.Function;

public class SchoolClass {
    //The following Collections hold the scores that students get in each class
    private ArrayList<Integer> java = new ArrayList<>();
    private ArrayList<Integer> dsa = new ArrayList<>();
    private ArrayList<Integer> databases = new ArrayList<>();
    private ArrayList<Integer> spring = new ArrayList<>();

    public void addPoint(int[] points) {
        if (points[0] != 0) {
            java.add(points[0]);
        }
        if (points[1] != 0) {
            dsa.add(points[1]);
        }
        if (points[2] != 0) {
            databases.add(points[2]);
        }
        if (points[3] != 0) {
            spring.add(points[3]);
        }
    }

    public String getMostPopular() {
        if (java.isEmpty() && dsa.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Most popular: n/a";
        }
        int largest = 0;
        //move all values to an array for more easily finding the largest
        int[] numberOfClassesTaken = new int[]{java.size(), dsa.size(), databases.size(), spring.size()};

        for (int i = 0; i < numberOfClassesTaken.length; i++) {
            if (numberOfClassesTaken[i] > numberOfClassesTaken[largest]) {
                largest = i;
            }
        }
        return "Most popular: " + AvailableClasses.findClassNameByClassCode(largest);
    }

    public String getLeastPopular() {
        if (java.isEmpty() && dsa.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Least popular: n/a";
        }
        int[] numberOfClassesTaken = new int[]{java.size(), dsa.size(), databases.size(), spring.size()};
        int smallest = 0;

        for (int i = 0; i < numberOfClassesTaken.length; i++) {
            if (numberOfClassesTaken[i] < numberOfClassesTaken[smallest]) {
                smallest = i;
            }
        }

        return "Least popular: " + AvailableClasses.findClassNameByClassCode(smallest);
    }

    //Calculates total points
    private int getTotalPoints(ArrayList<Integer> points) {
        int totalPoints = 0;
        for(int point : points) {
            totalPoints += point;
        }
        return totalPoints;
    }

    //Returns an array of all points
    private int[] getAllPoints() {
        return new int[]{
                getTotalPoints(java),
                getTotalPoints(dsa),
                getTotalPoints(databases),
                getTotalPoints(spring)
        };
    }

    public String getMostActive() {
        if (java.isEmpty() && dsa.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Highest activity: n/a";
        }
        int[] totalPoints = getAllPoints();
        int mostActivity = 0;

        for (int i = 0; i < totalPoints.length; i++) {
            if (totalPoints[i] > totalPoints[mostActivity]) {
                mostActivity = i;
            }
        }
        return "Highest activity: " + AvailableClasses.findClassNameByClassCode(mostActivity);
    }

    public String getLeastActive() {
        if (java.isEmpty() && dsa.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Lowest activity: n/a";
        }
        int[] totalPoint = getAllPoints();
        int leastActivity = 0;
        for (int i = 0; i < totalPoint.length; i++) {
            if (totalPoint[i] < totalPoint[leastActivity]) {
                leastActivity = i;
            }
        }

        return "Lowest activity: " + AvailableClasses.findClassNameByClassCode(leastActivity);
    }

    private int getAverageScore(ArrayList<Integer> points) {
        if (points.isEmpty()) {
            return 0;
        }
        return getTotalPoints(points)/points.size();
    }

    private int[] getAllAvg() {
        return new int[] {
          getAverageScore(java),
          getAverageScore(dsa),
          getAverageScore(databases),
          getAverageScore(spring)
        };
    }

    public String getEasiest() {
        if (java.isEmpty() && dsa.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Easiest course: n/a";
        }
        int[] averageScores = getAllAvg();
        int highestAvg = 0;

        for (int i = 0; i < averageScores.length; i++) {
            if (averageScores[i] > averageScores[highestAvg]) {
                if (averageScores[i] != 0) {
                    highestAvg = i;
                }
            }
        }
        return "Easiest course: " + AvailableClasses.findClassNameByClassCode(highestAvg);
    }

    public String getHardest() {
        if (java.isEmpty() && dsa.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Hardest course: n/a";
        }
        int[] averageScores = getAllAvg();
        int lowestAvg = 0;

        for (int i = 0; i < averageScores.length; i++) {
            if (averageScores[i] < averageScores[lowestAvg]) {
                lowestAvg = i;
            }
        }

        return "Hardest course: " + AvailableClasses.findClassNameByClassCode(lowestAvg);
    }

    public void printStatistics(){

        System.out.printf("%s\n%s\n%s\n%s\n%s\n%s\n",
                getMostPopular(), getLeastPopular(), getMostActive(), getLeastActive(), getEasiest(), getHardest());
    }
}
