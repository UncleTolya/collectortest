package ru.myhlv.collectortest.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor(staticName = "of")
public class InputString {
    private final List<String> values;
}
