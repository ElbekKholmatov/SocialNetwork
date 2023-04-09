package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User{
    @Id
    private Integer authUserId;
    private String name;
    private String bio;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany
    private List<Link> links;
    @OneToOne
    private Document picture;
    @ManyToMany
    private List<Document> stories;
    @ManyToMany
    private List<Document> posts;
    @ManyToMany
    private List<Document> saved;
    public enum Gender{
        MALE, FEMALE
    }
}
