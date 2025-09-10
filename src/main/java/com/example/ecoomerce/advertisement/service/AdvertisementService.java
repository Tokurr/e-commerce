package com.example.ecoomerce.advertisement.service;

import com.example.ecoomerce.advertisement.dto.AdvertisementDto;
import com.example.ecoomerce.advertisement.dto.CreateAdvertisementRequest;
import com.example.ecoomerce.advertisement.dto.UpdateAdvertisementRequest;
import com.example.ecoomerce.advertisement.dto.convertor.AdvertismenetDtoConvertor;
import com.example.ecoomerce.advertisement.exception.AdvertisementNotFoundException;
import com.example.ecoomerce.advertisement.model.Advertisement;
import com.example.ecoomerce.advertisement.model.AdvertisementDocument;
import com.example.ecoomerce.advertisement.repository.AdvertisementElasticSearchRepository;
import com.example.ecoomerce.advertisement.repository.AdvertisementRepository;
import com.example.ecoomerce.user.exception.UserNotFoundException;
import com.example.ecoomerce.user.model.User;
import com.example.ecoomerce.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvertisementService {

    private final AdvertisementRepository advertisementRepository;
    private final AdvertismenetDtoConvertor advertismenetDtoConvertor;

    private final AdvertisementElasticSearchRepository advertisementElasticSearchRepository;

    private final UserService userService;

    public AdvertisementService(AdvertisementRepository advertisementRepository, AdvertismenetDtoConvertor advertismenetDtoConvertor, AdvertisementElasticSearchRepository advertisementElasticSearchRepository, UserService userService) {
        this.advertisementRepository = advertisementRepository;
        this.advertismenetDtoConvertor = advertismenetDtoConvertor;
        this.advertisementElasticSearchRepository = advertisementElasticSearchRepository;
        this.userService = userService;
    }

    public AdvertisementDto createAdvertisement(CreateAdvertisementRequest request)
    {

        if(userService.isExistUser(request.getUserId()))
        {
            User user = userService.findByUserId(request.getUserId());
            Advertisement advertisement = new Advertisement(request.getTitle()
                    ,request.getDescription(),
                    request.getPrice(),
                    user,
                   request.getHashtag());

            AdvertisementDocument advertisementDocument = new AdvertisementDocument(request.getTitle(),request.getDescription(),request.getHashtag(),request.getPrice(),request.getUserId());
            advertisementRepository.save(advertisement);
            advertisementElasticSearchRepository.save(advertisementDocument);
            return advertismenetDtoConvertor.toDto(advertisement);
        }

       throw new  UserNotFoundException("user not found");

    }

    public AdvertisementDto getAdvertisement(UUID id)
    {
            Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new AdvertisementNotFoundException("Advertisement not found by id : " + id));
            return advertismenetDtoConvertor.toDto(advertisement);
    }


    public List<AdvertisementDto> getAllAdvertisements()
    {
       List<AdvertisementDto> advertisementDtoList =  advertisementRepository.findAll().stream().map(advertisement -> advertismenetDtoConvertor.toDto(advertisement)).collect(Collectors.toList());

       return advertisementDtoList;

    }

    public AdvertisementDto updateAdvertisement(UUID id, UpdateAdvertisementRequest updateAdvertisementRequest)
    {

        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(() -> new AdvertisementNotFoundException("Advertisement not found by id: " + id));

        advertisement.setTitle(updateAdvertisementRequest.getTitle());
        advertisement.setPrice(updateAdvertisementRequest.getPrice());
        advertisement.setDescription(updateAdvertisementRequest.getDescription());
        advertisement.setLastModifiedDate(LocalDateTime.now());
        advertisement.setHashtags(updateAdvertisementRequest.getHashtag());

        AdvertisementDocument advertisementDocument = new AdvertisementDocument(advertisement.getId().toString(),advertisement.getTitle(),advertisement.getDescription(),advertisement.getHashtags(),advertisement.getPrice(),advertisement.getUser().getId());

        advertisementRepository.save(advertisement);
        advertisementElasticSearchRepository.save(advertisementDocument);

        return advertismenetDtoConvertor.toDto(advertisement);
    }

    public void deleteAdvertisement(UUID id)
    {
        if(advertisementRepository.existsById(id))
        {
            advertisementRepository.deleteById(id);
            advertisementElasticSearchRepository.deleteById(id);
        }

        else
        {
            throw  new AdvertisementNotFoundException("Advertisement not found by id: " + id);
        }
    }
    public Page<AdvertisementDocument> searchByTitle(String keyword, Pageable pageable) {
        return advertisementElasticSearchRepository.findByTitleContaining(keyword, pageable);
    }

    public Page<AdvertisementDocument> searchByHashtag(String hashtag, Pageable pageable) {
        return advertisementElasticSearchRepository.findByHashtagsContaining(hashtag, pageable);
    }

    public Page<AdvertisementDocument> filterByPrice(BigDecimal min, BigDecimal max, Pageable pageable) {
        return advertisementElasticSearchRepository.findByPriceBetween(min, max, pageable);
    }

}
