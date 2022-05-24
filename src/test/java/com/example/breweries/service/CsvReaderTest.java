package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvReaderTest {
    String path = "src/test/resources/breweries_small.csv";
    CsvReader csvReader;

    @BeforeEach
    void init() {
        csvReader = new CsvReader();
    }

    @Test
    void shouldReadFromCsv() {
        var breweryList = csvReader.readAllFromCsv(path);
        assertTrue(breweryList.isPresent());
        assertEquals(21, breweryList.get().size());
        assertTrue(breweryList.get().contains(Brewery.builder()
                .id("AVweN1T_ByjofQCxwSPL")
                .address("Syr Airport")
                .categories("Brewery")
                .city("Syracuse")
                .country("US")
                .hours("")
                .keys("us/ny/syracuse/syrairport/2098450797")
                .latitude("")
                .longitude("")
                .menus("")
                .name("Adirondack Pub")
                .postalCode("")
                .province("NY")
                .twitter("")
                .websites("")
                .build()));
    }
}