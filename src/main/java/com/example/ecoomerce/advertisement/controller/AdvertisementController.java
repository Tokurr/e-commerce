package com.example.ecoomerce.advertisement.controller;

import com.example.ecoomerce.advertisement.dto.AdvertisementDto;
import com.example.ecoomerce.advertisement.dto.CreateAdvertisementRequest;
import com.example.ecoomerce.advertisement.dto.UpdateAdvertisementRequest;
import com.example.ecoomerce.advertisement.model.AdvertisementDocument;
import com.example.ecoomerce.advertisement.service.AdvertisementService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/advertisement")
public class AdvertisementController {

    private final AdvertisementService advertisementService;


    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @PostMapping
    public ResponseEntity<AdvertisementDto> createAdvertisement(@Valid @RequestBody CreateAdvertisementRequest createAdvertisementRequest)
    {
        return ResponseEntity.ok(advertisementService.createAdvertisement(createAdvertisementRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertisementDto> getAdvertisement(@PathVariable UUID id)
    {
        return ResponseEntity.ok(advertisementService.getAdvertisement(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdvertisementDto> updateAdvertisement(@PathVariable UUID id,@Valid @RequestBody UpdateAdvertisementRequest updateAdvertisementRequest)
    {
        return ResponseEntity.ok(advertisementService.updateAdvertisement(id,updateAdvertisementRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvertisement(@PathVariable UUID id)
    {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AdvertisementDto>> getAllAdvertisements()
    {
        return ResponseEntity.ok(advertisementService.getAllAdvertisements());
    }


    @GetMapping("/title")
    public ResponseEntity<Page<AdvertisementDocument>> searchByTitle(
            @RequestParam String keyword,
            Pageable pageable) {
        return ResponseEntity.ok(advertisementService.searchByTitle(keyword, pageable));
    }

    @GetMapping("/hashtag")
    public ResponseEntity<Page<AdvertisementDocument>> searchByHashtag(
            @RequestParam String hashtag,
            Pageable pageable) {
        return ResponseEntity.ok(advertisementService.searchByHashtag(hashtag, pageable));
    }

    @GetMapping("/price")
    public ResponseEntity<Page<AdvertisementDocument>> filterByPrice(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max,
            Pageable pageable) {
        return ResponseEntity.ok(advertisementService.filterByPrice(min, max, pageable));
    }


}
