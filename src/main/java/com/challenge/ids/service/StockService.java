package com.challenge.ids.service;

import com.challenge.ids.model.Stock;
import com.challenge.ids.repository.StockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> findAll() {
        return this.stockRepository.findAll();
    }

    public List<Stock> findByStock(String tickerSymbol) {
        return this.stockRepository.findByStock(tickerSymbol);
    }

    public Stock save(Stock stock) {
        return this.stockRepository.save(stock);
    }

    public List<Stock> saveAll(List<Stock> stocks) {
        return this.stockRepository.saveAll(stocks);
    }
}
