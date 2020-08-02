package com.hackerrank.stocktrade.application;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.logic.TradeLogicReader;
import com.hackerrank.stocktrade.logic.TradeLogicWriter;
import com.hackerrank.stocktrade.logic.model.StockPricesInfo;
import com.hackerrank.stocktrade.logic.model.StockStateInfo;
import com.hackerrank.stocktrade.logic.model.TradeInfo;

@Service
public class TradeApplication {
	
	@Autowired
	private ApplicationMapper applicationMapper;
	
	@Autowired
	private TradeLogicReader tradeLogicReader;
	
	@Autowired
	private TradeLogicWriter tradeLogicWriter;

	public void addTrade(AddTradeRequest tradeRequest) {
		TradeInfo tradeInfo = applicationMapper.tradeRequestToInfo(tradeRequest);
		tradeLogicWriter.addTrade(tradeInfo);
	}

	public void deleteAllTrades() {	
		tradeLogicWriter.deleteAllTrades();
	}

	public List<TradeResponse> readAllTrades() {
		List<TradeInfo> infos = tradeLogicReader.readAllTrades();
		return infos.stream().map(t->applicationMapper.tradeInfoToResponse(t)).collect(Collectors.toList());
	}

	public List<TradeResponse> readAllTradesByUser(Long userID) {
		List<TradeInfo> infos = tradeLogicReader.readAllTradesByUser(userID);
		return infos.stream().map(t->applicationMapper.tradeInfoToResponse(t)).collect(Collectors.toList());
	}

	public StockPricesResponse readStocksPricesByDateRange(String symbol, String start, String end) {
		Date startDate = applicationMapper.stringToStartDate(start);
		Date endDate = applicationMapper.stringToEndDate(end);
		StockPricesInfo info = tradeLogicReader.readStocksPricesByDateRange(symbol, startDate, endDate);
		return applicationMapper.stocksPricesInfoToResponse(symbol, info);
	}

	public List<StockStateResponse> readStocksStatsByDateRange(String start, String end) {
		Date startDate = applicationMapper.stringToStartDate(start);
		Date endDate = applicationMapper.stringToEndDate(end);
		List<String> symbols = tradeLogicReader.readAllStocksSymbols();
		List<StockStateInfo> infos = symbols.stream().map(s->tradeLogicReader.readStockState(s, startDate, endDate)).collect(Collectors.toList());
		return infos.stream().map(i->applicationMapper.stockStateInfoToResponse(i)).collect(Collectors.toList());
	}

}
