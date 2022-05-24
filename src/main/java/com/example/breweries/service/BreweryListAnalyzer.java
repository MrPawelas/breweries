package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BreweryListAnalyzer {
    private final MenuChecker menuChecker;

    public Map<String, Long> getCountOfBreweriesPerState(List<Brewery> breweries) {
        //I assume that province = state
        return breweries.stream()
                .collect(Collectors.groupingBy(Brewery::getProvince, Collectors.counting()));
    }

    public Map<String, Long> getTopXCitiesForBreweries(List<Brewery> breweries, long numberOfTopCities) {
        // I assume that top city is the city with most breweries in it
        Map<String, Long> topBreweries = new LinkedHashMap<>();
        breweries.stream()
                .collect(Collectors.groupingBy(this::combineCityWithState, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberOfTopCities)
                .forEachOrdered(x -> topBreweries.put(x.getKey(), x.getValue()));
        return topBreweries;
    }

    public Long getCountOfBreweriesWithWebsite(List<Brewery> breweries) {
        return breweries.stream()
                .filter(x -> x.getWebsites() != null && !x.getWebsites().isEmpty() && !x.getWebsites().isBlank())
                .count();
    }

    public Long getCountOfBreweriesOfferingTacosInGivenState(List<Brewery> breweries, String state) {
        return breweries.stream()
                .filter(x -> x.getProvince().equalsIgnoreCase(state))
                .filter(menuChecker::doesBreweryHaveTaco)
                .count();
    }

    public Map<String, String> getPercentagesOfBreweriesWithWinePerState(List<Brewery> breweries) {
        var breweriesPerState = getCountOfBreweriesPerState(breweries);
        var breweriesPerStateOfferingWine = breweries
                .stream()
                .filter(menuChecker::doesBreweryHaveWine).toList()
                .stream()
                .collect(Collectors.groupingBy(Brewery::getProvince, Collectors.counting()));

        Map<String, String> breweriesPercentageWithWinePerState = new LinkedHashMap<>();
        DecimalFormat df = new DecimalFormat("##.##%");

        for (String key : breweriesPerState.keySet()) {
            Long allBreweriesInState = breweriesPerState.get(key);
            Long onlyWithWineInstate = breweriesPerStateOfferingWine.getOrDefault(key, 0L);
            double percentage = ((double) onlyWithWineInstate / allBreweriesInState);
            String formattedPercentage = df.format(percentage);
            breweriesPercentageWithWinePerState.put(key, formattedPercentage);
        }
        return breweriesPercentageWithWinePerState;
    }

    private String combineCityWithState(Brewery brewery) {
        String city = brewery.getCity() != null ? brewery.getCity() : "empty city";
        String state = brewery.getProvince() != null ? brewery.getProvince() : "empty province";
        return state + "/" + city;
    }
}
