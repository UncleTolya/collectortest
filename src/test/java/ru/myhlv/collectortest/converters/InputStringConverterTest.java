package ru.myhlv.collectortest.converters;

import lombok.NonNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.myhlv.collectortest.entyties.InputString;

import java.util.stream.Stream;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class InputStringConverterTest extends ConverterTestBase<InputString, String> {

    @Autowired
    private Converter<InputString, String> converter;

    @Override
    Converter<InputString, String> converter() {
        return converter;
    }

    @ParameterizedTest
    @MethodSource("provideValidInputStrings")
    void getModelDoesNotThrowTest(@NonNull final String inputString) {
        super.getModelDoesNotThrowTest(inputString);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidInputStrings")
    void getModelIllegalInputStringTest(@NonNull final String inputString) {
        super.getModelIllegalViewTest(inputString);
    }

    private static Stream<String> provideValidInputStrings() {
        return Stream.of(
                "\"Ф\";\"Щ\";\"Ы\"",
                "\"1\";\"2\";\"3\"",
                "\"1\";\"2\";\"\"",
                "\"\";\"2\";\"3\"",
                "\"1\";\"\";\"\"",
                "\"\";\"2\";\"\"",
                "\"\";\"\";\"3\"",
                "\"\";\"\";\"\""
        );
    }

    private static Stream<String> provideInvalidInputStrings() {
        return Stream.of(
                "\"Ф\";\"Щ\";",
                "\"1\",\"2\",\"3\"",
                "\"\";\"\";\"",
                "1;2;",
                "\"1\";2;\"3\"",
                "\"1\";",
                "\"1\"",
                ";;"
        );
    }
}
