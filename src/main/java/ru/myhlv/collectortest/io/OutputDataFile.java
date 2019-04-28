package ru.myhlv.collectortest.io;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class OutputDataFile {

    private final Path outputFile;

    public OutputDataFile(@NonNull
                         @Value("#{T(java.nio.file.Paths).get('${ru.myhlv.collectortest.outputfilename}')}")
                         final Path outputFile) {
        this.outputFile = outputFile;
    }

    public Path getPath() {
        return outputFile;
    }
}
