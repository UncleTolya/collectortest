package ru.myhlv.collectortest.services.collector;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.myhlv.collectortest.brokers.CollectorResultMap;
import ru.myhlv.collectortest.entyties.Group;

import java.io.IOException;
import java.util.Iterator;

@SpringBootTest
@TestPropertySource("/application-test.properties")
class CollectorServiceTwoMapTest extends CollectorServiceTestBase {

    @Autowired
    private CollectorServiceTwoMap collectorService;

    @Autowired
    private CollectorResultMap resultMap;


    @Override
    CollectorService collectorService() {
        return collectorService;
    }

    @Override
    CollectorResultMap resultMap() {
        return resultMap;
    }

    @Test
    void collectStringsDoesNotThrow() {
        super.collectStringDoesNotThrow();
    }

    @Test
    void collectorStringsReturnRightCountOfGroups() throws IOException {
        collectStringsPositiveTest(5);
    }

    @After
    void clear() {
        Iterator<Group> itr = resultMap.keySet().iterator();
        while(itr.hasNext()) {
            resultMap.remove(itr.next());
        }
    }
}
