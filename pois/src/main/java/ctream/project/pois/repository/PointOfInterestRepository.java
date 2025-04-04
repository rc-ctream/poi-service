package ctream.project.pois.repository;

import ctream.project.pois.model.entity.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer> {
    List<PointOfInterest> findByCategory(String category);

    @Query("SELECT DISTINCT p.category FROM PointOfInterest p")
    List<String> findAllCategories();
}
