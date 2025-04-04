package com.salman.poi.service.repositories;

import com.salman.poi.service.entities.POI;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PoiRepository extends JpaRepository<POI, Long> {
    List<POI> findByCategoryIgnoreCase(String category);

}