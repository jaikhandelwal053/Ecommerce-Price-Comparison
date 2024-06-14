package com.backend_server_1.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.backend_server_1.model.DealItem;
import com.backend_server_1.model.Response;

@Service
public class DealService {

    public Response getDealsByCategory(String categoryName) {
    	DealItem item1 = new DealItem("123456789", "Blue Levis Jeans", "30", "Levis",
    		    new DealItem.Image("https://i.ebayimg.com/images/g/abc123/s-l225.jpg"),
    		    new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(29.99, "USD"),
    		        17.0, new DealItem.MarketingPrice.DiscountAmount(5.0, "USD"), "LIST_PRICE"),
    		    new DealItem.Price(24.99, "USD"), 3, "2024-01-01T00:00:00Z", "2024-12-31T23:59:59Z");

    		DealItem item2 = new DealItem("987654321", "Black Wrangler Jeans", "32", "Wrangler",
    		    new DealItem.Image("https://i.ebayimg.com/images/g/def456/s-l225.jpg"),
    		    new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(39.99, "USD"),
    		        20.0, new DealItem.MarketingPrice.DiscountAmount(10.0, "USD"), "SALE_PRICE"),
    		    new DealItem.Price(29.99, "USD"), 0, "2024-02-01T00:00:00Z", "2024-11-30T23:59:59Z");
 	
        return new Response(categoryName, Arrays.asList(item1, item2));
    }
}
