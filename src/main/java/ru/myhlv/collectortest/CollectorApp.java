package ru.myhlv.collectortest;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.myhlv.collectortest.controllers.CollectorController;

import java.io.IOException;

@SpringBootApplication
public class CollectorApp {
    public static void main(String[] args) throws IOException {
        val context = SpringApplication.run(CollectorApp.class, args);
        val controller = context.getBean(CollectorController.class);
        controller.collectStrings();
    }
}
