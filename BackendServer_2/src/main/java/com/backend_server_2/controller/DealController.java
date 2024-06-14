package com.backend_server_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_server_2.model.Response;
import com.backend_server_2.service.DealService;

@RestController
@RequestMapping("/backendserver2")
public class DealController {

    @Autowired
    private DealService dealService;

    @GetMapping("/ebay/deals/{categoryName}")
    public Response getDeals(@PathVariable String categoryName) {
    	 try {
             return dealService.getDealsByCategory(categoryName);
         } catch (Exception e) {
             throw new RuntimeException("Error retrieving deals for category: " + categoryName, e);
         }
    }
}