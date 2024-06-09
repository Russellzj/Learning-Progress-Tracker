package tracker;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Learning Progre Tracker");
        boolean running = true;
        while (running) {
            String userCommand = sc.next();
            switch (userCommand) {
                case "exit":
                    running = false;
                    System.out.println("Bye!");
                    break;
                case "":
                    System.out.println("No input");
                default:
                    System.out.println("Unknown command!");
            }
        }

    }
}
