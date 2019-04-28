package ru.myhlv.collectortest.controllers;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.myhlv.collectortest.services.collector.CollectorService;
import ru.myhlv.collectortest.services.OutputFileWriter;
import ru.myhlv.collectortest.services.CollectorInformer;

import java.io.IOException;

@Controller
public class CollectorController {

    private final CollectorInformer informer;
    private final CollectorService collectorService;
    private final OutputFileWriter fileWriter;

    public CollectorController(@NonNull final CollectorInformer informer,
                               @NonNull @Qualifier("collectorServiceTwoMap") final CollectorService collectorService,
                               @NonNull final OutputFileWriter fileWriter) {
        this.informer = informer;
        this.collectorService = collectorService;
        this.fileWriter = fileWriter;
    }

    public void collectStrings() throws IOException {
        informer.runTimer();
        informer.setNameOfCollectorService(collectorService.getClass().getSimpleName());

        collectorService.collectStrings();

        informer.stopTimer();

        fileWriter.writeFile();

        informer.getStatistic();

    }
}
