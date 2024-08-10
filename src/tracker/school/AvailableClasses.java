package tracker.school;

public enum AvailableClasses {
    JAVA(0, "Java", 600),
    DSA(1, "DSA", 400),
    DATABASES(2, "Databases", 480),
    SPRING(3, "Spring", 550);

    private final int classCode;
    private final String className;
    private final int pointsToComplete;

    AvailableClasses(int classCode, String className, int pointsToComplete) {
        this.classCode = classCode;
        this.className = className;
        this.pointsToComplete = pointsToComplete;
    }

    public int getClassCode(){
        return classCode;
    }

    public String getClassName(){
        return className;
    }

    public int getPointsToComplete() {
        return pointsToComplete;
    }

    public static String findClassNameByClassCode(int classCode) {
        for (AvailableClasses value : values()) {
            if (value.getClassCode() == classCode) {
                return value.className;
            }
        }
        return null;
    }

    public static int findPointsToCompleteByClassName(String className) {
        for (AvailableClasses value : values()) {
            if (value.getClassName().equals(className)) {
                return value.getPointsToComplete();
            }
        }
        return 0;
    }
}
