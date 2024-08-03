package tracker.People;

public class Student {
    //Basic student information
    final private int id;
    private String firstName;
    private String lastName;
    private String email;
    //classes that are filled with points from said classes
    private int java = 0;
    private int dataStructuresAndAlgroithms = 0;
    private int databases = 0;
    private int spring = 0;

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


    //The following methods are to add points to the student's classes
    public void addJavaPoints(int points) {
        this.java += points;
    }
    public void addDataStructuresAndAlgorithmsPoints(int points) {
        this.dataStructuresAndAlgroithms += points;
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
