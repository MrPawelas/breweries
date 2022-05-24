package com.example.breweries.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ResultPrinter {
    public void printResult(Object objectToPrint, String title) {
        log.info("-----------------");
        log.info(title);
        log.info(objectToPrint.toString());
    }
}
