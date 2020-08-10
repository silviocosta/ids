package com.challenge.ids.controller;

import com.challenge.ids.model.Stock;
import com.challenge.ids.service.StockService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping(value = "stocks")
@Api(value = "Service to get Stock data from Down Jones Index database", tags = "Stocks")
@Validated
public class StockController {

    private static final Logger LOG = LoggerFactory.getLogger(StockController.class);

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "List all", response = Stock.class)
    public ResponseEntity<List<Stock>> list() {
        return new ResponseEntity<>(stockService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ApiOperation(value = "Search stock by Ticker Symbol", response = Stock.class)
    public ResponseEntity<List<Stock>> search(@NotNull @RequestParam("tickerSymbol") String tickerSymbol) {
        return new ResponseEntity<>(stockService.findByStock(tickerSymbol), HttpStatus.OK);
    }

    @RequestMapping(value = "create", method = RequestMethod.PUT)
    @ApiOperation(value = "Add a new Stock entry", response = Stock.class)
    public ResponseEntity<Stock> create(@RequestBody Stock stock) {
        return new ResponseEntity<>(stockService.save(stock), HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        List<Stock> stocks;
        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty or invalid.", HttpStatus.BAD_REQUEST);
        } else {
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                CsvToBean<Stock> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Stock.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();
                stocks = csvToBean.parse();
                this.stockService.saveAll(stocks);
            } catch (Exception ex) {
                LOG.error("An error occurred while uploading the CSV file {}", ex.getLocalizedMessage(), ex);
                return new ResponseEntity<>("An error occurred while uploading the CSV file ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>("SUCCESS - Total of records added: " + stocks.size(), HttpStatus.OK);
    }
}
