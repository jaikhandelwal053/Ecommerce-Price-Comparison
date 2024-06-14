package com.backend_server_3.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.backend_server_3.model.DealItem;
import com.backend_server_3.model.Response;

@Service
public class DealService {

    public Response getDealsByCategory(String categoryName) {
    	DealItem item1 = new DealItem("556677889", "Light Blue Calvin Klein Jeans", "36", "Calvin Klein",
    		    new DealItem.Image("https://i.ebayimg.com/images/g/mno345/s-l225.jpg"),
    		    new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(69.99, "USD"),
    		        35.0, new DealItem.MarketingPrice.DiscountAmount(25.0, "USD"), "FLASH_SALE"),
    		    new DealItem.Price(44.99, "USD"), 4, "2023-05-01T00:00:00Z", "2024-08-31T23:59:59Z");

    		DealItem item2 = new DealItem("665544332", "Blue Gap Jeans", "31", "Gap",
    		    new DealItem.Image("https://i.ebayimg.com/images/g/pqr678/s-l225.jpg"),
    		    new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(79.99, "USD"),
    		        40.0, new DealItem.MarketingPrice.DiscountAmount(30.0, "USD"), "EXCLUSIVE"),
    		    new DealItem.Price(49.99, "USD"), 7, "2023-06-01T00:00:00Z", "2024-07-31T23:59:59Z");

        return new Response(categoryName, Arrays.asList(item1, item2));
    }
}
