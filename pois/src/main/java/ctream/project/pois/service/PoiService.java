package ctream.project.pois.service;

import ctream.project.pois.exception.ResourceNotFoundException;
import ctream.project.pois.model.dto.CommentRequestDto;
import ctream.project.pois.model.entity.Comment;
import ctream.project.pois.model.entity.PointOfInterest;
import ctream.project.pois.repository.PointOfInterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PoiService {
    private final PointOfInterestRepository poiRepository;

    public List<PointOfInterest> getAllPois() {
        return poiRepository.findAll();
    }

    public List<PointOfInterest> getPoisByCategory(String category) {
        return poiRepository.findByCategory(category);
    }

    public List<String> getAllCategories() {
        return poiRepository.findAllCategories();
    }

    public PointOfInterest getPoiById(Integer id) {
        return poiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("POI not found with id: " + id));
    }

    @Transactional
    public PointOfInterest likePoi(Integer id) {
        PointOfInterest poi = getPoiById(id);
        poi.setLikes(poi.getLikes() + 1);
        return poiRepository.save(poi);
    }

    @Transactional
    public PointOfInterest dislikePoi(Integer id) {
        PointOfInterest poi = getPoiById(id);
        poi.setDislikes(poi.getDislikes() + 1);
        return poiRepository.save(poi);
    }

    @Transactional
    public PointOfInterest addComment(Integer id, CommentRequestDto commentRequest) {
        PointOfInterest poi = getPoiById(id);

        Comment comment = new Comment();
        comment.setAuthor(commentRequest.getAuthor());
        comment.setMessage(commentRequest.getMessage());
        comment.setTimestamp(LocalDateTime.now());

        poi.addComment(comment);
        return poiRepository.save(poi);
    }
}
