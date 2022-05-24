package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DuplicateFinderTest {

    @Test
    void shouldReturnCountOfDuplicatesInCollection() {
        DuplicateFinder duplicateFinder = new DuplicateFinder();
        Brewery brewery1 = Brewery.builder().name("unique").id("456").build();
        Brewery brewery2 = Brewery.builder().name("duplicate").build();
        Brewery brewery3 = Brewery.builder().name("duplicate").build();
        Brewery brewery4 = Brewery.builder().name("duplicate").build();
        Brewery brewery5 = Brewery.builder().name("unique").id("123").build();
        Brewery brewery6 = Brewery.builder().name("duplicate2").id("123").build();
        Brewery brewery7 = Brewery.builder().name("duplicate2").id("123").build();
        List<Brewery> breweries = Arrays.asList(brewery1, brewery2, brewery3, brewery4, brewery5, brewery6, brewery7);
        var count = duplicateFinder.getCountOfDuplicatesInCollection(breweries);
        assertEquals(3, count);
    }
}