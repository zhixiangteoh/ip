package duke;

import java.io.File;
import java.io.FileNotFoundException;

public class Storage {
    public static final String FILE_PATH = "./data/duke.txt";
    public static final String FOLDER_PATH = "./data";

    private static File file;
    private static File folder;
    private static Parser parser;

    public Storage() {
        file = new File(FILE_PATH);
        folder = new File(FOLDER_PATH);
        parser = new Parser();
    }

    public File load() throws FileNotFoundException {
        if (!folder.exists()) {
            System.out.println("Creating new folder 'data' in root directory:");
            folder.mkdir();
        }

        if (file.exists()) {
            return file;
        } else {
            throw new FileNotFoundException();
        }
    }
}
