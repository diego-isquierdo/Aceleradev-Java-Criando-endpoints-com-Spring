package com.challenge.endpoints;

import com.challenge.endpoints.advice.ResourceNotFoundException;
import com.challenge.entity.User;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id).orElseThrow(()->new ResourceNotFoundException("User")), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Set<User>> findByAccelerationNameOrCompanyId(
            @RequestParam(required = false) String accelerationName,
            @RequestParam(required = false) Long companyId
    ){

        Set<User> users = new HashSet<>();

        if(accelerationName != null)users.addAll(userService.findByAccelerationName(accelerationName));
        if(companyId != null)users.addAll(userService.findByCompanyId(companyId));

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
