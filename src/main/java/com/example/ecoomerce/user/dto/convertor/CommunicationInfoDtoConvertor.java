package com.example.ecoomerce.user.dto.convertor;

import com.example.ecoomerce.user.dto.CommunicationInfoDto;
import com.example.ecoomerce.user.model.CommunicationInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommunicationInfoDtoConvertor {

    public CommunicationInfoDto toDto(CommunicationInfo communicationInfo)
    {
        return new CommunicationInfoDto(communicationInfo.getAddress(),communicationInfo.getCity(),communicationInfo.getCountry(),communicationInfo.getPostCode());
    }

    public List<CommunicationInfoDto> toDtoList(Set<CommunicationInfo> communicationInfo)
    {
        return communicationInfo.stream().map(communicationInfo1 -> toDto(communicationInfo1)).collect(Collectors.toList());
    }

}
