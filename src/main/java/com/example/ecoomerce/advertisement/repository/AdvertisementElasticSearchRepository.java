package com.example.ecoomerce.advertisement.repository;

import com.example.ecoomerce.advertisement.model.AdvertisementDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdvertisementElasticSearchRepository extends ElasticsearchRepository<AdvertisementDocument, UUID> {


    Page<AdvertisementDocument> findByTitle(String title, Pageable pageable);



}
