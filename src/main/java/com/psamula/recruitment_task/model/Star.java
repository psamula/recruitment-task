package com.psamula.recruitment_task.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Star {
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long distance;
}
