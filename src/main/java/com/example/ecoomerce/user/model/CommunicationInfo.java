package com.example.ecoomerce.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunicationInfo {

    @Id
    @GeneratedValue
    private  Long id;
    private  String address;
    private  String city;
    private  String country;
    private  String postCode;

    public CommunicationInfo(String address, String city, String country, String postCode, User user) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
        this.user = user;
    }



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public CommunicationInfo(Long id, String address, String city, String country, String postCode) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommunicationInfo that = (CommunicationInfo) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(country, that.country) && Objects.equals(postCode, that.postCode) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, city, country, postCode, user);
    }
}
