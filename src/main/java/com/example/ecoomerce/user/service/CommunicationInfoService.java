package com.example.ecoomerce.user.service;

import com.example.ecoomerce.user.dto.CommunicationInfoDto;
import com.example.ecoomerce.user.dto.CreateCommunicationInfoRequest;
import com.example.ecoomerce.user.dto.UpdateCommunicationInfo;
import com.example.ecoomerce.user.dto.convertor.CommunicationInfoDtoConvertor;
import com.example.ecoomerce.user.exception.CommunicationInfoNotFoundException;
import com.example.ecoomerce.user.model.CommunicationInfo;
import com.example.ecoomerce.user.model.User;
import com.example.ecoomerce.user.repository.CommunicationInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CommunicationInfoService {

    private final CommunicationInfoRepository communicationInfoRepository;
    private final UserService userService;
    private final CommunicationInfoDtoConvertor communicationInfoDtoConvertor;

    public CommunicationInfoService( CommunicationInfoRepository communicationInfoRepository, UserService userService, CommunicationInfoDtoConvertor communicationInfoDtoConvertor) {
        this.communicationInfoRepository = communicationInfoRepository;
        this.userService = userService;
        this.communicationInfoDtoConvertor = communicationInfoDtoConvertor;

    }

    public CommunicationInfoDto createCommunicationInfo (CreateCommunicationInfoRequest createCommunicationInfoRequest)
    {
        User user = userService.findByUserId(createCommunicationInfoRequest.getUserId());
        CommunicationInfo communicationInfo = new CommunicationInfo(createCommunicationInfoRequest.getAddress(), createCommunicationInfoRequest.getCity(), createCommunicationInfoRequest.getCountry(), createCommunicationInfoRequest.getPostCode(),user);


        return communicationInfoDtoConvertor.toDto(communicationInfoRepository.save(communicationInfo));

    }

    public CommunicationInfoDto updateCommunicationInfo(Long communicationInfoId,UpdateCommunicationInfo updateCommunicationInfo)
    {

        CommunicationInfo communicationInfo = findCommunicationInfoById(communicationInfoId);
        communicationInfo.setCity(updateCommunicationInfo.getCity());
        communicationInfo.setAddress(updateCommunicationInfo.getAddress());
        communicationInfo.setPostCode(updateCommunicationInfo.getPostCode());
        communicationInfo.setCountry(updateCommunicationInfo.getCountry());

        return communicationInfoDtoConvertor.toDto(communicationInfoRepository.save(communicationInfo));

    }

    public void deleteCommunicationInfo(Long id)
    {
        if(communicationInfoRepository.existsById(id))
        {
           communicationInfoRepository.deleteById(id);
        }

        else {
            throw new CommunicationInfoNotFoundException("Communication info not found by id : " + id);
        }

    }



    private CommunicationInfo findCommunicationInfoById(Long id)
    {
        return communicationInfoRepository.findById(id).orElseThrow(() -> new CommunicationInfoNotFoundException("Communication info not found by id : " + id));
    }


}
