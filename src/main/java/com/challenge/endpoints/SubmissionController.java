package com.challenge.endpoints;

import com.challenge.dto.SubmissionDTO;
import com.challenge.entity.Submission;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.interfaces.SubmissionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/submission")

public class SubmissionController {

    @Autowired
    private SubmissionServiceInterface submissionService;

    @Autowired
    private SubmissionMapper mapper;

    @GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByAccelerationIdAndUserId(
            @RequestParam(required = false) Long challengeId,
            @RequestParam(required = false) Long accelerationId
    ){

        List<Submission> submissions = submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);

        List<SubmissionDTO> submissionDTOList = submissions.stream()
                .map(submission -> mapper.map(submission)).collect(Collectors.toList());

        return new ResponseEntity<>(submissionDTOList, HttpStatus.OK);

    }

}