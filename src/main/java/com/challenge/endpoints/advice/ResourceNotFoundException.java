package com.challenge.endpoints.advice;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException (String resourceName){
        super("Resource " + resourceName + " Not Found");
    }
}
