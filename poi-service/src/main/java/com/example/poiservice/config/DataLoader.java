package com.example.poiservice.config;

import com.example.poiservice.model.POI;
import com.example.poiservice.repository.PoiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final PoiRepository poiRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (poiRepository.count() == 0) {
            try (InputStream inputStream = getClass().getResourceAsStream("/pois.json")) {
                List<POI> pois = objectMapper.readValue(
                        inputStream,
                        new TypeReference<>() {
                        }
                );
                poiRepository.saveAll(pois);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load POIs", e);
            }
        }
    }
}
