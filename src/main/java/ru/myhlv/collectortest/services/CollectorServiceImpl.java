package ru.myhlv.collectortest.services;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.myhlv.collectortest.views.InputStringView;

import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class CollectorServiceImpl implements CollectorService {
    private final ConcurrentLinkedQueue<InputStringView> queue;

    public CollectorServiceImpl(ConcurrentLinkedQueue<InputStringView> queue) {
        this.queue = queue;
    }

    public void collectStrings(@NonNull final String fileName) {
        //check validation file

    }

    private void readStringsToQueue() {

    }
}
