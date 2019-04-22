package ru.myhlv.collectortest.collectors;

import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.brokers.InputStringQueue;
import ru.myhlv.collectortest.model.Group;
import ru.myhlv.collectortest.model.InputString;
import ru.myhlv.collectortest.model.Model;
import ru.myhlv.collectortest.utils.ConsoleUtils;

import java.util.List;

@Component
public class InputStringCollector implements Runnable {
    private final InputStringQueue broker;
    private final Model model;

    public InputStringCollector(@NonNull final InputStringQueue broker,
                                @NonNull final Model model) {
        this.broker = broker;
        this.model = model;
    }

    @Override
    public void run() {
        var inputString = broker.get();
        while (broker.getContinueProducing()) {
            if (inputString != null) {
                distributeInputString(inputString);
                inputString = broker.get();
            }
        }
        ConsoleUtils.log(this.getClass().getSimpleName() + " finished its job");

    }

    private void distributeInputString(@NonNull final InputString inputString) {
        val values = inputString.getValues();
        val map = model.getMap();
        var owner = getOwnerOfStrings(values);
        if (owner == null) {
            owner = new Group();
        }
        setOwnerToStrings(values, owner);
        owner.getAlikeStrings().add(inputString);
    }

    private void setOwnerToStrings(@NonNull final List<String> values,
                                   @NonNull final Group group) {
        val map = model.getMap();
        for (int i = 0; i < values.size(); i++) {
            map.putIfAbsent(values.get(i), group);
        }
    }

    private Group getOwnerOfStrings(@NonNull final List<String> values) {
        val map = model.getMap();
        for (int i = 0; i < values.size(); i++) {
            if (map.containsKey(values.get(i))) {
                return map.get(values.get(i));
            }
        }
        return null;
    }
}
