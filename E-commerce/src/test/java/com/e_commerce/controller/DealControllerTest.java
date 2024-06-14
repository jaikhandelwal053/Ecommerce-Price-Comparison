package com.e_commerce.controller;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.e_commerce.model.DealItem;
import com.e_commerce.model.Response;
import com.e_commerce.service.DealService;

@WebMvcTest(DealController.class)
public class DealControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DealService dealService;

    @Test
    public void testGetDeals() throws Exception {
    	DealItem item1= new DealItem("123", "Test Product 1 Jeans", "L", "BrandA",new DealItem.Image("url1"),
				new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(100, "USD"),
						17, new DealItem.MarketingPrice.DiscountAmount(90, "USD"), "LIST_PRICE"),
				new DealItem.Price(90, "USD"), 0, "2023-01-01", "2024-12-31");
    	
    	DealItem item2 =  new DealItem("456", "Test Product 2 Jeans", "M", "BrandB",new DealItem.Image("url2"),
				new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(200, "USD"),
						20, new DealItem.MarketingPrice.DiscountAmount(90, "USD"), "LIST_PRICE"),
				new DealItem.Price(90, "USD"), 3, "2023-01-01", "2024-12-31");
    	
        Response mockResponse = new Response("Jeans", Arrays.asList(item1, item2));

        Mockito.when(dealService.getAllDeals(anyString())).thenReturn(mockResponse);

        mockMvc.perform(get("/deals/Jeans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dealItems", hasSize(2)))
                .andExpect(jsonPath("$.dealItems[0].itemid").value("123"))
                .andExpect(jsonPath("$.dealItems[1].itemid").value("456"));
    }
}
