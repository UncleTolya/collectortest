package ru.myhlv.collectortest.converters;

public interface Converter<M, V> {
    M getModel(V view) throws IllegalArgumentException;
    V getView(M model);
}
