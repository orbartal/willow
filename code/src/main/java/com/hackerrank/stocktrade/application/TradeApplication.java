package com.hackerrank.stocktrade.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.logic.TradeReader;
import com.hackerrank.stocktrade.logic.TradeWriter;
import com.hackerrank.stocktrade.logic.model.TradeInfo;

@Service
public class TradeApplication {
	
	@Autowired
	private ApplicationMapper applicationMapper;
	
	@Autowired
	private TradeReader tradeReader;
	
	@Autowired
	private TradeWriter tradeWriter;

	public void addTrade(AddTradeRequest tradeRequest) {
		TradeInfo tradeInfo = applicationMapper.tradeRequestToInfo(tradeRequest);
		tradeWriter.addTrade(tradeInfo);
	}

	public void deleteAllTrades() {	
		tradeWriter.deleteAllTrades();
	}

	public List<TradeResponse> readAllTrades() {
		List<TradeInfo> infos = tradeReader.readAllTrades();
		return infos.stream().map(t->applicationMapper.tradeInfoToResponse(t)).collect(Collectors.toList());
	}

	public List<TradeResponse> readAllTradesByUser(Long userID) {
		List<TradeInfo> infos = tradeReader.readAllTradesByUser(userID);
		return infos.stream().map(t->applicationMapper.tradeInfoToResponse(t)).collect(Collectors.toList());
	}

	public StockPricesResponse readStocksPricesByDateRange(String symbol, String start, String end) {
		Date startDate = applicationMapper.stringToDate(start);
		Date endDate = applicationMapper.stringToDate(end);
		Double highest = tradeReader.readHighestPriceBySymbolAndDateRange(symbol, startDate, endDate);
		Double lowest = tradeReader.readLowestPriceBySymbolAndDateRange(symbol, startDate, endDate); 
		StockPricesResponse response = new StockPricesResponse();
		response.setSymbol(symbol);
		response.setHighest(highest);
		response.setLowest(lowest);
		return response;
	}

	//TODO
	public List<StockStateResponse> readStocksStatsByDateRange(Date startDate, Date endDate) {
		return new ArrayList<>();
	}

}
