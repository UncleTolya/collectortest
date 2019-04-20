package ru.myhlv.collectortest.converters;

public interface Converter<M, V> {
    M getModel(V view);
    V getView(M model);
}
