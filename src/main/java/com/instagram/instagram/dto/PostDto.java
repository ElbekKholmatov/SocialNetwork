package com.instagram.instagram.dto;

import com.instagram.instagram.domains.Location;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

/**
 * A DTO for the {@link Post} entity
 */
@Data
public class PostDto implements Serializable {
    private final Long createdBy;
    private final String caption;
    private final List<Long> documents;
    private final List<String> mentions;
    private final Location location;
    private final List<HashTagDto> hashTags;

    /**
     * A DTO for the {@link Document} entity
     */
    @Data
    public static class DocumentDto implements Serializable {
        private final Long createdBy;
        private final String originalName;
        private final String generatedName;
        private final String extension;
        private final String mimeType;
        private final Long size;
        private final String caption;
    }

    /**
     * A DTO for the {@link com.instagram.instagram.domains.auth.AuthUser} entity
     */
    @Data
    public static class AuthUserDto implements Serializable {
        private final Long id;
        private final String username;
    }

    /**
     * A DTO for the {@link com.instagram.instagram.domains.HashTag} entity
     */
    @Data
    public static class HashTagDto implements Serializable {
        private final Long id;
        private final String hashTag;
    }
}