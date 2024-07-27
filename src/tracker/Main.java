package tracker;

import tracker.People.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Student> students= new ArrayList<>();
    public static HashSet<String> usedEmails = new HashSet<>();
    public static int currentStudentID = 1000;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        //boolean running = true;
        //List<Student> students = new ArrayList<>();
        HashSet<String> emails = new HashSet<>();
        //Set initial student ID to 1000
        int id = 1000;
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (userCommand.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return");
                int studentsAdded = 0;
                while(true) {
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
            } else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else if (userCommand.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");

            } else if (userCommand.equals("list")) {
                printStudentList(students);
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
                students.add(new Student(firstName, lastName, email, currentStudentID++));
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

    //Prints students based on their id
    public static void printStudentList(List<Student> students) {
        if (students.isEmpty()) {
            System.out.println("No students found");

        } else {
            System.out.println("Students:");
            for (Student student : students) {
                System.out.println(student.getId());
            }
        }
    }
}
