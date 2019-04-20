package ru.myhlv.collectortest.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class InputString {
    private final String col0;
    private final String col1;
    private final String col2;
}
