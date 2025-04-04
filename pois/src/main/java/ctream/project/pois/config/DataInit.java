package ctream.project.pois.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ctream.project.pois.model.entity.PointOfInterest;
import ctream.project.pois.repository.PointOfInterestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInit {
    private final ResourceLoader resourceLoader;
    private final PointOfInterestRepository poiRepository;

    @Value("${poi.init.data.path}")
    private String dataPath;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            if (poiRepository.count() == 0) {
                log.info("Initializing POI data from {}", dataPath);
                try {
                    Resource resource = resourceLoader.getResource(dataPath);
                    try (InputStream inputStream = resource.getInputStream()) {
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.registerModule(new JavaTimeModule());
                        List<PointOfInterest> pois = objectMapper.readValue(
                                inputStream,
                                new TypeReference<List<PointOfInterest>>() {}
                        );

                        pois.forEach(poi -> {
                            if (poi.getComments() != null) {
                                poi.getComments().forEach(comment -> comment.setPoi(poi));
                            }
                        });

                        poiRepository.saveAll(pois);
                        log.info("Successfully loaded {} POIs", pois.size());
                    }
                } catch (IOException e) {
                    log.error("Failed to initialize POI data", e);
                }
            } else {
                log.info("Database already contains POI data, skipping initialization");
            }
        };
    }
}
