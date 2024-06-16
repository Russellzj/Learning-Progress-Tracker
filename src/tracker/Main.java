package tracker;

import tracker.People.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        //boolean running = true;
        List<Student> students = new ArrayList<Student>();
        while (true) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (userCommand.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return");
                String studentCommand = sc.nextLine();
                if (!studentCommand.equals("back")) {
                    //removes bad white space from the user input
                    studentCommand = studentCommand.trim().replaceAll("\\s+", " ");
                    if (studentInputChecker(studentCommand)) {
                        String[] studentID = studentInputSeparator(studentCommand);
                        students.add(new Student(studentID[0], studentID[1], studentID[2]));
                    }
                } else {
                    System.out.printf("Total %d students have been added.\n", students.size());
                }
            } else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else {
                System.out.println("Error: Unknown command!");
            }
        }

    }

    //Checks to see if the user entered the right amount of parameters for a student object
    public static boolean studentInputChecker(String studentCommand) {
        if (studentCommand.split(" ").length > 2) {
            System.out.println("Incorrect credentials.");
            return false;
        } else {
            String[] studentID = studentInputSeparator(studentCommand);
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
