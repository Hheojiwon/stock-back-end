package com.example.investment_api.home.fluctuation.service.client;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

@Component
public class FluctuationDataFetcher {

    private final RestTemplate restTemplate;

    @Value("${api.app_key}")
    private String appKey;

    @Value("${api.app_secret}")
    private String appSecret;

    @Value("${fluctuation.tr_id}")
    private String trId;

    @Value("${api.access_token}")
    private String accessToken;

    public FluctuationDataFetcher(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> fluctuationData() {
        return getStringResponseEntity();
    }

    private ResponseEntity<String> getStringResponseEntity() {
        String url = setURL();
        HttpHeaders headers = new HttpHeaders();
        setHeader(headers);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
    }

    private void setHeader(final HttpHeaders headers) {
        headers.set("tr_id", trId);
        headers.set("appsecret", appSecret);
        headers.set("appkey", appKey);
        headers.set("Authorization", "Bearer " + accessToken);
        headers.set("Content-Type", "application/json");
    }

    private String setURL() {
        return "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/ranking/fluctuation?"
                + "fid_cond_mrkt_div_code=J&"
                + "fid_cond_scr_div_code=20170&"
                + "fid_input_iscd=0000&"
                + "fid_rank_sort_cls_code=0&"
                + "fid_input_cnt_1=0&"
                + "fid_prc_cls_code=0&"
                + "fid_input_price_1=&"
                + "fid_input_price_2=&"
                + "fid_vol_cnt=&"
                + "fid_trgt_cls_code=0&"
                + "fid_trgt_exls_cls_code=0&"
                + "fid_div_cls_code=0&"
                + "fid_rsfl_rate1=&"
                + "fid_rsfl_rate2=";
    }
}
