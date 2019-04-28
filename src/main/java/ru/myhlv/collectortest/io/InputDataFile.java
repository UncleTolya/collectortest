package ru.myhlv.collectortest.io;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class InputDataFile {

    private final Path inputFile;

    public InputDataFile(@NonNull
                         @Value("#{T(java.nio.file.Paths).get('${ru.myhlv.collectortest.inputfilename}')}")
                         final Path inputFile) {
        this.inputFile = inputFile;
    }

    public Path getPath() {
        return inputFile;
    }
}
