package com.example.ecoomerce.advertisement.repository;

import com.example.ecoomerce.advertisement.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvertisementRepository extends JpaRepository<Advertisement, UUID> {



}
