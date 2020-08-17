public class Duke {
    final static String border = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____            _\n"
                    + "|  _ \\ _   _   _| | ___\n"
                    + "| | | | | | |/ _  |/ _ \\\n"
                    + "| |_| | |_| | |_| |  __/\n"
                    + "|____/ \\__,_|\\____|\\___|";
        System.out.println("Hello from\n" + logo);
        System.out.println(border);
        greet();
        farewell();
    }

    private static void greet() {
        String greeting = "Hello! I'm Dude\n"
                        + "What can I do for you?";
        System.out.println(greeting);
        System.out.println(border);
    }

    private static void farewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
        System.out.println(border);
    }
}
