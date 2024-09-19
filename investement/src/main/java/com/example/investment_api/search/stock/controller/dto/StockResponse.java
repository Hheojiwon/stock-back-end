package com.example.investment_api.search.stock.controller.dto;

public record StockResponse(
        String stockName,
        String stockPrice,
        String previousStockPrice,
        String contrastRatio,
        String highStockPrice,
        String lowStockPrice
) {
}
