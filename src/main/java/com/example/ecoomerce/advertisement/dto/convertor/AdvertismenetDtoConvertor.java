package com.example.ecoomerce.advertisement.dto.convertor;

import com.example.ecoomerce.advertisement.dto.AdvertisementDto;
import com.example.ecoomerce.advertisement.model.Advertisement;
import com.example.ecoomerce.user.dto.convertor.UserDtoConvertor;
import org.springframework.stereotype.Component;

@Component
public class AdvertismenetDtoConvertor {

    private final UserDtoConvertor userDtoConvertor;

    public AdvertismenetDtoConvertor(UserDtoConvertor userDtoConvertor) {
        this.userDtoConvertor = userDtoConvertor;
    }

    public AdvertisementDto toDto(Advertisement advertisement)
    {
        return new AdvertisementDto(advertisement.getId(),advertisement.getTitle(),advertisement.getDescription(),advertisement.getPrice(),advertisement.getCreationDate(),advertisement.getLastModifiedDate(),userDtoConvertor.toDto(advertisement.getUser()) ,advertisement.getHashtags());
    }


}
