package ru.myhlv.collectortest.services.filewriter;

import lombok.val;
import ru.myhlv.collectortest.entyties.Group;
import ru.myhlv.collectortest.utils.Informer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public interface OutputFileWriter {
    default void writeFile() throws IOException {
        Path resultFile = Paths.get(getResultFileName());
        Files.deleteIfExists(resultFile);
        Files.createFile(resultFile);
        val tempFile = Files.createTempFile("temp-", "t");

        long groupCounter = 0;
        try (val writer = new BufferedWriter(new FileWriter(tempFile.toFile()))) {
            for (final Group group : getSortedGroups()) {
                getInformer().incrGroup(group.getAlikeStrings().size());
                writer.write("Группа " + groupCounter + "\n");
                writer.write(group.toString());
                groupCounter = groupCounter + 1;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (val writer = new BufferedWriter(new FileWriter(resultFile.toFile()));
             val reader = new BufferedReader(new FileReader(tempFile.toFile()))) {
            writer.write(getInformer().getStatistic());
            while (reader.ready()) {
                writer.write(reader.readLine() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Group> getSortedGroups();

    Informer getInformer();

    String getResultFileName();
}
