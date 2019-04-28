package ru.myhlv.collectortest.entyties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@EqualsAndHashCode
@RequiredArgsConstructor(staticName = "of")
public class InputString {
    @Getter
    private final List<String> values;

    @Override
    public String toString() {
        val sb = new StringBuilder();
        for (final String string : values) {
            sb.append(string + ";");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
