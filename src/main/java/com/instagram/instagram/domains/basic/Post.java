package com.instagram.instagram.domains.basic;

import com.instagram.instagram.domains.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "childBuilder")
public class Post extends Auditable<Long> {
    private String caption;
    private Integer likes;
    private Integer viewsCount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Document> document;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> mentions;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location location;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HashTag> hashTags;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Builder(builderMethodName = "childBuilder")
    public Post(
            Long id,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            Long createdBy,
            Long updatedBy,
            boolean deleted,
            String caption, Integer likes, Integer viewsCount, List<Document> document, Location location, List<HashTag> hashTags, List<Comment> comments, List<User> mentions) {
        super(id, createdAt, updatedAt, createdBy, updatedBy, deleted);
        this.caption = caption;
        this.likes = likes;
        this.viewsCount = viewsCount;
        this.document = document;
        this.location = location;
        this.hashTags = hashTags;
        this.comments = comments;
        this.mentions = mentions;

    }
}
