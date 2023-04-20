package com.instagram.instagram.dto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Min;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
public record FollowDTO(
        @Parameter(required = true, example = "user1")
        String from,
        @Parameter(required = true, example = "user1")
        String to) {
}
