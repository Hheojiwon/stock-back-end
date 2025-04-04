package com.example.investment_api.search.detail.consensus.controller;

import com.example.investment_api.search.detail.consensus.controller.dto.InvestmentRecommendationDTO;

import com.example.investment_api.search.detail.consensus.service.InvestmentRecommendationService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/search/investmentRecommendation")
public class InvestmentRecommendationController {

    private final InvestmentRecommendationService investmentRecommendationService;

    public InvestmentRecommendationController(final InvestmentRecommendationService investmentRecommendationService) {
        this.investmentRecommendationService = investmentRecommendationService;
    }

    @GetMapping
    public ResponseEntity<InvestmentRecommendationDTO> getFinancialRatio(@RequestParam String stockName) throws IOException {
        return getInvestmentRecommendationDTOResponseEntity(stockName);
    }

    private ResponseEntity<InvestmentRecommendationDTO> getInvestmentRecommendationDTOResponseEntity(final String stockName) throws IOException {
        InvestmentRecommendationDTO investmentRecommendationDTO = investmentRecommendationService.getInvestmentRecommendation(stockName);
        return ResponseEntity.ok(investmentRecommendationDTO);
    }
}
