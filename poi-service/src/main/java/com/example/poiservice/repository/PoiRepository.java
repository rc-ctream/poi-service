package com.example.poiservice.repository;

import com.example.poiservice.model.POI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PoiRepository extends JpaRepository<POI, UUID>
{
    @Query("SELECT DISTINCT p.category FROM POI p")
    List<String> findDistinctCategories();

    List<POI> findByCategoryIgnoreCase(String category);
}
