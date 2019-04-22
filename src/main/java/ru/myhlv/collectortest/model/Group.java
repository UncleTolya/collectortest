package ru.myhlv.collectortest.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Group {
    private final ArrayList<InputString> alikeStrings = new ArrayList<>();
}
