package com.example.ecoomerce.advertisement.model;

import com.example.ecoomerce.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "advertisements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {

    public Advertisement(String title, String description, BigDecimal price, User user, Set<String> hashtags) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
        this.user = user;
        this.hashtags = hashtags;
    }

    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;

    @ElementCollection
    @CollectionTable(name = "advertisement_hashtags", joinColumns = @JoinColumn(name = "advertisement_id"))
    @Column(name = "hashtag")
    private Set<String> hashtags = new HashSet<>();

    private BigDecimal price;

    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}