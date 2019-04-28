package ru.myhlv.collectortest.utils;

import lombok.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static boolean fileIsValid(@NonNull final Path path) {
        return Files.exists(path) && Files.isRegularFile(path);
    }

    public static Path creatFile(@NonNull final Path path) throws IOException {
        Files.deleteIfExists(path);
        Files.createFile(path);
        return path;
    }
}
