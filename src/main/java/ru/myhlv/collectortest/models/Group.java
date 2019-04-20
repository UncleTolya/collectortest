package ru.myhlv.collectortest.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CopyOnWriteArrayList;

@Data
@RequiredArgsConstructor(staticName = "of")
public class Group {
    private final CopyOnWriteArrayList<InputString> alikeStrings;
}