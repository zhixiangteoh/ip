package duke;

import java.io.FileNotFoundException;

/**
 * Main class and driver of application.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for main program. Initializes TaskList and UI.
     *
     * @throws FileNotFoundException
     */
    public Duke() throws FileNotFoundException {
        tasks = new TaskList();
        ui = new Ui(tasks);
    }

    /**
     * Main method. Initializes Duke program object and runs it.
     *
     * @param args Command Line arguments
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }

    /**
     * Main high-level, abstract flow of program.
     */
    public void run() {
        ui.showLogo();
        ui.greet();
        ui.interact();
        ui.sayBye();
    }
}
