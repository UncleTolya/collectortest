package ru.myhlv.collectortest.services.filewriter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.brokers.CollectorResultOneMap;
import ru.myhlv.collectortest.entyties.Group;
import ru.myhlv.collectortest.utils.Informer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class OutputFileWriterOneMap implements OutputFileWriter {

    private final CollectorResultOneMap resultMap;
    private final Informer informer;
    private ArrayList<Group> sortedGroups;
    private String resultFileName = "src/test/resources/output-data-onemap.txt";

    public OutputFileWriterOneMap(@NonNull final CollectorResultOneMap resultMap,
                                  @NonNull final Informer informer) {
        this.resultMap = resultMap;
        this.informer = informer;
    }

    @Override
    public ArrayList<Group> getSortedGroups() {
        if (sortedGroups == null) {
            sortedGroups = resultMap.values()
                    .stream()
                    .distinct()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return sortedGroups;
    }

    @Override
    public Informer getInformer() {
        return informer;
    }

    @Override
    public String getResultFileName() {
        return resultFileName;
    }
}
