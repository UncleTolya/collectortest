package ru.myhlv.collectortest.domains;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class InputString {
    private final long val0;
    private final long val1;
    private final long val2;

    @Override
    public String toString() {
        return "\"" + val0 + "\"" + ";" +
                "\"" + val1 + "\"" + ";" +
                 "\"" + val2 + "\"";
    }
}
