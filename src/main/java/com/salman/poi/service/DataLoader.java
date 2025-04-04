package com.salman.poi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salman.poi.service.entities.POI;
import com.salman.poi.service.repositories.PoiRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(PoiRepository poiRepository) {
        return args -> {
            if (poiRepository.count() == 0) {
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<POI>> typeRef = new TypeReference<>() {
                };

                InputStream inputStream = getClass().getClassLoader().getResourceAsStream("pois.json");

                if (inputStream != null) {
                    List<POI> pois = mapper.readValue(inputStream, typeRef);
                    poiRepository.saveAll(pois);
                    System.out.println("Loaded initial POIs from pois.json");
                } else {
                    System.err.println("Could not find pois.json in resources!");
                }

            }

        };
    }
}