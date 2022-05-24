package com.example.breweries.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class BreweriesAppRunner {
    private final CsvReader csvReader;
    private final BreweryListAnalyzer breweryListAnalyzer;
    private final ResultPrinter resultPrinter;
    private final DuplicateFinder duplicateFinder;
    @Value("${path.to.csv}")
    private String PATH_TO_CSV;

    @PostConstruct
    public void printResult() {
        var breweriesOptional = csvReader.readAllFromCsv(PATH_TO_CSV);
        if (breweriesOptional.isPresent()) {
            var breweries = breweriesOptional.get();

            var breweriesPerState = breweryListAnalyzer.getCountOfBreweriesPerState(breweries);
            resultPrinter.printResult(breweriesPerState, "breweries count per state");

            var topCities = breweryListAnalyzer.getTopXCitiesForBreweries(breweries, 10);
            resultPrinter.printResult(topCities, "top 10 cities for brewery");

            var breweriesWithWebsiteCount = breweryListAnalyzer.getCountOfBreweriesWithWebsite(breweries);
            resultPrinter.printResult(breweriesWithWebsiteCount, "breweries with website count");

            var breweriesOfferingTacosInDelaware = breweryListAnalyzer.getCountOfBreweriesOfferingTacosInGivenState(breweries, "DA");
            resultPrinter.printResult(breweriesOfferingTacosInDelaware, "breweries with tacos in Delaware count");

            var breweriesWithWinePerState = breweryListAnalyzer.getPercentagesOfBreweriesWithWinePerState(breweries);
            resultPrinter.printResult(breweriesWithWinePerState, "breweries with wine percentage per state");

            var breweriesDuplicatesCount = duplicateFinder.getCountOfDuplicatesInCollection(breweries);
            resultPrinter.printResult(breweriesDuplicatesCount, "duplicate of breweries count");

        }
    }
}
