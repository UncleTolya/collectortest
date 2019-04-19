package ru.myhlv.collectortest.services;

import lombok.NonNull;
import lombok.val;
import ru.myhlv.collectortest.domains.InputString;

public class InputStringParser {
    public static InputString parse(@NonNull final String string) {
        val values = string.split("\\D");
        val val0 = Long.parseLong(values[0]);
        val val1 = Long.parseLong(values[1]);
        val val2 = Long.parseLong(values[2]);
        return InputString.of(val0, val1, val2);
    }
}
