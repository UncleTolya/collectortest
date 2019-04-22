package ru.myhlv.collectortest.services;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.myhlv.collectortest.brokers.InputStringQueue;
import ru.myhlv.collectortest.collectors.InputStringCollector;
import ru.myhlv.collectortest.model.Model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Service
public class CollectorServiceImpl implements CollectorService {
    private final int NTHREADS = 100;
    private final ExecutorService executorService = Executors.newFixedThreadPool(NTHREADS);

    private final Model map;
    private final InputFileReader inputFileReader;
    private final InputStringQueue broker;
    private final InputStringCollector collector;
    private final OutputFileWriter outputFileWriter;

    public CollectorServiceImpl(@NonNull final Model map,
                                @NonNull final InputFileReader inputFileReader,
                                @NonNull final InputStringQueue broker,
                                @NonNull final InputStringCollector collector,
                                @NonNull final OutputFileWriter outputFileWriter) {
        this.map = map;
        this.inputFileReader = inputFileReader;
        this.broker = broker;
        this.collector = collector;
        this.outputFileWriter = outputFileWriter;
    }

    public void collectStrings() {
        runCollector();
        readStringsToQueue();
        outputFileWriter.writeFile();
        executorService.shutdown();
    }

    private void readStringsToQueue() {
        try {
            val producerStatus = executorService.submit(inputFileReader);
            producerStatus.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void runCollector() {
        executorService.execute(collector);
    }
}
