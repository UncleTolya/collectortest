package ru.myhlv.collectortest.entyties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of")
public class InputString {
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
