package tracker;

import tracker.People.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        boolean running = true;
        List<Student> students = new ArrayList<Student>();
        while (running) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (userCommand.equals("add students")) {
                System.out.println("Enter student credentials or 'back' to return");
                String studentCommand = sc.nextLine();
                if (!studentCommand.equals("back")) {
                    students.add((new Student(studentCommand)));
                }
            } else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else {
                System.out.println("Error: Unknown command!");
            }
        }

    }
}
