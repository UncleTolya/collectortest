package ru.myhlv.collectortest.services.filewriters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.myhlv.collectortest.io.OutputDataFile;
import ru.myhlv.collectortest.services.OutputFileWriter;

import java.io.IOException;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class OutputFileWriterTest extends OutputFileWriterTestBase {
    @Autowired
    private OutputFileWriter outputFileWriter;
    @Autowired
    private OutputDataFile outputDataFile;


    @Override
    OutputFileWriter outputFileWriter() {
        return outputFileWriter;
    }

    @Test
    void writeFileDoesNotThrow() {
        super.writeFileDoesNotThrow();
    }

    @Test
    void fileWasCreated() throws IOException {
        super.outputFileWriterPositiveTest(outputDataFile.getPath());
    }
}
