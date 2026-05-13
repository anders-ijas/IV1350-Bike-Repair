package se.kth.iv1350.bikerepair.integration;

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

    /**
     * Logs messages to log.txt
     * @param message The message to add to the log.
     */
    public static void log(String message) {
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
