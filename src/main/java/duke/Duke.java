package duke;

import java.io.FileNotFoundException;

public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() throws FileNotFoundException {
        tasks = new TaskList();
        ui = new Ui(tasks);
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }

    public void run() {
        ui.showLogo();
        ui.greet();
        ui.interact();
        ui.sayBye();
    }
}
