package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuCheckerTest {

    @Test
    void shouldReturnTrueWhenBreweryHasTacoInMenuOrInCategory() {
        MenuChecker menuChecker = new MenuChecker();
        Brewery brewery1 = Brewery.builder().menus("tacos and beer").build();
        Brewery brewery2 = Brewery.builder().menus("only beer").build();
        Brewery brewery3 = Brewery.builder().categories("winery").build();
        Brewery brewery4 = Brewery.builder().categories("taco").build();

        assertTrue(menuChecker.doesBreweryHaveTaco(brewery1));
        assertFalse(menuChecker.doesBreweryHaveTaco(brewery2));
        assertFalse(menuChecker.doesBreweryHaveTaco(brewery3));
        assertTrue(menuChecker.doesBreweryHaveTaco(brewery4));
    }

    @Test
    void shouldReturnTrueWhenBreweryHasWineInMenuOrInCategory() {
        MenuChecker menuChecker = new MenuChecker();
        Brewery brewery1 = Brewery.builder().categories("brewery").menus("wine and beer").build();
        Brewery brewery2 = Brewery.builder().categories("brewery").menus("only beer").build();
        Brewery brewery3 = Brewery.builder().categories("brewery").categories("brewery").build();
        Brewery brewery4 = Brewery.builder().categories("brewery").categories("winery").build();

        assertTrue(menuChecker.doesBreweryHaveWine(brewery1));
        assertFalse(menuChecker.doesBreweryHaveWine(brewery2));
        assertFalse(menuChecker.doesBreweryHaveWine(brewery3));
        assertTrue(menuChecker.doesBreweryHaveWine(brewery4));
    }
}