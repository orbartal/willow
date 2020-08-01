package com.hackerrank.stocktrade.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.model.Trade;

@RestController
public class StockTradeApiRestController {

    //Adding new trades
    @RequestMapping(value = "/trades", method = RequestMethod.POST)
	public ResponseEntity<Void> addTrade(@RequestBody Trade trade) throws RuntimeException {
    	return ResponseEntity.ok().build();	
    }

	//Erasing all the trades
    @DeleteMapping("/erase")
    public ResponseEntity<Void> erase() {
    	return ResponseEntity.ok().build();	
    }

    //Returning all the trades
    @RequestMapping(value = "/trades", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getAllTrades() throws RuntimeException {
    	return ResponseEntity.ok(new ArrayList<>());	
	}
    
    //Returning the trade records filtered by the user ID
    @RequestMapping(value = "/trades/users/{userID}", method = RequestMethod.GET)
	public ResponseEntity<List<Trade>> getAllTradesByUser(@PathVariable("userID") Long userID) throws RuntimeException {
    	return ResponseEntity.ok(new ArrayList<>());	
	}
    
    //Returning the highest and lowest price for the stock symbol in the given date range
    @RequestMapping(value = "/stocks/{stockSymbol}/price?start={startDate}&end={endDate}", method = RequestMethod.GET)
	public ResponseEntity<StockPricesResponse> getStocksPricesByDateRange(
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) throws RuntimeException {
    	return ResponseEntity.ok(new StockPricesResponse());	
	}
    
    //Returning the fluctuations count, maximum daily rise and maximum daily fall for each stock symbol for the period in the given date range
    @RequestMapping(value = "/stocks/stats?start={startDate}&end={endDate}", method = RequestMethod.GET)
	public ResponseEntity<StockStateResponse> getStocksStatsByDateRange(
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) throws RuntimeException {
    	return ResponseEntity.ok(new StockStateResponse());	
	}

}
