package ru.myhlv.collectortest.brokers;

import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.entyties.Group;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CollectorResultOneMap{
    private final Map<String, Group> map = new HashMap<>();


    public Group get(Object key) {
        return map.get(key);
    }

    public void put(String key, Group value) {
        map.put(key, value);
    }

    public Set<Map.Entry<String, Group>> entrySet() {
        return map.entrySet();
    }

    public Set<String> keySet() {
        return map.keySet();
    }

    public Collection<Group> values() {
        return map.values();
    }
}
