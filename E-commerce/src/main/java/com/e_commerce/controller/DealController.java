package com.e_commerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.e_commerce.exception.CustomErrorException;
import com.e_commerce.model.Response;
import com.e_commerce.service.DealService;


@RestController
public class DealController {

    private final DealService dealService;

    public DealController(DealService dealService) {
        this.dealService = dealService;
    }

    @GetMapping("/deals/{categoryName}")
    public Response getDeals(@PathVariable String categoryName){

    	try {
            return dealService.getAllDeals(categoryName);
        } catch (Exception e) {
            throw new CustomErrorException("Invalide categoryName", "400 Bad Request");
        }
    }
}
