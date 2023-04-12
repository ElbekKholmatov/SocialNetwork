package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post extends Auditable<Long> {
    private String caption;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Document> document;
    @ManyToOne
    private Location location;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HashTag> hashTags;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Likes> likes;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Comment> comments;



}
