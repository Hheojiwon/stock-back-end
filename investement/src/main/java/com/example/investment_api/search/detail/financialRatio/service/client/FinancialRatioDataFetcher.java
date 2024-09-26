package com.example.investment_api.search.detail.financialRatio.service.client;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

@Component
public class FinancialRatioDataFetcher {

    @Value("${financialRatio.tr_id}")
    private String trId;

    @Value("${api.app_secret}")
    private String appSecret;

    @Value("${api.app_key}")
    private String appKey;

    @Value("${api.access_token}")
    private String accessToken;

    private final RestTemplate restTemplate;

    public FinancialRatioDataFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> fetchFinancialRatioData(String stockInfo) {
        return setURL(stockInfo);
    }

    private ResponseEntity<String> setURL(final String stockInfo) {
        String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/finance/financial-ratio?FID_DIV_CLS_CODE=0&fid_cond_mrkt_div_code=J&fid_input_iscd=" + stockInfo;

        HttpHeaders headers = setHeader();

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    private HttpHeaders setHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("tr_id", trId);
        headers.set("appsecret", appSecret);
        headers.set("appkey", appKey);
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }

}
