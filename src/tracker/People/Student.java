package tracker.People;

public class Student {
    //Basic student information
    final private int id;
    private String firstName;
    private String lastName;
    private String email;
    //classes that are filled with points from said classes
    private int java = 0;
    private int dataStructuresAndAlgorithms = 0;
    private int databases = 0;
    private int spring = 0;
    private boolean javaCompleted = false;
    private boolean dataStructuresAndAlgorithmsCompleted = false;
    private boolean databasesCompleted = false;
    private boolean springCompleted = false;

    public Student(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public int getId() {return id;}
    public int getJava() { return java; }
    public int getDataStructuresAndAlgorithms() { return dataStructuresAndAlgorithms;}
    public int getDatabases() { return databases; }
    public int getSpring() { return spring; }


    //The following methods are to add points to the student's classes
    public void addJavaPoints(int points) {
        this.java += points;
    }
    public void addDataStructuresAndAlgorithmsPoints(int points) {
        this.dataStructuresAndAlgorithms += points;
    }
    public void addDatabasesPoints(int points) {
        this.databases += points;
    }
    public void addSpringPoints(int points) {
        this.spring += points;
    }

    public void addAllClassPoints(int java, int dataStructuresAndAlgorithms, int databases, int spring) {
        addJavaPoints(java);
        addDataStructuresAndAlgorithmsPoints(dataStructuresAndAlgorithms);
        addDatabasesPoints(databases);
        addSpringPoints(spring);
    }

    //get methods for completed courses and notified
    public boolean isJavaCompleted() {
        return javaCompleted;
    }
    public boolean isDataStructuresAndAlgorithmsCompleted() {
        return dataStructuresAndAlgorithmsCompleted;
    }
    public boolean isDatabasesCompleted() {
        return databasesCompleted;
    }
    public boolean isSpringCompleted() {
        return springCompleted;
    }

    //Sets the completed course to true if a notification is sent
    public void setJavaCompleted() {
        this.javaCompleted = true;
    }
    public void setDataStructuresAndAlgorithms() {
        this.dataStructuresAndAlgorithmsCompleted = true;
    }
    public void setDatabasesCompleted() {
        this.databasesCompleted = true;
    }
    public void setSpringCompleted() {
        this.springCompleted = true;
    }

    @Override
    public String toString() {
        return String.format("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d", id,java, dataStructuresAndAlgorithms,
                databases, spring);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Student)) return false;
        return (this.id == ((Student) other).getId());
    }

    @Override
    public int hashCode() {
        int hash = 32;
        return hash * 31 + this.id;
    }

}
