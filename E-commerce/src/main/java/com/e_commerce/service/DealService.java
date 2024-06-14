package com.e_commerce.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.e_commerce.model.DealItem;
import com.e_commerce.model.Response;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class DealService {

	private final WebClient webClient;

	@Value("${backend.amazon.url}")
	private String amazonUrl;

	@Value("${backend.ebay.url}")
	private String ebayUrl;

	@Value("${backend.walmart.url}")
	private String walmartUrl;

	public DealService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.build();
	}

	public Response getAllDeals(String categoryName) throws ExecutionException, InterruptedException {
		CompletableFuture<List<DealItem>> amazonDeals = fetchDeals(amazonUrl, categoryName);
		CompletableFuture<List<DealItem>> ebayDeals = fetchDeals(ebayUrl, categoryName);
		CompletableFuture<List<DealItem>> walmartDeals = fetchDeals(walmartUrl, categoryName);

		CompletableFuture.allOf(amazonDeals, ebayDeals, walmartDeals).join();

		List<DealItem> combinedDeals = amazonDeals.get();
		combinedDeals.addAll(ebayDeals.get());
		combinedDeals.addAll(walmartDeals.get());
		combinedDeals = combinedDeals.stream()
				.filter(dealItem -> dealItem.getStock() > 0)
				.filter(this::isDealActive)
				.sorted((item1, item2) -> {
					if (item2.getMarketingPrice().getDiscountPercentage() != item1.getMarketingPrice().getDiscountPercentage()) {
						return Double.compare(item2.getMarketingPrice().getDiscountPercentage(), 
								item1.getMarketingPrice().getDiscountPercentage());
					}
					return Double.compare(item1.getPrice().getValue(), item2.getPrice().getValue());
				})
				.collect(Collectors.toList());
		return new Response(categoryName, combinedDeals);
	}

	private boolean isDealActive(DealItem dealItem) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime startDate = LocalDateTime.parse(dealItem.getDealStartDate(), formatter);
		LocalDateTime endDate = LocalDateTime.parse(dealItem.getDealEndDate(), formatter);
		return now.isAfter(startDate) && now.isBefore(endDate);
	}

	private CompletableFuture<List<DealItem>> fetchDeals(String baseUrl, String categoryName) {
		return webClient.get()
				.uri(baseUrl + "/" + categoryName)
				.retrieve()
				.bodyToMono(Response.class)
				.map(Response::getDealItems)
				.onErrorResume(WebClientResponseException.class, e -> {
					log.error("Failed to fetch deals from {}: {}", baseUrl, e.getMessage());
					return Mono.just(Collections.emptyList());
				})
				.toFuture();
	}
}
