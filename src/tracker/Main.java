package tracker;

import tracker.People.Student;

import java.util.*;

public class Main {

    //public static List<Student> students2= new ArrayList<>();
    //public static LinkedHashSet<Student> students = new LinkedHashSet<>();
    public static LinkedHashMap<Integer, Student> students = new LinkedHashMap<>();
    public static HashSet<String> usedEmails = new HashSet<>();
    //Set initial student ID to 1000
    public static int currentStudentID = 1000;
    public static SchoolClass currentClasses = new SchoolClass();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        //boolean running = true;
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("exit")) {
                System.out.println("Bye!");
                break;
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
            } else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else if (userCommand.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");

            } else if (userCommand.equals("list")) {
                printStudentList();
            }
            else {
                System.out.println("Unknown command!");
            }
        }

    }

    //Adds students to Student ArrayList
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
        students.get(pointValues[0]).addAllClassPoints(
                pointValues[1], pointValues[2], pointValues[3], pointValues[4]
        );
        currentClasses.addPoint(Arrays.copyOfRange(pointValues,1, 5));
        System.out.println("Points updated");
    }
}
