package com.example.breweries.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brewery {
    @CsvBindByName
    private String id;
    @CsvBindByName
    private String address;
    @CsvBindByName
    private String categories;
    @CsvBindByName
    private String city;
    @CsvBindByName
    private String country;
    @CsvBindByName
    private String hours;
    @CsvBindByName
    private String keys;
    @CsvBindByName
    private String latitude;
    @CsvBindByName
    private String longitude;
    @CsvBindByName
    private String menus;
    @CsvBindByName
    private String name;
    @CsvBindByName
    private String postalCode;
    @CsvBindByName
    private String province;
    @CsvBindByName
    private String twitter;
    @CsvBindByName
    private String websites;

}
