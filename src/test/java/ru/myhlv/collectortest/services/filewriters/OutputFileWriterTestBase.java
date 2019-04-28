package ru.myhlv.collectortest.services.filewriters;

import lombok.NonNull;
import ru.myhlv.collectortest.services.OutputFileWriter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

abstract class OutputFileWriterTestBase {

    abstract OutputFileWriter outputFileWriter();

    void writeFileDoesNotThrow() {
        assertDoesNotThrow(() -> outputFileWriter().writeFile());
    }

    void outputFileWriterPositiveTest(@NonNull final Path resultFile) throws IOException {
        assertTrue(Files.exists(resultFile));
        assertTrue(Files.size(resultFile) > 0);
    }
}
