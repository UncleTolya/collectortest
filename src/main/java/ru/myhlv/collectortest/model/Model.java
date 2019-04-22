package ru.myhlv.collectortest.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@Getter
public class Model {
    private final ConcurrentHashMap<String, Group> map = new ConcurrentHashMap<>();
}
