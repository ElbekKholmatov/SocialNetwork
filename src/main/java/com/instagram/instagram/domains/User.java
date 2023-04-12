package com.instagram.instagram.domains;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User{

    @Id
    private Long authUserId;
    private String name;
    private String bio;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Link> links;
    @OneToOne
    private Document picture;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Document> stories;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Document> posts;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Document> saved;
    public enum Gender{
        MALE, FEMALE
    }
}


















