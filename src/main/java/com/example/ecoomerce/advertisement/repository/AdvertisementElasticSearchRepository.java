package com.example.ecoomerce.advertisement.repository;

import com.example.ecoomerce.advertisement.model.Advertisement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementElasticSearchRepository extends ElasticsearchRepository<Advertisement,String> {


    Page<Advertisement> findByTitle(String title, Pageable pageable);





}
