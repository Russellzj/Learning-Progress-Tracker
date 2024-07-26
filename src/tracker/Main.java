package tracker;

import tracker.People.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        //boolean running = true;
        List<Student> students = new ArrayList<>();
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
                while(true) {
                    String studentCommand = sc.nextLine();
                    if (!studentCommand.equals("back")) {
                        if (studentInputChecker(studentCommand)) {
                            //removes bad white space from the user input
                            studentCommand = studentCommand.trim().replaceAll("\\s+", " ");
                            String[] studentID = studentInputSeparator(studentCommand);
                            if (emails.contains(studentID[2])) {
                                System.out.println("This email is already taken.");
                            } else {
                                emails.add(studentID[2]);
                                students.add(new Student(studentID[0], studentID[1], studentID[2], id++));
                                System.out.println("This student has been added.");
                            }
                        }
                    } else {
                        System.out.printf("Total %d students have been added.\n", students.size());
                        break;
                    }
                }
            } else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else if (userCommand.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");

            } else {
                System.out.println("Unknown command!");
            }
        }

    }

    //Checks to see if the user entered the right amount of parameters for a student object
    public static boolean studentInputChecker(String studentCommand) {
        studentCommand = studentCommand.trim().replaceAll("\\s+", " ");
        if (studentCommand.split(" ").length < 3) {
            System.out.println("Incorrect credentials.");
            return false;
        } else {
            String[] studentID = studentInputSeparator(studentCommand);
            if (!studentID[0].matches("[A-Za-z][a-zA-z]*(['-][A-Za-z])*[a-zA-Z]*")
                    || studentID[0].length() < 2) {
                System.out.println("Incorrect first name.");
                return false;
            } else if (!studentID[1].matches("[A-Za-z][a-zA-z ]*(['-][A-Za-z])*([a-zA-z ])*")
                    || studentID[1].length() < 2){
                System.out.println("Incorrect last name.");
                return false;
            } else if(!studentID[2].matches("[.\\w'-]+@\\w+\\.\\w+")) {
                System.out.println("Incorrect email.");
                return false;
            }
        }
        return true;
    }

    //Separates the user command into 3 different Strings
    public static String[] studentInputSeparator(String studentCommand) {
        return new String[]
                {
                        studentCommand.substring(0, studentCommand.indexOf(" ")),
                        studentCommand.substring(studentCommand.indexOf(" ") + 1, studentCommand.lastIndexOf(" ")),
                        studentCommand.substring(studentCommand.lastIndexOf(" ") + 1)
                };
    }
}
