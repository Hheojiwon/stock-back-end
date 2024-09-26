package com.example.investment_api.search.base.dashboard.controller;

import com.example.investment_api.search.base.dashboard.dto.DashboardData;
import com.example.investment_api.search.base.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/search/dashboard")
public class DashboardDataController {

    private final DashboardService dashboardService;

    public DashboardDataController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<List<DashboardData>> getData(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        try {
            List<DashboardData> stockDataList = dashboardService.getDashboardData(page, size);
            return ResponseEntity.ok(stockDataList);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
