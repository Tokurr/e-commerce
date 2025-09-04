package com.example.ecoomerce.user.controller;

import com.example.ecoomerce.user.dto.CommunicationInfoDto;
import com.example.ecoomerce.user.dto.CreateCommunicationInfoRequest;
import com.example.ecoomerce.user.dto.UpdateCommunicationInfo;
import com.example.ecoomerce.user.service.CommunicationInfoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/communication")
public class CommunicationInfoController {

    private final CommunicationInfoService communicationInfoService;


    public CommunicationInfoController(CommunicationInfoService communicationInfoService) {
        this.communicationInfoService = communicationInfoService;
    }

    @PostMapping
    public ResponseEntity<CommunicationInfoDto> createCommunicationInfo(@Valid @RequestBody CreateCommunicationInfoRequest createCommunicationInfoRequest)
    {
        return ResponseEntity.ok(communicationInfoService.createCommunicationInfo(createCommunicationInfoRequest));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CommunicationInfoDto> updateCommunicationInfo(@PathVariable Long id,@Valid  @RequestBody UpdateCommunicationInfo updateCommunicationInfo)
    {
        return ResponseEntity.ok(communicationInfoService.updateCommunicationInfo(id,updateCommunicationInfo));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommunicationInfo(@PathVariable Long id)
    {
       communicationInfoService.deleteCommunicationInfo(id);
        return ResponseEntity.noContent().build();
    }


}
