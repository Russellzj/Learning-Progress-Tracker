package tracker.People;

public class Student {
    private String firstName;
    private String lastName;
    private String email;

    public Student(String unorganizedStudentInfo) {
        this.firstName = unorganizedStudentInfo.substring(0,
                unorganizedStudentInfo.indexOf(" "));
        this.lastName = unorganizedStudentInfo.substring(
                unorganizedStudentInfo.indexOf(" ") + 1,
                unorganizedStudentInfo.lastIndexOf(" "));
        this.email = unorganizedStudentInfo.substring(
                unorganizedStudentInfo.lastIndexOf(" ") + 1);
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
}
