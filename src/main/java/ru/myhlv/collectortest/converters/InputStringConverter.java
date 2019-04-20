package ru.myhlv.collectortest.converters;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.models.InputString;
import ru.myhlv.collectortest.views.InputStringView;

@Component
public class InputStringConverter implements Converter<InputString, InputStringView> {
    public InputString getModel(@NonNull final InputStringView view) {
        val values = view.getString().split(";");
        return InputString.of(values[0], values[1], values[2]);
    }

    public InputStringView getView(@NonNull final InputString model) {
        val col0 = model.getCol0();
        val col1 = model.getCol1();
        val col2 = model.getCol2();
        return InputStringView.of(col0 + ";" + col1 + ";" + col2);
    }
}
