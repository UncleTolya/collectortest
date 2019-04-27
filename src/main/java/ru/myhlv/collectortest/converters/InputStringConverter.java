package ru.myhlv.collectortest.converters;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.entyties.InputString;

import java.util.Arrays;

@Component
public class  InputStringConverter implements Converter<InputString, String> {
    public InputString getModel(@NonNull final String view) {
        val values = view.split(";");
        return InputString.of(Arrays.asList(values));
    }

    public String getView(@NonNull final InputString model) {
        val sb = new StringBuffer();
        for (final String value : model.getValues()) {
            sb.append(value);
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
