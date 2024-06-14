# E-commerce Price Comparison Application

## Overview

This is an E-commerce Price Comparison application built using Java 8, Spring Boot, and WebClient. The application fetches product data from multiple e-commerce APIs concurrently and compares prices to provide users with the best deals and prices.

## Features

- Fetches product data from multiple e-commerce backend servers (Amazon, eBay, Walmart) concurrently.
- Aggregates product data and compares prices to identify the best deals for each category.
- Filters and sorts deals by maximum discount and price.
- Handles errors gracefully and provides default responses for failed API calls.
- Utilizes Java 8 features such as lambdas, streams, and CompletableFuture for concurrent processing.
- Includes JUnit test cases with Mockito for mocking responses.

## Project Structure

The project consists of the following components:

- **Main Application**: Runs on port 8090 and aggregates deals from all backend servers.
- **Backend Server 1 (Amazon)**: Runs on port 8081 and returns hard-coded deal data.
- **Backend Server 2 (eBay)**: Runs on port 8082 and returns hard-coded deal data.
- **Backend Server 3 (Walmart)**: Runs on port 8083 and returns hard-coded deal data.

## Prerequisites

- Java 8 or higher
- Maven
- Lombok plugin installed in your IDE

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/jaikhandelwal053/Ecommerce-Price-Comparison.git
   cd ecommerce-price-comparison

## Configuration

The application.properties file in the main application contains the URLs of the backend servers:

    server.port=8080
    backend.amazon.url=http://localhost:8081/amazon/deals
    backend.ebay.url=http://localhost:8082/ebay/deals
    backend.walmart.url=http://localhost:8083/walmart/deals

## API Endpoints

URI: `http://localhost:8080/deals/{categoryName}`

GET `GET http://localhost:8090/deals/jeans`

Response:
{
    "categoryName": "jeans",
    "dealItems": [
        {
            "itemid": "123456789",
            "productTitle": "Blue Levis Jeans",
            "size": "30",
            "brand": "Levis",
            "image": {
                "imageUrl": "https://i.ebayimg.com/images/g/abc123/s-l225.jpg"
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": 29.99,
                    "currency": "USD"
                },
                "discountPercentage": 17.0,
                "discountAmount": {
                    "value": 5.0,
                    "currency": "USD"
                },
                "discountType": "LIST_PRICE"
            },
            "price": {
                "value": 24.99,
                "currency": "USD"
            },
            "stock": 3,
            "dealStartDate": "2024-01-01T00:00:00Z",
            "dealEndDate": "2024-12-31T23:59:59Z"
        },
        {
            "itemid": "987654321",
            "productTitle": "Black Wrangler Jeans",
            "size": "32",
            "brand": "Wrangler",
            "image": {
                "imageUrl": "https://i.ebayimg.com/images/g/def456/s-l225.jpg"
            },
            "marketingPrice": {
                "originalPrice": {
                    "value": 39.99,
                    "currency": "USD"
                },
                "discountPercentage": 20.0,
                "discountAmount": {
                    "value": 10.0,
                    "currency": "USD"
                },
                "discountType": "SALE_PRICE"
            },
            "price": {
                "value": 29.99,
                "currency": "USD"
            },
            "stock": 6,
            "dealStartDate": "2024-02-01T00:00:00Z",
            "dealEndDate": "2024-11-30T23:59:59Z"
        }
    ]
}