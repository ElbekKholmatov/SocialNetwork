package com.instagram.instagram.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;
import org.springdoc.core.annotations.ParameterObject;

@ParameterObject
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnDocumentDTO{

        String originalName;
        String generatedName;
        String extension;
        String mimeType;
        Long size;
        String path;
}
