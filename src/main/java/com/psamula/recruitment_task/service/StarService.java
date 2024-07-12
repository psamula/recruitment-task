package com.psamula.recruitment_task.service;

import com.psamula.recruitment_task.model.Star;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StarService {

    Star findById(Long id);

    Star update(Long id, Star star);

    Star save(Star star);

    void deleteById(Long id);

    List<Star> findClosestStars(List<Star> stars, int size);

    Collection<Star> getUniqueStars(Collection<Star> stars);

    Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars);
}
