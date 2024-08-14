package tracker.school;

public enum AvailableClasses {
    JAVA(0, "Java", 600),
    DSA(1, "DSA", 400),
    DATABASES(2, "Databases", 480),
    SPRING(3, "Spring", 550);

    private final int courseCode;
    private final String courseName;
    private final int pointsToComplete;

    AvailableClasses(int courseCode, String courseName, int pointsToComplete) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.pointsToComplete = pointsToComplete;
    }

    public int getCourseCode(){
        return courseCode;
    }

    public String getCourseName(){
        return courseName;
    }

    public int getPointsToComplete() {
        return pointsToComplete;
    }

    public static AvailableClasses findCourseByCourseCode(int classCode) {
        for (AvailableClasses value : values()) {
            if (value.getCourseCode() == classCode) {
                return value;
            }
        }
        return null;
    }

    public static AvailableClasses findByCourseName(String className) {
        for (AvailableClasses value : values()) {
            if (className.equals(value.getCourseName())) {
                return value;
            }
        } return null;
    }

    public static int findPointsToCompleteByCourseName(String className) {
        for (AvailableClasses value : values()) {
            if (value.getCourseName().equals(className)) {
                return value.getPointsToComplete();
            }
        }
        return 0;
    }
}
