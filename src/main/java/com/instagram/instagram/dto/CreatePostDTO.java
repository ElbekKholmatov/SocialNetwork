package com.instagram.instagram.dto;

import com.instagram.instagram.domains.HashTag;
import com.instagram.instagram.domains.Location;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.instagram.instagram.domains.basic.Post} entity
 */
@Data
@Schema(name = "Post Create DTO", description = "This DTO used to create new POST")
public class CreatePostDTO implements Serializable {
    private final Long createdBy;
    private final String caption;
    private final Location location;
//    private final List<Document> document;
//    private final List<AuthUser> mentions;
//    private final List<HashTag> hashTags;
}