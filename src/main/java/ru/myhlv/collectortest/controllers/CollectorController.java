package ru.myhlv.collectortest.controllers;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.myhlv.collectortest.services.CollectorService;

@Data
@Controller
public class CollectorController {

    private final CollectorService collectorService;

    public CollectorController(@Qualifier("collectorServiceImpl") CollectorService collectorService) {
        this.collectorService = collectorService;
    }

    public void collectStrings(@NonNull final String fileName) {
        collectorService.collectStrings(fileName);
    }

    public void collectStrings() {
        val fileName = "fileName";
        collectorService.collectStrings(fileName);
    }
}
