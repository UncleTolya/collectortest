package ru.myhlv.collectortest.utils;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Informer {
    private final Map<Integer, Integer> groups = new TreeMap<>((a, b) -> b - a);

    private Date startDate;
    private Date endDate;
    @Getter
    private int countOfStrings;
    @Setter
    private String nameOfCollectorService;

    public void runTimer() {
        startDate = new Date();
    }

    public void incStringsCounter() {
        countOfStrings++;
    }

    public void incrGroup(@NonNull final Integer group) {
        if (groups.containsKey(group)) {
            groups.put(group, groups.get(group) + 1);
        } else {
            groups.put(group, 1);
        }
    }

    public void stopTimer() {
        endDate = new Date();
    }

    public String getStatistic() {
        val sb = new StringBuilder();

        sb.append(nameOfCollectorServiceLine() + "\n");
        sb.append(countOfStringsLine() + "\n");
        sb.append(spendTimeLine() + "\n");
        sb.append(groupsStatLine() + "\n");
        return sb.toString();
    }

    private String nameOfCollectorServiceLine() {
        return "Collector - " + nameOfCollectorService;
    }

    private String spendTimeLine() {
        return "Spend time - " + (endDate.getTime() - startDate.getTime()) + " ms";
    }

    private String groupsStatLine() {
        val sb = new StringBuilder();
        sb.append("Result file info:\n");
        sb.append("| Group size | Count \t | \n");
        for (final Map.Entry<Integer, Integer> entry : groups.entrySet()) {
            sb.append("| " + entry.getKey() + " \t\t | " + entry.getValue() + "\t\t | \n");
        }
        return sb.toString();
    }

    private String countOfStringsLine() {
        return "Count of strings - " + countOfStrings;
    }

    public String processedInfo() {
        Date date2 = new Date();
        int stringProcessed = countOfStrings / (1000000 / 100);
        long timeSpend = date2.getTime() - startDate.getTime();
        return "Strings processed " + countOfStrings + " " + stringProcessed + " %. "
                + "Spend time " + ((timeSpend / 1000)/ 60) + " min. ";
    }
}
