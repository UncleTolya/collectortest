package ru.myhlv.collectortest.inputdata;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class InputDataFile implements InputData {

    private final Path inputData;

    public InputDataFile(
            @NonNull
            @Value("#{T(java.nio.file.Paths).get('/Users/mhlv/Documents/prog/collectortest/src/main/resources/input-data.csv')}")
            final Path inputData) {
        this.inputData = inputData;
    }

    @Override
    public Path getData() {
        return inputData;
    }
}
