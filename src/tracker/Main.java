package tracker;

import tracker.People.Student;
import tracker.school.AvailableCourses;
import tracker.school.SchoolCourses;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Main {

    //Organized list of students by when they were entered and by their ID Number
    public static LinkedHashMap<Integer, Student> students = new LinkedHashMap<>();
    //Emails used by the students
    public static HashSet<String> usedEmails = new HashSet<>();
    //Set initial student ID to 1000
    public static int currentStudentID = 1000;
    public static SchoolCourses currentCourses = new SchoolCourses();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        //boolean running = true;
        while (true) {
            String userCommand = sc.nextLine();
            //Exits the Program
            if (userCommand.equals("exit")) {
                System.out.println("Bye!");
                break;
            //Loop to add student(s) to students
            } else if (userCommand.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return");
                int studentsAdded = 0;
                while (true) {
                    String studentCommand = sc.nextLine();
                    if (!studentCommand.equals("back")) {
                        if (studentCommand.split(" ").length < 3) {
                            System.out.println("Incorrect credentials.");
                        } else {
                            studentsAdded = addStudent(studentCommand) ? studentsAdded + 1 : studentsAdded;
                        }
                    } else {
                        System.out.printf("Total %d students have been added.\n", studentsAdded);
                        break;
                    }
                }
            //Allows user to add points to the students courses
            } else if (userCommand.equals("add points")) {
                System.out.println("Enter an id and points or 'back' to return");
                while (true) {
                    String pointsInput = sc.nextLine();
                    if (pointsInput.equals("back")) {
                        break;
                    }
                    if (studentPointInputChecker(pointsInput)) {
                        addPoints(pointsInput);
                    }
                }
            //Allows user to print out student based on their ID code
            } else if (userCommand.equals("find")) {
                System.out.println("Enter an id or 'back' to return");
                while (true) {
                    String findUserInput = sc.nextLine();
                    if (findUserInput.equals("back")) {
                        break;
                    }
                    if (!findUserInput.matches("[0-9]+")) {
                        System.out.println("No student is found for id=" + findUserInput);
                    } else {
                        if (students.containsKey(Integer.valueOf(findUserInput))) {
                            System.out.println(students.get(Integer.parseInt(findUserInput)));
                        } else {
                            System.out.println("No student is found for id=" + findUserInput);
                        }
                    }
                }
            //Retrieves the statistics of the courses and finds students based on the courses they have taken
            } else if (userCommand.equals("statistics")) {
                System.out.println("Type the name of a course to see details or 'back' to quit:");
                currentCourses.printStatistics();
                while (true) {
                    String statisticInput = sc.nextLine();
                    if (statisticInput.equals("back")) {
                        break;
                    } else {
                        getTopStudents(statisticInput);
                    }

                }
            //Notifies students if they have completed a course
            } else if (userCommand.equals("notify")) {
                notifyStudents();
            }else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else if (userCommand.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");
            } else if (userCommand.equals("list")) {
                printStudentList();
            } else {
                System.out.println("Unknown command!");
            }
        }

    }

    //Adds students to Students
    public static boolean addStudent(String userInput) {
        String[] studentInfo = studentInputSeparator(userInput);
        String firstName = studentInfo[0];
        String lastName = studentInfo[1];
        String email = studentInfo[2];
        if (studentInputChecker(firstName, lastName, email)) {
            if (usedEmails.contains(email)) {
                System.out.println("This email is already taken.");
                return false;
            }
                usedEmails.add(email);
                Student newStudent = new Student(firstName, lastName, email, currentStudentID++);
                students.put(newStudent.getId(), newStudent);
                System.out.println("This student has been added.");
                return true;
        } else {
            return false;
        }
    }

    //Checks to see if the user entered the right amount of parameters for a student object
    public static boolean studentInputChecker(String firstName, String lastName, String email) {
            if (!firstName.matches("[A-Za-z][a-zA-z]*(['-][A-Za-z])*[a-zA-Z]*")
                    || firstName.length() < 2) {
                System.out.println("Incorrect first name.");
                return false;
            } else if (!lastName.matches("[A-Za-z][a-zA-z ]*(['-][A-Za-z])*([a-zA-z ])*")
                    || lastName.length() < 2){
                System.out.println("Incorrect last name.");
                return false;
            } else if(!email.matches("[.\\w'-]+@\\w+\\.\\w+")) {
                System.out.println("Incorrect email.");
                return false;
            }
            return true;
        }

    //Separates the user command into 3 different Strings
    public static String[] studentInputSeparator(String studentCommand) {
        studentCommand = studentCommand.trim().replaceAll("\\s+", " ");
        return new String[]
                {
                        studentCommand.substring(0, studentCommand.indexOf(" ")),
                        studentCommand.substring(studentCommand.indexOf(" ") + 1, studentCommand.lastIndexOf(" ")),
                        studentCommand.substring(studentCommand.lastIndexOf(" ") + 1)
                };
    }

    //Checks to see if the input from the user is acceptable for adding points to a student
    public static boolean studentPointInputChecker(String input) {
        String problemMessage = "Incorrect points format";
        if (!input.matches("[a-zA-Z0-9]+( [0-9]+){4}")) {
            System.out.println(problemMessage);
            return false;
        }
        String[] inputSplit = input.split(" ");
        if (!inputSplit[0].matches("[0-9]+")) {
            System.out.printf("No student is found for id=%s\n", inputSplit[0]);
            return false;
        }
        if (!students.containsKey(Integer.parseInt(inputSplit[0]))) {
            System.out.println("Not student is found for id=" + inputSplit[0]);
            return false;
        }
        return true;
    }
    //Prints students based on their id
    public static void printStudentList() {
        if (students.isEmpty()) {
            System.out.println("No students found");

        } else {
            System.out.println("Students:");
            for (int studentID : students.keySet()) {
                System.out.println(studentID);
            }
        }
    }

    //Adds Points to students and updates Class Array
    public static void addPoints(String input) {
        int[] pointValues = new int[5];
        String[] inputSplit = input.split(" ");
        for (int i = 0; i < inputSplit.length; i++) {
            pointValues[i] = Integer.parseInt(inputSplit[i]);
        }
        int studentID = Integer.parseInt(inputSplit[0]);

        currentCourses.addPoint(Arrays.copyOfRange(pointValues,1, 5));
        currentCourses.addStudent(
                students.get(studentID).getJava() == 0,
                students.get(studentID).getDataStructuresAndAlgorithms() ==0,
                students.get(studentID).getDatabases() == 0,
                students.get(studentID).getSpring() == 0
        );

        students.get(studentID).addAllClassPoints(
                pointValues[AvailableCourses.JAVA.getCourseCode()+1], pointValues[AvailableCourses.DSA.getCourseCode()+1],
                pointValues[AvailableCourses.DATABASES.getCourseCode()+1], pointValues[AvailableCourses.SPRING.getCourseCode()+1]
        );

        System.out.println("Points updated");
    }

    //prints current students in selected course and prints them out by their number of points in the course
    //followed by their id if their course points are the same
    public static void getTopStudents(String input) {
        //Creates collection of students from students
        ArrayList<Student> topStudents = new ArrayList<>();
        topStudents.addAll(students.values());
        //Checks to see if user chosen class exists
        AvailableCourses chosenClass = AvailableCourses.findByCourseName(input);
        if (chosenClass == null) {
            System.out.println("Unknown course.");
            return;
        }
        //Print Setup
        System.out.println(chosenClass.getCourseName());
        String tableHeaders = "id   points completed\n";
        System.out.print(tableHeaders);
        String talbeFields = "%d %d     %.1f%%\n";

        switch (chosenClass) {
            case JAVA:
                topStudents.sort(Comparator.comparing(Student::getJava).reversed().thenComparing(Student::getId));
                for (Student item : topStudents) {
                    if (item.getJava() != 0) {
                        System.out.printf(talbeFields, item.getId(), item.getJava(),
                                new BigDecimal(
                                        (double) item.getJava() / chosenClass.getPointsToComplete() * 100).
                                        setScale(1, RoundingMode.HALF_UP));
                    }
                }
                break;
            case DSA:
                topStudents.sort(Comparator.comparing(Student::getDataStructuresAndAlgorithms).reversed().
                        thenComparing(Student::getId));
                for (Student value : topStudents) {
                    if (value.getDataStructuresAndAlgorithms() != 0) {
                        System.out.printf(talbeFields, value.getId(), value.
                                        getDataStructuresAndAlgorithms(),
                                new BigDecimal(
                                        (double) value.getDataStructuresAndAlgorithms() /
                                                chosenClass.getPointsToComplete() * 100).
                                        setScale(1, RoundingMode.HALF_UP));
                    }
                }
                break;
            case DATABASES:
                topStudents.sort(Comparator.comparing(Student::getDatabases).reversed().thenComparing(Student::getId));
                for (Student student : topStudents) {
                    if (student.getDatabases() != 0) {
                        System.out.printf(talbeFields, student.getId(), student.getDatabases(),
                                new BigDecimal(
                                        (double) student.getDatabases() / chosenClass.getPointsToComplete() * 100).
                                        setScale(1, RoundingMode.HALF_UP));
                    }
                }
                break;
            case SPRING:
                topStudents.sort(Comparator.comparing(Student::getSpring).reversed().thenComparing(Student::getId));
                for (Student topStudent : topStudents) {
                    if (topStudent.getSpring() != 0) {
                        System.out.printf(talbeFields, topStudent.getId(), topStudent.getSpring(),
                                new BigDecimal(
                                        (double) topStudent.getSpring() / chosenClass.getPointsToComplete() * 100).
                                        setScale(1, RoundingMode.HALF_UP));
                    }
                }
        }
    }

    //Notifies students if they have completed a course and has not been notified before
    public static void notifyStudents() {
        int totalStudentsNotified = 0;
        boolean studentNotified = false;
        String message = """
                To: %s
                Re: Your Learning Progress
                Hello, %s %s! You have accomplished our %s course!
                """;
        for (Student student : students.values()) {
            if (!student.isJavaCompleted() && student.getJava() >=
                    AvailableCourses.JAVA.getPointsToComplete()) {
                students.get(student.getId()).setJavaCompleted();
                System.out.printf(message, student.getEmail(), student.getFirstName(), student.getLastName(),
                        AvailableCourses.JAVA.getCourseName());
                studentNotified = true;
            }
            if (!student.isDataStructuresAndAlgorithmsCompleted() && student.getDataStructuresAndAlgorithms() >=
                    AvailableCourses.DSA.getPointsToComplete()) {
                students.get(student.getId()).setDataStructuresAndAlgorithms();
                System.out.printf(message, student.getEmail(), student.getFirstName(), student.getLastName(),
                        AvailableCourses.DSA.getCourseName());
                studentNotified = true;
            }
            if (!student.isDatabasesCompleted() && student.getDatabases() >=
                    AvailableCourses.DATABASES.getPointsToComplete()) {
                students.get(student.getId()).setDatabasesCompleted();
                System.out.printf(message, student.getEmail(), student.getFirstName(), student.getLastName(),
                        AvailableCourses.DATABASES.getCourseName());
                studentNotified = true;
            }
            if (!student.isSpringCompleted() && student.getSpring() >=
                    AvailableCourses.SPRING.getPointsToComplete()) {
                students.get(student.getId()).setSpringCompleted();
                System.out.printf(message, student.getEmail(), student.getFirstName(), student.getLastName(),
                        AvailableCourses.SPRING.getCourseName());
                studentNotified = true;
            }
            if (studentNotified) {
                totalStudentsNotified++;
                studentNotified = false;
            }
        }
        System.out.printf("Total %s students have been notified.\n",
                totalStudentsNotified);
    }
}
