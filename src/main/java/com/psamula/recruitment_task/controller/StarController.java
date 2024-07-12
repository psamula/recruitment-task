package com.psamula.recruitment_task.controller;

import com.psamula.recruitment_task.model.Star;
import com.psamula.recruitment_task.service.StarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stars")
@RequiredArgsConstructor
public class StarController {

    private final StarService starService;

    @GetMapping("/{id}")
    public ResponseEntity<Star> getStarById(@PathVariable Long id) {
        Star star = starService.findById(id);
        return ResponseEntity.ok().body(star);
    }

    @PostMapping
    public ResponseEntity<Star> addStar(@Valid @RequestBody Star star) {
        Star savedStar = starService.save(star);
        return new ResponseEntity<>(savedStar, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Star> updateStar(@PathVariable Long id, @Valid @RequestBody Star star) {
        Star updatedStar = starService.update(id, star);
        return new ResponseEntity<>(updatedStar, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStar(@PathVariable Long id) {
        starService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/closest")
    public ResponseEntity<List<Star>> findClosestStars(@RequestBody List<Star> stars, @RequestParam int size) {
        List<Star> closestStars = starService.findClosestStars(stars, size);
        return new ResponseEntity<>(closestStars, HttpStatus.OK);
    }

    @GetMapping("/distances")
    public ResponseEntity<Map<Long, Integer>> getNumberOfStarsByDistances(@RequestBody List<Star> stars) {
        Map<Long, Integer> distances = starService.getNumberOfStarsByDistances(stars);
        return new ResponseEntity<>(distances, HttpStatus.OK);
    }

    @GetMapping("/unique")
    public ResponseEntity<Collection<Star>> getUniqueStars(@RequestBody List<Star> stars) {
        Collection<Star> uniqueStars = starService.getUniqueStars(stars);
        return new ResponseEntity<>(uniqueStars, HttpStatus.OK);
    }
}
