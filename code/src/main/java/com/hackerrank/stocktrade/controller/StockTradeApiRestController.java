package com.hackerrank.stocktrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hackerrank.stocktrade.application.TradeApplication;
import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.exceptions.StockSymbolNotFoundException;
import com.hackerrank.stocktrade.exceptions.UserNotFoundException;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StockTradeApiRestController {

	@Autowired
	private TradeApplication tradeApplication;
	
	@ApiOperation(value = "Adding new trades")
	@ApiResponses({
        @ApiResponse(code = 201, message = "Success"),
       	@ApiResponse(code = 400, message = "Trade with the same id already exists"),
	})
    @RequestMapping(value = "/trades", method = RequestMethod.POST)
	public ResponseEntity<Void> addTrade(@RequestBody AddTradeRequest trade) throws RuntimeException {
		tradeApplication.addTrade(trade);
    	return ResponseEntity.status(HttpStatus.CREATED).build();	
    }

	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Trade with the same id already exists")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void error(DataIntegrityViolationException ex) {}

	@ApiOperation(value = "Erasing all the trades")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
	})
    @DeleteMapping("/erase")
    public ResponseEntity<Void> erase() {
		tradeApplication.deleteAllTrades();
    	return ResponseEntity.ok().build();	
    }

	@ApiOperation(value = "Returning all the trades")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
	})
    @RequestMapping(value = "/trades", method = RequestMethod.GET)
	public ResponseEntity<List<TradeResponse>> getAllTrades() throws RuntimeException {
		List<TradeResponse> data = tradeApplication.readAllTrades();
    	return ResponseEntity.ok(data);	
	}

	@ApiOperation(value = "Returning the trade records filtered by the user ID")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
       	@ApiResponse(code = 404, message = "Requested user does not exist"),
	})
    @RequestMapping(value = "/trades/users/{userID}", method = RequestMethod.GET)
	public ResponseEntity<List<TradeResponse>> getAllTradesByUser(@PathVariable("userID") Long userID) throws RuntimeException {
		List<TradeResponse> data = tradeApplication.readAllTradesByUser(userID);
    	return ResponseEntity.ok(data);	
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Requested user does not exist")
	@ExceptionHandler(UserNotFoundException.class)
	public void error(UserNotFoundException ex) {}
    
	@ApiOperation(value = "Returning the highest and lowest price for the stock symbol in the given date range")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success"),
       	@ApiResponse(code = 404, message = "Requested stock symbol does not exist"),
	})
    @RequestMapping(value = "/stocks/{stockSymbol}/price", method = RequestMethod.GET)
	public ResponseEntity<StockPricesResponse> getStocksPricesByDateRange(
			@PathVariable("stockSymbol") String stockSymbol,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate")  String endDate
			) throws RuntimeException {
		StockPricesResponse data = tradeApplication.readStocksPricesByDateRange(stockSymbol, startDate, endDate);
    	return ResponseEntity.ok(data);	
	}

	@ApiOperation(value = "Returning the fluctuations count, maximum daily rise and maximum daily fall for each stock symbol for the period in the given date range")
	@ApiResponses({
        @ApiResponse(code = 200, message = "Success")
	})
    @RequestMapping(value = "/stocks/stats", method = RequestMethod.GET)
	public ResponseEntity<List<StockStateResponse>> getStocksStatsByDateRange(
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate")  String endDate
			) throws RuntimeException {
		List<StockStateResponse> data = tradeApplication.readStocksStatsByDateRange(startDate, endDate);
    	return ResponseEntity.ok(data);	
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Requested stock symbol does not exist")
	@ExceptionHandler(StockSymbolNotFoundException.class)
	public void error(StockSymbolNotFoundException ex) {}
    
	

}
