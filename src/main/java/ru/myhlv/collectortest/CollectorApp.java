package ru.myhlv.collectortest;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.myhlv.collectortest.controllers.CollectorController;

@SpringBootApplication
public class CollectorApp {
    public static void main(String[] args) {
        val context = SpringApplication.run(CollectorApp.class, args);
        val controller = context.getBean(CollectorController.class);
        controller.collectStrings();
    }
}