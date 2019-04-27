package ru.myhlv.collectortest.services.filewriter;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.brokers.CollectorResultTwoMap;
import ru.myhlv.collectortest.entyties.Group;
import ru.myhlv.collectortest.utils.Informer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class OutputFileWriterTwoMap implements OutputFileWriter {

    private final CollectorResultTwoMap resultMap;
    private final Informer informer;
    private ArrayList<Group> sortedGroups;
    private String resultFileName = "src/test/resources/output-data-twomap.txt";

    public OutputFileWriterTwoMap(@NonNull final CollectorResultTwoMap resultMap,
                                  @NonNull final Informer informer) {
        this.resultMap = resultMap;
        this.informer = informer;
    }

    @Override
    public ArrayList<Group> getSortedGroups() {
        if (sortedGroups == null) {
            sortedGroups = resultMap.keySet()
                    .stream()
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
