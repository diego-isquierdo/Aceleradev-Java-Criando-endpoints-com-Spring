package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Acceleration;
import com.challenge.service.interfaces.AccelerationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/acceleration")
public class AccelerationController {

    @Autowired
    private AccelerationServiceInterface accelerationService;

    @GetMapping("/{id}")
    public ResponseEntity<Acceleration> findById(@PathVariable Long id){
        return new ResponseEntity<>(accelerationService.findById(id).orElseThrow(()->new ResourceNotFoundException("Acceleretion")), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<Acceleration>> findByCompanyId(@RequestParam Long companyId){
        List<Acceleration> accelerations = accelerationService.findByCompanyId(companyId);

        return new ResponseEntity<>(accelerations, HttpStatus.OK);
    }

}
