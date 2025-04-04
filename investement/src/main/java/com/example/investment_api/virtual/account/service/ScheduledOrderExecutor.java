package com.example.investment_api.virtual.account.service;

import com.example.investment_api.virtual.account.domain.OrderType;
import com.example.investment_api.virtual.account.domain.StockOrder;
import com.example.investment_api.virtual.account.domain.StockOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledOrderExecutor {

    private final StockOrderRepository stockOrderRepository;
    private final OrderService memberAccountService;

    @Scheduled(fixedRate = 10000)
    @Transactional
    public void executePendingOrders() {
        List<StockOrder> pendingOrders = stockOrderRepository.findByIsProcessedFalse();
        for (StockOrder order : pendingOrders) {
            Long memberId = order.getMemberId();
            String stockName = order.getStockName();
            int limitPrice = order.getLimitPrice();
            int quantity = order.getQuantity();
            int currentPrice = memberAccountService.getCurrentPrice(stockName);

            processStockOrder(order, memberId, stockName, limitPrice, quantity, currentPrice);
        }
    }

    private void processStockOrder(StockOrder order, Long memberId, String stockName, int limitPrice, int quantity, int currentPrice) {
        if (currentPrice == limitPrice) {
            if (order.getIsBuyOrder().equals(OrderType.BUY.getType())) {
                memberAccountService.buyStockImmediately(memberId, stockName, quantity);
            } else {
                memberAccountService.sellStockImmediately(memberId, stockName, quantity);
            }
            order.setProcessed(true);
            stockOrderRepository.save(order);
        }
    }
}
