package com.example.breweries.service;

import com.example.breweries.model.Brewery;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CsvReader {

    public Optional<List<Brewery>> readAllFromCsv(String pathToCsvFile) {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(pathToCsvFile))
        ) {
            CsvToBean<Brewery> breweryCsvToBean = new CsvToBeanBuilder<Brewery>(reader)
                    .withType(Brewery.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withEscapeChar('\0')
                    .withIgnoreEmptyLine(true)
                    .build();

            List<Brewery> breweries = breweryCsvToBean.parse();
            log.info("read info about " + breweries.size() + "breweries");
            return Optional.of(breweries);
        } catch (Exception e) {
            log.error("exception during reading csv");
            log.error(e.toString());
            log.error(e.getMessage());
            log.error(e.fillInStackTrace().toString());

        }
        return Optional.empty();
    }
}
