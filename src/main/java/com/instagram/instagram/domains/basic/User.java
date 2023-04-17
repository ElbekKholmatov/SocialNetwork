package com.instagram.instagram.domains.basic;

import com.instagram.instagram.domains.Link;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User{

    @Id
    private Long authUserId;
    private String fullName;
    private String bio;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Link> links;
    @OneToOne
    private Document picture;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Document> stories;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Document> posts;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Document> saved;
    public enum Gender{
        MALE, FEMALE, NOT_GIVEN
    }
}


















