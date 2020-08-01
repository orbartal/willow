package com.hackerrank.stocktrade.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;

@Service
public class TradeApplication {

	public void addTrade(AddTradeRequest trade) {		
	}

	public void deleteAllTrades() {		
	}

	public List<TradeResponse> readAllTrades() {
		return new ArrayList<>();
	}

	public List<TradeResponse> readAllTradesByUser(Long userID) {
		return new ArrayList<>();
	}

	public StockPricesResponse readStocksPricesByDateRange(Date startDate, Date endDate) {
		return new StockPricesResponse();
	}

	public List<StockStateResponse> readStocksStatsByDateRange(Date startDate, Date endDate) {
		return new ArrayList<>();
	}

}
