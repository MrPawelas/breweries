package com.example.breweries.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@Service
@Slf4j
public class DuplicateFinder {
    //it depends on what we consider a duplicate - if you put both exact records in csv it will work
    //I need more info to consider something a duplicate that has different values for some columns but same values for some columns
    public long getCountOfDuplicatesInCollection(List<?> list) {
        log.info("-----------------");
        log.info("total count = " + list.size());
        List<?> listWithoutDuplicates = new ArrayList<>(
                new LinkedHashSet<>(list));
        log.info("distinct count = " + listWithoutDuplicates.size());
        return list.size() - listWithoutDuplicates.size();
    }
}
