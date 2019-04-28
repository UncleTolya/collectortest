package ru.myhlv.collectortest.entyties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.ArrayList;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (o == null) return false;
        return this == o;
    }

    @Override
    public int hashCode() {
        return Objects.hash(alikeStrings);
    }

    @Override
    public int compareTo(Group o) {
        return Integer.compare(alikeStrings.size(),
                o.getAlikeStrings().size());
    }
}
