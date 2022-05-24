package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import org.springframework.stereotype.Service;

@Service
public class MenuChecker {

    public boolean doesBreweryHaveTaco(Brewery brewery) {
        String menuString = brewery.getMenus() != null ? brewery.getMenus().toLowerCase() : "";
        String categoryString = brewery.getCategories() != null ? brewery.getCategories().toLowerCase() : "";

        return menuString.contains("taco") || menuString.contains("tacos")
                || categoryString.contains("taco") || categoryString.contains("tacos");

    }

    public boolean doesBreweryHaveWine(Brewery brewery) {
        String menuString = brewery.getMenus() != null ? brewery.getMenus().toLowerCase() : "";
        String categoryString = brewery.getCategories() != null ? brewery.getCategories().toLowerCase() : "";

        return menuString.contains("wine") || categoryString.contains("wine") || categoryString.contains("winery")
                || categoryString.contains("vineyards");

    }

}
