package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.Company;
import com.challenge.service.interfaces.CompanyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceInterface companyService;

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id){
        return new ResponseEntity<>(companyService.findById(id).orElseThrow(()->new ResourceNotFoundException("Company")), HttpStatus.OK);
    }


    @GetMapping
    public List<Company> listByAccelerationIdOrUserId(
            @RequestParam(required = false) Long accelerationId,
            @RequestParam(required = false) Long userId
    ){
        if (accelerationId != null) return companyService.findByAccelerationId(accelerationId);
        if (userId != null) return companyService.findByUserId(userId);

        return Collections.emptyList();
    }


}
