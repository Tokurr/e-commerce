package com.example.ecoomerce.advertisement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName = "advertisement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertisement {
    public Advertisement(String title, String description, Double price, Date creationDate, Date lastModifiedDate) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String description;

    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date creationDate;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date lastModifiedDate;


}
