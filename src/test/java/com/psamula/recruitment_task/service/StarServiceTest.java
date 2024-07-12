package com.psamula.recruitment_task.service;

import com.psamula.recruitment_task.model.Star;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StarServiceTest {

    private final StarService starService = new StarService();

    @Test
    void shouldFindLimitedClosestStars() {
        // Given
        Star star1 = new Star("star 1", 2);
        Star star2 = new Star("star 2", 1);
        Star star3 = new Star("star 3", 4);
        Star star4 = new Star("star 4", 3);
        List<Star> stars = List.of(star1, star2, star3, star4);
        int size = 3;

        // When
        List<Star> starsSortedByDistance = starService.findClosestStars(stars, size);

        // Then
        assertThat(starsSortedByDistance).hasSize(size);
        assertThat(starsSortedByDistance).containsExactly(star2, star1, star4);
    }

    @Test
    void shouldGetAndSortNumberOfStarsByDistances_whenValidList() {
        // Given
        long duplicatedDistance = 500;
        Star star1 = new Star("star 1", 2);
        Star star2 = new Star("star 2", duplicatedDistance);
        Star star3 = new Star("star 3", 4);
        Star star4 = new Star("star 4", duplicatedDistance);
        List<Star> stars = List.of(star1, star2, star3, star4);

        Map<Long, Integer> expectedCountByDistanceMap = new TreeMap<>();
        expectedCountByDistanceMap.put(2L, 1);
        expectedCountByDistanceMap.put(4L, 1);
        expectedCountByDistanceMap.put(duplicatedDistance, 2);


        // When
        Map<Long, Integer> countByDistanceMap = starService.getNumberOfStarsByDistances(stars);


        // Then
        assertThat(countByDistanceMap)
                .containsExactlyEntriesOf(expectedCountByDistanceMap);
    }

    @Test
    void shouldThrowException_whenListIsEmpty() {
        List<Star> stars = new ArrayList<>();

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> starService.getNumberOfStarsByDistances(stars));

        assertThat(exception.getMessage()).isEqualTo("No stars found");
    }

    @Test
    void shouldReturnCorrectCounts_whenListHasNullValues() {
        // Given
        Star star1 = new Star("star 1", 2);
        Star star2 = new Star("star 2", 1);
        Star star3 = new Star("star 3", 4);
        Star star4 = new Star("star 4", 3);
        List<Star> stars = Arrays.asList(null, star1, null, star2, star3, star4);

        Map<Long, Integer> expectedCountByDistanceMap = new TreeMap<>();
        expectedCountByDistanceMap.put(1L, 1);
        expectedCountByDistanceMap.put(2L, 1);
        expectedCountByDistanceMap.put(3L, 1);
        expectedCountByDistanceMap.put(4L, 1);

        // When
        Map<Long, Integer> distanceToCountMap = starService.getNumberOfStarsByDistances(stars);

        // Then
        assertThat(distanceToCountMap)
                .hasSize(4)
                .containsExactlyEntriesOf(expectedCountByDistanceMap);
    }

    @Test
    void shouldReturnUniqueStars_whenListIsValid() {
        // Given
        String duplicatedStarName = "duplicatedStar";
        Star star1 = new Star("star 1", 2);
        Star star2 = new Star(duplicatedStarName, 3);
        Star star3 = new Star("star 3", 4);
        Star star4 = new Star(duplicatedStarName, 5);

        List<Star> stars = List.of(star1, star2, star3, star4);

        // When
        Collection<Star> uniqueStars = starService.getUniqueStars(stars);

        // Then
        assertThat(uniqueStars)
                .hasSize(3)
                .containsExactlyInAnyOrder(star1, star2, star3);
    }
}
