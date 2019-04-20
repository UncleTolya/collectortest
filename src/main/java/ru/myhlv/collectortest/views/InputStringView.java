package ru.myhlv.collectortest.views;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor(staticName = "of")
@Component
public class InputStringView {
    private final String string;
}
