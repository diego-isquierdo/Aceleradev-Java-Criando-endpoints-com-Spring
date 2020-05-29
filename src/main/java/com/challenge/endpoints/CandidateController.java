package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper mapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(
            @PathVariable Long userId,
            @PathVariable Long companyId,
            @PathVariable Long accelerationId){

        Candidate candidate = candidateService.findById(userId, companyId, accelerationId)
                                              .orElseThrow(()->new ResourceNotFoundException("Candidate"));

        return new ResponseEntity<>(mapper.map(candidate), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<CandidateDTO>> findByCompanyIdOrAccelerationId(
            @RequestParam(required = false) Long companyId,
            @RequestParam(required = false) Long accelerationId
    ){
        Set<Candidate> candidates = new HashSet<>();

        if(companyId!=null) candidates.addAll(candidateService.findByCompanyId(companyId));
        if(accelerationId!=null) candidates.addAll(candidateService.findByAccelerationId(accelerationId));


        Set<CandidateDTO> candidateDTOList = candidates.stream()
                .map(candidate -> mapper.map(candidate)).collect(Collectors.toSet());


        return new ResponseEntity<>(candidateDTOList, HttpStatus.OK);
    }

}
