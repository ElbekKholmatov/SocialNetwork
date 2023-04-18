package com.instagram.instagram.dto;

import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.User;
import org.springdoc.core.annotations.ParameterObject;

import java.io.Serializable;

@ParameterObject
public record UpdateUserDTO(
        Long id,
        String fullName,
        String bio,
        User.Gender gender) implements Serializable {
}
