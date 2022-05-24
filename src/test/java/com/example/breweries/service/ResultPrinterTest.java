package com.example.breweries.service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResultPrinterTest {


    @Test
    void shouldPrintCorrectResult() {
        Logger logger = (Logger) LoggerFactory.getLogger(ResultPrinter.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);

        ResultPrinter resultPrinter = new ResultPrinter();
        String title = "testing logging";
        Map<String, String> testingMap = new HashMap<>();
        testingMap.put("key", "value");

        resultPrinter.printResult(testingMap, title);

        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("-----------------", logsList.get(0)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(0)
                .getLevel());

        assertEquals(title, logsList.get(1)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(1)
                .getLevel());

        assertEquals("{key=value}", logsList.get(2)
                .getMessage());
        assertEquals(Level.INFO, logsList.get(2)
                .getLevel());
    }
}