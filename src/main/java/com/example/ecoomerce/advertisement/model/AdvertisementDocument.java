package com.example.ecoomerce.advertisement.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Document(indexName = "advertisements")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertisementDocument {

    public AdvertisementDocument(String title, String description, Set<String> hashtags, BigDecimal price, Long userId) {
        this.title = title;
        this.description = description;
        this.hashtags = hashtags;
        this.price = price;
        this.userId = userId;

    }

    @Id
    @GeneratedValue
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Keyword)
    private Set<String> hashtags;

    @Field(type = FieldType.Double)
    private BigDecimal price;


    @Field(type = FieldType.Keyword)
    private Long userId;
}