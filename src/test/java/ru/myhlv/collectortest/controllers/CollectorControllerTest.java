package ru.myhlv.collectortest.controllers;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import ru.myhlv.collectortest.brokers.CollectorResultMap;
import ru.myhlv.collectortest.entyties.Group;

import java.util.Iterator;

@SpringBootTest
@TestPropertySource("/application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CollectorControllerTest extends CollectorControllerTestBase {

    @Autowired
    private CollectorController collectorController;
    @Autowired
    private CollectorResultMap resultMap;

    @Override
    CollectorController collectorController() {
        return collectorController;
    }

    @Test
    void collectStringsDoesNotThrow() {
        super.collectStringsPositiveTest();
    }

    @After
    void clear() {
        Iterator<Group> itr = resultMap.keySet().iterator();
        while (itr.hasNext()) {
            resultMap.remove(itr.next());
        }
    }
}
