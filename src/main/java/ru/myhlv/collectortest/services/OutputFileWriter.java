package ru.myhlv.collectortest.services;

import lombok.val;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.model.Group;
import ru.myhlv.collectortest.model.Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class OutputFileWriter {

    private final Model model;

    public OutputFileWriter(Model model) {
        this.model = model;
    }

    public void writeFile() {
        val map = model.getMap();
        val fileName = "/Users/mhlv/Documents/prog/collectortest/src/test/resources/output-data.txt";
        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            val vals = new ArrayList<Group>(map.values());
            for (int i = 0; i < vals.size(); i++) {
                writer.write(vals.get(i).toString());
                writer.newLine();
                for (int j = 0; j < vals.get(i).getAlikeStrings().size(); j++) {
                    writer.write(vals.get(i).getAlikeStrings().get(j).toString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
