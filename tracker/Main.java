package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progress Tracker");
        boolean running = true;
        while (running) {
            String userCommand = sc.nextLine();
            if (userCommand.equals("exit")) {
                System.out.println("Bye!");
                break;
            } else if (userCommand.isBlank()) {
                System.out.println("No input.");
            } else {
                System.out.println("Error: Unknown command!");
            }
        }

    }
}
