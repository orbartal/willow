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

import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.model.Trade;

import io.swagger.annotations.*;

@RestController
public class StockTradeApiRestController {

    //
	@ApiOperation(value = "Adding new trades")
	@ApiResponses({
        @ApiResponse(code = 201, message = "Success"),
       	@ApiResponse(code = 400, message = "Trade with the same id already exists"),
	})
    @RequestMapping(value = "/trades", method = RequestMethod.POST)
	public ResponseEntity<Void> addTrade(@RequestBody AddTradeRequest trade) throws RuntimeException {
    	return ResponseEntity.ok().build();	
    }

	@ApiOperation(value = "Erasing all the trades")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
	})
    @DeleteMapping("/erase")
    public ResponseEntity<Void> erase() {
    	return ResponseEntity.ok().build();	
    }

	@ApiOperation(value = "Returning all the trades")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
	})
    @RequestMapping(value = "/trades", method = RequestMethod.GET)
	public ResponseEntity<List<TradeResponse>> getAllTrades() throws RuntimeException {
    	return ResponseEntity.ok(new ArrayList<>());	
	}

	@ApiOperation(value = "Returning the trade records filtered by the user ID")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
       	@ApiResponse(code = 404, message = "Requested user does not exist"),
	})
    @RequestMapping(value = "/trades/users/{userID}", method = RequestMethod.GET)
	public ResponseEntity<List<TradeResponse>> getAllTradesByUser(@PathVariable("userID") Long userID) throws RuntimeException {
    	return ResponseEntity.ok(new ArrayList<>());	
	}
    
	@ApiOperation(value = "Returning the highest and lowest price for the stock symbol in the given date range")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
       	@ApiResponse(code = 404, message = "Requested stock symbol does not exist"),
	})
    @RequestMapping(value = "/stocks/{stockSymbol}/price?start={startDate}&end={endDate}", method = RequestMethod.GET)
	public ResponseEntity<StockPricesResponse> getStocksPricesByDateRange(
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) throws RuntimeException {
    	return ResponseEntity.ok(new StockPricesResponse());	
	}

	@ApiOperation(value = "Returning the fluctuations count, maximum daily rise and maximum daily fall for each stock symbol for the period in the given date range")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
       	@ApiResponse(code = 404, message = "Requested stock symbol does not exist"),
	})
    @RequestMapping(value = "/stocks/stats?start={startDate}&end={endDate}", method = RequestMethod.GET)
	public ResponseEntity<List<StockStateResponse>> getStocksStatsByDateRange(
			@PathVariable("startDate") Date startDate,
			@PathVariable("endDate") Date endDate) throws RuntimeException {
    	return ResponseEntity.ok(new ArrayList<>());	
	}

}
