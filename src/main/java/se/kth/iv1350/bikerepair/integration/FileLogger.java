package se.kth.iv1350.bikerepair.integration;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Enables logging to file. Log.txt stores the log written.
 */
public class FileLogger {
    private static final FileLogger INSTANCE = new FileLogger();

    /**
     * Creates the FileLogger, uses pattern singleton.
     */
    private FileLogger() {}

    /**
     * Returns the only instance of FileLogger singleton.
     * @return FileLogger
     */
    public static FileLogger getInstance() {
        return INSTANCE;
    }

    /**
     * Logs messages to log.txt
     * @param message The message to add to the log.
     */
    public void log(String message) {
        String logEntry = message + "Time: "+ LocalDateTime.now() + "\n";
        try {
            Files.write(
                    Paths.get("log.txt"),
                    logEntry.getBytes(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
