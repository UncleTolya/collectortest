package ru.myhlv.collectortest.services.collector;

import lombok.NonNull;
import org.springframework.test.annotation.DirtiesContext;
import ru.myhlv.collectortest.brokers.CollectorResultMap;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

abstract class CollectorServiceTestBase {

    abstract CollectorService collectorService();

    abstract CollectorResultMap resultMap();


    void collectStringDoesNotThrow() {
        assertDoesNotThrow(() -> collectorService().collectStrings());
    }

    void collectStringsPositiveTest(@NonNull final int expected) throws IOException {
        collectorService().collectStrings();
        assertEquals(expected, resultMap().keySet().size());
    }
}