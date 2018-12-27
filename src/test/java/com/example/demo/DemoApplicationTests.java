package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private final static Logger log = LoggerFactory.getLogger(DemoApplicationTests.class);

    @Test
    public void contextLoads() {
        log.info("this is atest ");
        String test = "test";
        Assert.notNull(test, "not null");
    }

}

