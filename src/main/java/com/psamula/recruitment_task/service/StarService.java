package com.psamula.recruitment_task.service;

import com.psamula.recruitment_task.model.Star;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StarService {

    public List<Star> findClosestStars(List<Star> stars, int size) {
        if (CollectionUtils.isEmpty(stars)) {
            throw new RuntimeException("No stars found");
        }

        return stars.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingLong(Star::getDistance))
                .limit(size)
                .toList();
    }

    public Map<Long, Integer> getNumberOfStarsByDistances(List<Star> stars) {
        if (CollectionUtils.isEmpty(stars)) {
            throw new RuntimeException("No stars found");
        }

        return stars.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(
                        Star::getDistance,
                        TreeMap::new,
                        Collectors.collectingAndThen(Collectors.counting(), Long::intValue)
                ));
    }

    public Collection<Star> getUniqueStars(Collection<Star> stars) {
        if (CollectionUtils.isEmpty(stars)) {
            throw new RuntimeException("No stars found");
        }

        return stars.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(
                        Star::getName,
                        star -> star,
                        (existing, replacement) -> existing))
                .values();
    }
}
