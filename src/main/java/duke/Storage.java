package duke;

import java.io.File;

/**
 * Storage containing file and folder contents.
 */
public class Storage {
    public static final String FILE_PATH = "./data/duke.txt";
    public static final String FOLDER_PATH = "./data";

    private static File file;
    private static File folder;
    private static Parser parser;

    /**
     * Constructor. Initializes the save file and folder used in the application.
     */
    public Storage() {
        file = new File(FILE_PATH);
        folder = new File(FOLDER_PATH);
        parser = new Parser();
    }

    /**
     * Returns the save file. Creates folder if not initially present.
     *
     * @return save write file
     */
    public File load() {
        if (!folder.exists()) {
            System.out.println("Creating new folder 'data' in root directory:");
            folder.mkdir();
        }

        return file;
    }
}
