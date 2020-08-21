import java.util.Scanner;

public class Duke {
    final static String TAB = "    ";
    final static String BORDER = TAB + "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____            _\n"
                + "|  _ \\ _   _   _| | ___\n"
                + "| | | | | | |/ _  |/ _ \\\n"
                + "| |_| | |_| | |_| |  __/\n"
                + "|____/ \\__,_|\\____|\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.println(BORDER);
        greet();

        // create input scanner
        Scanner in = new Scanner(System.in);
        echo(in);
        in.close();

        farewell();
    }

    private static void greet() {
        String greeting = TAB + "Hello! I'm Dude\n"
                        + TAB + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(BORDER);
    }

    private static void echo(Scanner in) {
        while (in.hasNextLine()) {
            String userInputLine = in.nextLine();

            if (!userInputLine.equalsIgnoreCase("bye")) {
                System.out.println(BORDER);
                System.out.println(TAB + userInputLine);
                System.out.println(BORDER);
            } else {
                break;
            }
        }
    }

    private static void farewell() {
        String farewell = TAB + "Bye. Hope to see you again soon!";
        System.out.println(BORDER);
        System.out.println(farewell);
        System.out.println(BORDER);
    }
}
