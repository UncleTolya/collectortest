package ru.myhlv.collectortest.controllers;

import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.myhlv.collectortest.services.collector.CollectorService;
import ru.myhlv.collectortest.services.filewriter.OutputFileWriter;
import ru.myhlv.collectortest.utils.Informer;

import java.io.IOException;

@Data
@Controller
public class CollectorController {

    private final Informer informer;
    private final CollectorService collectorService;
    private final OutputFileWriter fileWriter;

    public CollectorController(@NonNull final Informer informer,
//                               @NonNull @Qualifier("collectorServiceTwoMap") final CollectorService collectorService,
//                               @NonNull @Qualifier("outputFileWriterTwoMap") final OutputFileWriter fileWriter)
                                @NonNull @Qualifier("collectorServiceOneMap") final CollectorService collectorService,
                                @NonNull @Qualifier("outputFileWriterOneMap") final OutputFileWriter fileWriter)
                                {
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
