package ru.myhlv.collectortest.utils;

import lombok.NonNull;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    public static boolean fileIsValide(@NonNull final Path path) {
        return Files.exists(path) && Files.isRegularFile(path);
    }
}
