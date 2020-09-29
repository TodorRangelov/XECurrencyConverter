package console;

import java.util.Scanner;

public class ConsoleRunner {

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ConsoleCommandExecutor commandExecutor = new ConsoleCommandExecutor();

        while (true) {
            String[] args = scanner.nextLine().split("\\s+");

            commandExecutor.execute(args);
        }
    }

}
