package ru.myhlv.collectortest.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public abstract class CollectorControllerTestBase {

    abstract CollectorController collectorController();

    public void collectStringsPositiveTest() {
        assertDoesNotThrow(() -> collectorController().collectStrings());
    }
}
