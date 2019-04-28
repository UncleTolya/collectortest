package ru.myhlv.collectortest.utils;

import lombok.NonNull;

import java.util.logging.Logger;

public class ConsoleUtils {
    private static final Logger logger = Logger.getLogger("Collector Logger");

    public static void log(@NonNull String message) {
        logger.info(message);
    }
}
