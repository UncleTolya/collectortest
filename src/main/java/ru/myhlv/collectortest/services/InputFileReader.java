package ru.myhlv.collectortest.services;

import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.brokers.InputStringQueue;
import ru.myhlv.collectortest.converters.InputStringConverter;
import ru.myhlv.collectortest.inputdata.InputData;
import ru.myhlv.collectortest.utils.ConsoleUtils;
import ru.myhlv.collectortest.views.InputStringView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class InputFileReader implements Runnable {

    private final InputStringQueue broker;
    private final InputData inputDataFile;
    private final InputStringConverter converter;

    public InputFileReader(@NonNull @Qualifier("inputDataFile") final InputData inputDataFile,
                           @NonNull final InputStringQueue broker,
                           @NonNull final InputStringConverter converter) {
        this.broker = broker;
        this.inputDataFile = inputDataFile;
        this.converter = converter;
    }

    @Override
    public void run() {
        val file = (Path) inputDataFile.getData();
        try (val br = new BufferedReader(new InputStreamReader(Files.newInputStream(file)))) {
            while (br.ready()) {
                val inputStringView = InputStringView.of(br.readLine());
                broker.put(converter.getModel(inputStringView));
            }
            this.broker.stopProducing();
            ConsoleUtils.log(this.getClass().getSimpleName() + " finished its job.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
