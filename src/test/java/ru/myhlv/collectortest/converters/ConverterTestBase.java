package ru.myhlv.collectortest.converters;

import lombok.NonNull;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class ConverterTestBase<M, V> {

    abstract Converter<M, V> converter();

    void getModelDoesNotThrowTest(@NonNull final V view) {
        assertDoesNotThrow(() -> converter().getModel(view));
    }

    void getModelIllegalViewTest(@NonNull final V view) {
        assertThrows(IllegalArgumentException.class, () -> converter().getModel(view));
    }
}
