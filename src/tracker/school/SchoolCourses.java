package tracker.school;

import java.util.ArrayList;
import java.util.Objects;

public class SchoolCourses {
    //The following Collections hold the scores that students get in each class
    private ArrayList<Integer> javaTotalPoints = new ArrayList<>();
    private ArrayList<Integer> dsaTotalPoints = new ArrayList<>();
    private ArrayList<Integer> databases = new ArrayList<>();
    private ArrayList<Integer> spring = new ArrayList<>();

    //The following holds total number of unique students in each course
    private int javaNumberOfStudents = 0;
    private int  dsaNumberOfStudents = 0;
    private int databasesNumberOfStudents = 0;
    private int springNumberOfStudents = 0;


    public void addPoint(int[] points) {
        if (points[0] != 0) {
            javaTotalPoints.add(points[0]);
        }
        if (points[1] != 0) {
            dsaTotalPoints.add(points[1]);
        }
        if (points[2] != 0) {
            databases.add(points[2]);

        }
        if (points[3] != 0) {
            spring.add(points[3]);
        }
    }
    public void addStudent(boolean newJavaStudent, boolean newDsaStudnet, boolean newDatabasesStudent
            , boolean newSpringStudent) {
        javaNumberOfStudents = newJavaStudent ? javaNumberOfStudents + 1 : javaNumberOfStudents;
        dsaNumberOfStudents = newDsaStudnet ? dsaNumberOfStudents + 1 : dsaNumberOfStudents;
        databasesNumberOfStudents = newDatabasesStudent ? databasesNumberOfStudents + 1 : databasesNumberOfStudents;
        springNumberOfStudents = newSpringStudent ? springNumberOfStudents + 1 : springNumberOfStudents;
    }

    //Gets the largest course from the array of ints and returns the course(s)' names
    public String getLargestCourse(int[] values) {
        //Collection to hold largest values location
        ArrayList<Integer> largest = new ArrayList<>();
        //Starts from the first element
        largest.add(0);

        //Goes through the array and find the largest value(s)
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[largest.get(largest.size() - 1)]) {
                largest.clear();
                largest.add(i);
            } else if (values[i] == values[largest.get(largest.size() - 1)]) {
                largest.add(i);
            }
        }

        StringBuilder largestCourseNames = new StringBuilder();
        for (int course : largest) {
            largestCourseNames.append(Objects.requireNonNull(AvailableCourses.findCourseByCourseCode(course)).getCourseName()).append(" ");
        }

        return largestCourseNames.toString();
    }

    //Gets the largest course from the array of ints and returns the course(s)' names
    public String getSmallestCourse(int[] values) {
        //Collection to hold largest values location
        ArrayList<Integer> smallest = new ArrayList<>();
        //Starts from the first element
        smallest.add(0);

        //Goes through the array and find the smallest value(s)
        for (int i = 1; i < values.length; i++) {
            if (values[i] < values[smallest.get(smallest.size() - 1)]) {
                smallest.clear();
                smallest.add(i);
            } else if (values[i] == values[smallest.get(smallest.size() - 1)]) {
                smallest.add(i);
            }
        }

        String smallestCourseNames = "";
        for (int course : smallest) {
            smallestCourseNames += AvailableCourses.findCourseByCourseCode(course).getCourseName() + " ";
        }

        return smallestCourseNames;
    }

    //returns true if all the courses have the same value
    private boolean isAllSame(int[] courses) {
        boolean allEqual = true;
        //Holds the first courses value to compare to all the others
        int courseComparator = courses[0];
        for (int course : courses) {
            if (courseComparator != course) return false;
        }
        return true;
    }

    public String getMostPopular() {
        if (javaTotalPoints.isEmpty() && dsaTotalPoints.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "n/a";
        }
        //move all values to an array for more easily finding the largest
        int[] numberOfCoursesTaken = new int[]{javaNumberOfStudents, dsaNumberOfStudents,
                databasesNumberOfStudents, springNumberOfStudents};
        return getLargestCourse(numberOfCoursesTaken);
    }

    public String getLeastPopular() {
        if (javaTotalPoints.isEmpty() && dsaTotalPoints.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "Least popular: n/a";
        }
        int[] numberOfCoursesTaken = new int[]{javaNumberOfStudents, dsaNumberOfStudents, databasesNumberOfStudents,
                springNumberOfStudents};
        if (isAllSame(numberOfCoursesTaken)) {
            return "n/a";
        }

        return getSmallestCourse(numberOfCoursesTaken);
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
                getTotalPoints(javaTotalPoints),
                getTotalPoints(dsaTotalPoints),
                getTotalPoints(databases),
                getTotalPoints(spring)
        };
    }

    public String getMostActive() {
        if (javaTotalPoints.isEmpty() && dsaTotalPoints.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "n/a";
        }
        //move all values to an array for more easily finding the largest
        int[] numberOfClassesTaken = new int[]{javaTotalPoints.size(), dsaTotalPoints.size(), databases.size(), spring.size()};
        return getLargestCourse(numberOfClassesTaken);
    }

    public String getLeastActive() {
        if (javaTotalPoints.isEmpty() && dsaTotalPoints.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "n/a";
        }
        int[] numberOfCoursesTaken = new int[]{javaTotalPoints.size(), dsaTotalPoints.size(), databases.size(), spring.size()};
        if (isAllSame(numberOfCoursesTaken)) {
            return "n/a";
        }

        return getSmallestCourse(numberOfCoursesTaken);
    }

    private int getAverageScore(ArrayList<Integer> points) {
        if (points.isEmpty()) {
            return 0;
        }
        return getTotalPoints(points)/points.size();
    }

    private int[] getAllAvg() {
        return new int[] {
          getAverageScore(javaTotalPoints),
          getAverageScore(dsaTotalPoints),
          getAverageScore(databases),
          getAverageScore(spring)
        };
    }

    public String getEasiest() {
        if (javaTotalPoints.isEmpty() && dsaTotalPoints.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "n/a";
        }

        int[] averageScores = getAllAvg();

        return getLargestCourse(averageScores);
    }

    public String getHardest() {
        if (javaTotalPoints.isEmpty() && dsaTotalPoints.isEmpty() && databases.isEmpty() && spring.isEmpty()) {
            return "n/a";
        }
        int[] averageScores = getAllAvg();

        return getSmallestCourse(averageScores);
    }

    public void printStatistics(){

        System.out.printf("Most popular: %s\n" +
                        "Least popular: %s\n" +
                        "Highest activity: %s\n" +
                        "Lowest activity: %s\n" +
                        "Easiest course: %s\n" +
                        "Hardest course: %s\n",
                getMostPopular(), getLeastPopular(), getMostActive(), getLeastActive(), getEasiest(), getHardest());
    }
}
