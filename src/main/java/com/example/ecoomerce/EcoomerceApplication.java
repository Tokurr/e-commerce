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
/*
	private static AdvertisementElasticSearchRepository advertisementElasticSearchRepository;

	public EcoomerceApplication(AdvertisementElasticSearchRepository advertisementElasticSearchRepository) {
		this.advertisementElasticSearchRepository = advertisementElasticSearchRepository;
	}
*/
	public static void main(String[] args) {
		SpringApplication.run(EcoomerceApplication.class, args);






	}

/*
	@Bean
	public CommandLineRunner demoData() {
		return args -> {
			Date now = new Date();

			Advertisement adv1 = new Advertisement(
					"1",
					"Laptop",
					"16 GB RAM, 512 GB SSD, i7 işlemci",
					15000.0,
					now,
					now
			);

			advertisementElasticSearchRepository.save(adv1);
			System.out.println("Kayıt eklendi: " + adv1);

			Pageable pageable = PageRequest.of(0, 2);
			Page<Advertisement> findAdvertisement =
					advertisementElasticSearchRepository.findByTitle("Laptop", pageable);

			findAdvertisement.getContent().forEach(System.out::println);
		};
	}
*7

}
