package com.psamula.recruitment_task.repository;

import com.psamula.recruitment_task.model.Star;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StarDao {
    private final Map<Long, Star> starStorage = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Optional<Star> findById(Long id) {
        return Optional.ofNullable(starStorage.get(id));
    }

    public Star save(Star star) {
        if (star.getId() == null) {
            star.setId(idGenerator.incrementAndGet());
        }
        starStorage.put(star.getId(), star);
        return star;
    }

    public void deleteById(Long id) {
        starStorage.remove(id);
    }
}