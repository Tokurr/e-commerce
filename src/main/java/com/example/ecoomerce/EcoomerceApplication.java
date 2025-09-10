package com.example.ecoomerce;

import com.example.ecoomerce.advertisement.model.Advertisement;
import com.example.ecoomerce.advertisement.repository.AdvertisementElasticSearchRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@SpringBootApplication
public class EcoomerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcoomerceApplication.class, args);






	}


}
