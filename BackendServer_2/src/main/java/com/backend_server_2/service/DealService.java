package com.backend_server_2.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.backend_server_2.model.DealItem;
import com.backend_server_2.model.Response;

@Service
public class DealService {

	public Response getDealsByCategory(String categoryName) {
		DealItem item1 = new DealItem("112233445", "Grey Lee Jeans", "28", "Lee",
			    new DealItem.Image("https://i.ebayimg.com/images/g/ghi789/s-l225.jpg"),
			    new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(49.99, "USD"),
			        25.0, new DealItem.MarketingPrice.DiscountAmount(15.0, "USD"), "PROMOTION"),
			    new DealItem.Price(34.99, "USD"), 5, "2024-03-01T00:00:00Z", "2024-10-31T23:59:59Z");

			DealItem item2 = new DealItem("223344556", "Dark Blue Diesel Jeans", "34", "Diesel",
			    new DealItem.Image("https://i.ebayimg.com/images/g/jkl012/s-l225.jpg"),
			    new DealItem.MarketingPrice(new DealItem.MarketingPrice.OriginalPrice(59.99, "USD"),
			        30.0, new DealItem.MarketingPrice.DiscountAmount(20.0, "USD"), "CLEARANCE"),
			    new DealItem.Price(39.99, "USD"), 1, "2024-04-01T00:00:00Z", "2024-09-30T23:59:59Z");

		return new Response(categoryName, Arrays.asList(item1, item2));
	}
}
