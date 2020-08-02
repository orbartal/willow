package com.hackerrank.stocktrade.application;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockPricesNotFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.exceptions.StockSymbolNotFoundException;
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
		Date endDate2 = new Date(endDate.getTime() + (1000 * 60 * 60 * 24)-1); //Until end of day
		Double highest = tradeReader.readHighestPriceBySymbolAndDateRange(symbol, startDate, endDate2);
		Double lowest = tradeReader.readLowestPriceBySymbolAndDateRange(symbol, startDate, endDate2);
		if (highest == null || lowest == null) {
			Long count = tradeReader.countTradeBySymbol(symbol);
			if (count == 0) {
				throw new StockSymbolNotFoundException();
			}
			StockPricesNotFoundResponse response = new StockPricesNotFoundResponse();
			response.setMessage("There are no trades in the given date range");
			return response;
		}
		StockPricesFoundResponse response = new StockPricesFoundResponse();
		response.setSymbol(symbol);
		response.setHighest(highest);
		response.setLowest(lowest);
		return response;
	}

	public List<StockStateResponse> readStocksStatsByDateRange(String start, String end) {
		Date startDate = applicationMapper.stringToDate(start);
		Date endDate = applicationMapper.stringToDate(end);
		Date endDate2 = new Date(endDate.getTime() + (1000 * 60 * 60 * 24)-1); //Until end of day
		List<String> symbols = tradeReader.readAllStocksSymbols();
		return symbols.stream().map(s->tradeReader.readStockState(s, startDate, endDate2)).map(i->tradeReader.buildStockStateResponse(i)).collect(Collectors.toList());
	}

}
