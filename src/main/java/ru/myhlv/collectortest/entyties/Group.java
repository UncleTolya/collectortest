package ru.myhlv.collectortest.entyties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayList;


@RequiredArgsConstructor(staticName = "of")
public class Group implements Comparable<Group> {
    @Getter
    private final ArrayList<InputString> alikeStrings;

    @Override
    public String toString() {
        val sb = new StringBuilder();
        for (final InputString inputString : alikeStrings) {
            sb.append(inputString + "\n");
        }
        return sb.toString();
    }


    @Override
    public int compareTo(Group o) {
        return Integer.compare(alikeStrings.size(),
                o.getAlikeStrings().size());
    }
}
