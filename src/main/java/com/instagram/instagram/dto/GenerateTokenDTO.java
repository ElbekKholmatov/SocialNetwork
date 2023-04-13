package com.instagram.instagram.dto;

import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
public record GenerateTokenDTO(String username, String password) {
}
