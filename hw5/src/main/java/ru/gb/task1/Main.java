package ru.gb.task1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void main(String[] args) {
        createBackup(SRC_DIR, BACKUP_DIR);
    }

    private static final String SRC_DIR = "./";
    private static final String BACKUP_DIR = "./backup";

    private static void createBackupDir(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createBackup(String source, String backup) {
        Path src = Paths.get(source);
        Path copied = Paths.get(backup);
        createBackupDir(copied);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(src)) {
            for (Path entry : directoryStream) {
                if (Files.isRegularFile(entry)) {
                    Files.copy(entry, copied.resolve(entry.getFileName()), 
                               StandardCopyOption.REPLACE_EXISTING);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
