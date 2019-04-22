package ru.myhlv.collectortest.brokers;

import lombok.NonNull;
import org.springframework.stereotype.Component;
import ru.myhlv.collectortest.model.InputString;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class InputStringQueue {
    private final Integer NTHREADS = 100;
    private final BlockingQueue<InputString> queue
            = new LinkedBlockingQueue<>(NTHREADS);
    private boolean continueProducing = true;

    public void put(@NonNull final InputString inputString) throws InterruptedException {
        this.queue.put(inputString);
    }

    public InputString get() {
        return this.queue.poll();
    }

    public synchronized boolean getContinueProducing() {
        return continueProducing;
    }

    public synchronized void stopProducing() {
        this.continueProducing = false;
    }
}
