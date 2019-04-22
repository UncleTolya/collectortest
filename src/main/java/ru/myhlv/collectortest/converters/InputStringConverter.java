package ru.myhlv.collectortest.converters;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.model.InputString;
import ru.myhlv.collectortest.views.InputStringView;

import java.util.Arrays;

@Component
public class InputStringConverter implements Converter<InputString, InputStringView> {
    public InputString getModel(@NonNull final InputStringView view) {
        val values = view.getString().split(";");
        return InputString.of(Arrays.asList(values));
    }

    public InputStringView getView(@NonNull final InputString model) {
        val sb = new StringBuffer();
        for (final String value : model.getValues()) {
            sb.append(value);
            sb.append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        return InputStringView.of(sb.toString());
    }
}
