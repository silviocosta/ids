package com.challenge.ids;

import com.challenge.ids.controller.StockController;
import com.challenge.ids.model.Stock;
import com.challenge.ids.service.StockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(StockController.class)
class IDSApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService service;

    @Test
    protected void givenTickerShouldReturnStock() throws Exception {
        Stock stock = new Stock();
        stock.setStock("AA");
        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);
        when(service.findByStock("AA")).thenReturn(stocks);
        this.mockMvc.perform(get("/stocks/search?tickerSymbol=AA")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("AA")));
    }

    @Test
    protected void givenStockShouldSave() throws Exception {
        Stock stock = new Stock();
        stock.setId(1L);
        stock.setStock("AA");
        stock.setQuarter("1");
        when(service.save(stock)).thenReturn(stock);
        this.mockMvc.perform(put("/stocks/create").contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(stock))).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("AA")));
    }
}
