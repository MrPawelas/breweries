package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BreweryListAnalyzerTest {
    private MenuChecker menuChecker;
    private BreweryListAnalyzer breweryListAnalyzer;

    @BeforeEach
    void init() {
        menuChecker = mock(MenuChecker.class);
    }

    @Test
    void getCountOfBreweriesPerState() {
        Brewery brewery1 = Brewery.builder().province("CA").id("1").build();
        Brewery brewery2 = Brewery.builder().province("CA").websites("www.g.com").build();
        Brewery brewery3 = Brewery.builder().province("DA").build();
        Brewery brewery4 = Brewery.builder().province("XD").build();
        List<Brewery> breweries = Arrays.asList(brewery1, brewery2, brewery3, brewery4);

        breweryListAnalyzer = new BreweryListAnalyzer(menuChecker);
        var breweriesPerState = breweryListAnalyzer.getCountOfBreweriesPerState(breweries);
        assertEquals(3, breweriesPerState.size());
        assertEquals(2, breweriesPerState.get("CA"));
        assertEquals(1, breweriesPerState.get("DA"));
        assertEquals(1, breweriesPerState.get("XD"));
    }

    @Test
    void getTopXCitiesForBreweries() {
        Brewery brewery1 = Brewery.builder().province("CA").city("york").id("1").build();
        Brewery brewery2 = Brewery.builder().province("CA").city("not-york").id("2").build();
        Brewery brewery3 = Brewery.builder().province("DA").city("york").id("4").build();
        Brewery brewery4 = Brewery.builder().province("DA").city("york").id("10").build();
        Brewery brewery5 = Brewery.builder().city("town").id("11").build();
        Brewery brewery6 = Brewery.builder().province("XD").build();
        Brewery brewery7 = Brewery.builder().province("CA").city("york").id("44").build();
        Brewery brewery8 = Brewery.builder().province("CA").city("not-york").id("6").build();
        Brewery brewery9 = Brewery.builder().province("CA").city("not-york").id("88").build();

        List<Brewery> breweries = Arrays.asList(brewery1, brewery2, brewery3, brewery4, brewery5, brewery6, brewery7, brewery8, brewery9);

        breweryListAnalyzer = new BreweryListAnalyzer(menuChecker);
        var top2cities = breweryListAnalyzer.getTopXCitiesForBreweries(breweries, 2);
        assertEquals(2, top2cities.size());
        assertEquals(2, top2cities.get("DA/york"));
        assertEquals(3, top2cities.get("CA/not-york"));
        assertNull(top2cities.get("XD"));
    }

    @Test
    void getCountOfBreweriesWithWebsite() {
        Brewery brewery1 = Brewery.builder().websites("CA.com").build();
        Brewery brewery2 = Brewery.builder().websites("CA.org").build();
        Brewery brewery3 = Brewery.builder().websites("").build();
        Brewery brewery4 = Brewery.builder().websites(null).build();
        List<Brewery> breweries = Arrays.asList(brewery1, brewery2, brewery3, brewery4);

        breweryListAnalyzer = new BreweryListAnalyzer(menuChecker);
        var breweriesWithWebsite = breweryListAnalyzer.getCountOfBreweriesWithWebsite(breweries);
        assertEquals(2, breweriesWithWebsite);
    }

    @Test
    void getCountOfBreweriesOfferingTacosInGivenState() {

        Brewery brewery1 = Brewery.builder().province("CA").menus("tacos and beer").build();
        Brewery brewery2 = Brewery.builder().province("CA").menus("only beer").build();
        Brewery brewery3 = Brewery.builder().province("CA").categories("winery").build();
        Brewery brewery4 = Brewery.builder().province("CA").categories("taco").build();
        Brewery brewery5 = Brewery.builder().province("DA").categories("taco").id("3").build();
        Brewery brewery6 = Brewery.builder().province("DA").categories("taco").id("1").build();
        Brewery brewery7 = Brewery.builder().province("XD").categories("beer").build();

        when(menuChecker.doesBreweryHaveTaco(brewery1)).thenReturn(true);
        when(menuChecker.doesBreweryHaveTaco(brewery2)).thenReturn(false);
        when(menuChecker.doesBreweryHaveTaco(brewery3)).thenReturn(false);
        when(menuChecker.doesBreweryHaveTaco(brewery4)).thenReturn(true);
        when(menuChecker.doesBreweryHaveTaco(brewery5)).thenReturn(true);
        when(menuChecker.doesBreweryHaveTaco(brewery6)).thenReturn(true);
        when(menuChecker.doesBreweryHaveTaco(brewery7)).thenReturn(false);

        List<Brewery> breweries = Arrays.asList(brewery1, brewery2, brewery3, brewery4, brewery5, brewery6, brewery7);

        breweryListAnalyzer = new BreweryListAnalyzer(menuChecker);
        var breweriesPerStateCA = breweryListAnalyzer.getCountOfBreweriesOfferingTacosInGivenState(breweries, "CA");
        var breweriesPerStateXD = breweryListAnalyzer.getCountOfBreweriesOfferingTacosInGivenState(breweries, "XD");
        var breweriesPerStateDA = breweryListAnalyzer.getCountOfBreweriesOfferingTacosInGivenState(breweries, "DA");

        assertEquals(2, breweriesPerStateCA);
        assertEquals(2, breweriesPerStateDA);
        assertEquals(0, breweriesPerStateXD);
    }

    @Test
    void getPercentagesOfBreweriesWithWinePerState() {

        Brewery brewery1 = Brewery.builder().province("CA").id("11").build();
        Brewery brewery2 = Brewery.builder().province("CA").id("1").build();
        Brewery brewery3 = Brewery.builder().province("CA").id("13").build();
        Brewery brewery4 = Brewery.builder().province("CA").id("14").build();
        Brewery brewery5 = Brewery.builder().province("DA").id("1132").build();
        Brewery brewery6 = Brewery.builder().province("DA").id("11231").build();
        Brewery brewery7 = Brewery.builder().province("XD").id("3").build();

        when(menuChecker.doesBreweryHaveWine(brewery1)).thenReturn(true);
        when(menuChecker.doesBreweryHaveWine(brewery2)).thenReturn(false);
        when(menuChecker.doesBreweryHaveWine(brewery3)).thenReturn(false);
        when(menuChecker.doesBreweryHaveWine(brewery4)).thenReturn(true);
        when(menuChecker.doesBreweryHaveWine(brewery5)).thenReturn(true);
        when(menuChecker.doesBreweryHaveWine(brewery6)).thenReturn(true);
        when(menuChecker.doesBreweryHaveWine(brewery7)).thenReturn(false);

        List<Brewery> breweries = Arrays.asList(brewery1, brewery2, brewery3, brewery4, brewery5, brewery6, brewery7);

        breweryListAnalyzer = new BreweryListAnalyzer(menuChecker);
        var breweriesWithWinePerState = breweryListAnalyzer.getPercentagesOfBreweriesWithWinePerState(breweries);

        assertEquals(3, breweriesWithWinePerState.size());
        assertEquals("50%", breweriesWithWinePerState.get("CA"));
        assertEquals("100%", breweriesWithWinePerState.get("DA"));
        assertEquals("0%", breweriesWithWinePerState.get("XD"));
    }
}