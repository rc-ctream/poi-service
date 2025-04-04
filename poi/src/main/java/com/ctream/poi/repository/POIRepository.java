package com.ctream.poi.repository;


import com.ctream.poi.model.POI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface POIRepository extends JpaRepository<POI, Long> {
    @Query("SELECT DISTINCT p.category FROM POI p")
    List<String> findDistinctCategories();
}
