package ru.myhlv.collectortest.brokers;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.entyties.Group;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CollectorResultTwoMap{
    private final Map<Group, Set<String>> map
            = new HashMap<>();

    public Set<String> get(@NonNull final Group key) {
        return map.get(key);
    }

    public void  put(@NonNull final Group key,
                           @NonNull final Set<String> value) {
        map.put(key, value);
    }

    public void remove(@NonNull final Group key) {
        map.remove(key);
    }

    public Set<Group> keySet() {
        return map.keySet();
    }
}
