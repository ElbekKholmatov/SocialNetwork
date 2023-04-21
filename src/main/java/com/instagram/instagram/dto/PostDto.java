package com.instagram.instagram.dto;

import com.instagram.instagram.domains.Location;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * A DTO for the {@link Post} entity
 */
@Data
@Schema(name = "Post Create DTO", description = "This DTO used to create new POST")
public class PostDto implements Serializable {
    private final String caption;
    private final List<Long> documents;
    private final List<String> mentions;
    private final Long location;
    private final List<String> hashTags;
}