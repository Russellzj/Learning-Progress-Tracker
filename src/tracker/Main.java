package tracker;

import tracker.People.Student;

import java.util.*;

public class Main {

    //public static List<Student> students2= new ArrayList<>();
    //public static LinkedHashSet<Student> students = new LinkedHashSet<>();
    public static LinkedHashMap<Integer, Student> students = new LinkedHashMap();
    public static HashSet<String> usedEmails = new HashSet<>();
    //Set initial student ID to 1000
    public static int currentStudentID = 1000;
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
                boolean addPointLoop = true;
                while (addPointLoop) {
                    String pointsInput = sc.nextLine();
                    if (pointsInput.equalsIgnoreCase("back")) {
                        break;
                    }
                    if (pointsInput.contains("[a-zA-Z]")) {
                        System.out.println("Incorrect ");
                    } else {
                        String[] pointValues = pointsInput.split(" ");
                        if (pointValues.length != 5) {
                            System.out.println("Incorrect points format");
                            break;
                        }
                        for (String pointValue : pointValues) {
                            if (Integer.parseInt(pointValue) < 0) {
                                addPointLoop = false;
                                break;
                            }
                        }
                        if (!addPointLoop) {
                            System.out.println("Incorrect points format");
                            break;
                        }
                        if (!students.containsValue(Integer.parseInt(pointValues[0]))) {
                            System.out.printf("No student is found for id=%s\n", pointValues[0]);
                            break;
                        }
                        //Looks up student by ID and adds in the new points
                        students.get(Integer.parseInt(pointValues[0])).addAllClassPoints
                                (Integer.parseInt(pointValues[1]), Integer.parseInt(pointValues[2]),
                                        Integer.parseInt(pointValues[3]), Integer.parseInt(pointValues[4]));
                        System.out.println("Points updated");


                    }
                }
            } else if (userCommand.equals("find")) {
                System.out.println("Enter an id or 'back' to return");
                String findUserInput = sc.nextLine();
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

    public static void addStudentPoints(int java, int dataStructuresAndAlgorithms, int databases, int spring) {

    }

    public static boolean studentPointInputChecker(String input) {
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
}
