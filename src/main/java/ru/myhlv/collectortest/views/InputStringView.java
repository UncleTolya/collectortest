package ru.myhlv.collectortest.views;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class InputStringView {
    private final String string;
}
