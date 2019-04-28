package ru.myhlv.collectortest.services;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectorInformer {
    private final Map<Integer, Integer> groups = new TreeMap<>(Comparator.reverseOrder());

    private Date startDate;
    private Date endDate;
    @Getter
    private int countOfStrings;
    @Setter
    private String nameOfCollectorService;

    private List<String> invalidStrings = new ArrayList<>();

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

    public void addInvalidString(@NonNull final String nextString) {
        invalidStrings.add(nextString);
    }

    public void stopTimer() {
        endDate = new Date();
    }

    public String getStatistic() {
        return nameOfCollectorServiceLine() + "\n" +
                countOfStringsLine() + "\n" +
                spendTimeLine() + "\n" +
                groupsStatLine() + "\n" +
                invalideStringsLine() + "\n";
}

    private String nameOfCollectorServiceLine() {
        return "Collector: " + nameOfCollectorService;
    }

    private String spendTimeLine() {
        try {
            return "Spend time: " + (endDate.getTime() - startDate.getTime()) + " ms";
        } catch (Exception e) {
            return "";
        }
    }

    private String groupsStatLine() {
        val sb = new StringBuilder();
        sb.append("Result file info: \n");
        sb.append("| Group size | Count \t | \n");
        for (final Map.Entry<Integer, Integer> entry : groups.entrySet()) {
            sb.append("| ")
                    .append(entry.getKey()).append(" \t\t | ")
                    .append(entry.getValue()).append("\t\t | \n");
        }
        return sb.toString();
    }

    private String countOfStringsLine() {
        return "Count of valid strings: " + countOfStrings;
    }

    private String invalideStringsLine() {
        if (invalidStrings.isEmpty()) return "";
        val sb = new StringBuilder();
        sb.append("Invalid format strings: \n");
        for (final String string : invalidStrings) {
            sb.append(string).append("\n");
        }
        return sb.toString();
    }
}
