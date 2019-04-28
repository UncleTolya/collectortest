package ru.myhlv.collectortest.services;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.brokers.CollectorResultMap;
import ru.myhlv.collectortest.entyties.Group;
import ru.myhlv.collectortest.io.OutputDataFile;
import ru.myhlv.collectortest.utils.FileUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Component
public class OutputFileWriter {

    private final CollectorResultMap resultMap;
    private final CollectorInformer informer;
    private final OutputDataFile outputFile;
    private ArrayList<Group> sortedGroups;

    public OutputFileWriter(@NonNull final CollectorResultMap resultMap,
                            @NonNull final CollectorInformer informer,
                            @NonNull final OutputDataFile outputFile) {
        this.resultMap = resultMap;
        this.informer = informer;
        this.outputFile = outputFile;
    }

    public void writeFile() throws IOException {
        val resultFile = FileUtils.creatFile(outputFile.getPath()).toFile();
        val tempFile = Files.createTempFile("temp-", "t").toFile();

        writeDataToTempFile(tempFile);
        addStatisticToFile(tempFile, resultFile);
    }

    private void addStatisticToFile(@NonNull final File tempFile,
                                    @NonNull final File resultFile) {
        try (val writer = new BufferedWriter(new FileWriter(resultFile));
             val reader = new BufferedReader(new FileReader(tempFile))) {
            writer.write(informer.getStatistic());
            while (reader.ready()) {
                writer.write(reader.readLine() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeDataToTempFile(@NonNull final File tempFile) {
        int groupCounter = 0;
        try (val writer = new BufferedWriter(new FileWriter(tempFile))) {
            for (final Group group : getSortedGroups()) {
                informer.incrGroup(group.getAlikeStrings().size());
                writer.write("Группа " + ++groupCounter + "\n");
                writer.write(group.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Group> getSortedGroups() {
        if (sortedGroups == null) {
            return resultMap.keySet().stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        return sortedGroups;
    }
}
