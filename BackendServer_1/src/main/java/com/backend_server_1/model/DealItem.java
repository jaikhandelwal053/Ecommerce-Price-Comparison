package com.backend_server_1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealItem {
    private String itemid;
    private String productTitle;
    private String size;
    private String brand;
    private Image image;
	private MarketingPrice marketingPrice;
	private Price price;
	private int stock;
	private String dealStartDate;
	private String dealEndDate;
    
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Image {
		private String imageUrl;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class MarketingPrice {
		private OriginalPrice originalPrice;
		private double discountPercentage;
		private DiscountAmount discountAmount;
		private String discountType;

		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class OriginalPrice {
			private double value;
			private String currency;
		}

		@Data
		@AllArgsConstructor
		@NoArgsConstructor
		public static class DiscountAmount {
			private double value;
			private String currency;
		}
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Price {
		private double value;
		private String currency;
	}
}