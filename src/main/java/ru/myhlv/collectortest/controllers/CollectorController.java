package ru.myhlv.collectortest.controllers;

import lombok.Data;
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

    public void collectStrings() {
        collectorService.collectStrings();
    }
}
