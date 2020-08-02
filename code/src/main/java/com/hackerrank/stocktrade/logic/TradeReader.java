package com.hackerrank.stocktrade.logic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.controller.model.response.StockStateFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateNotFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.exceptions.UserNotFoundException;
import com.hackerrank.stocktrade.logic.model.FluctuationInfo;
import com.hackerrank.stocktrade.logic.model.StockStateInfo;
import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.model.TradeEntity;


@Service
@Transactional(readOnly = true)
public class TradeReader {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private DataMapper dataMapper;

	public List<TradeInfo> readAllTrades() {
		List<TradeEntity> entities = tradeRepository.findAll();
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public List<TradeInfo> readAllTradesByUser(Long userID) {
		List<TradeEntity> entities = tradeRepository.readAllByUserId(userID);
		if (entities.isEmpty()) {
			throw new UserNotFoundException();
		}
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public Double readHighestPriceBySymbolAndDateRange(String symbol, Date startDate, Date endDate) {
		return tradeRepository.readHighestPriceBySymbolAndDateRange(symbol, startDate, endDate);
	}

	public Double readLowestPriceBySymbolAndDateRange(String symbol, Date startDate, Date endDate) {
		return tradeRepository.readLowestPriceBySymbolAndDateRange(symbol, startDate, endDate);
	}

	public List<String> readAllStocksSymbols() {
		return tradeRepository.readAllStocksSymbols();
	}

	public StockStateResponse buildStockStateResponse(StockStateInfo input) {
		if (!input.getFluctuation().isPresent()) {
			StockStateNotFoundResponse result = new StockStateNotFoundResponse();
			result.setSymbol(input.getSymbol());
			result.setMessage("There are no trades in the given date range");
			return result;
		}
		FluctuationInfo info = input.getFluctuation().get();
		StockStateFoundResponse result = new StockStateFoundResponse();
		result.setSymbol(input.getSymbol());
		result.setFluctuations(info.getFluctuations());
		result.setMaxFall(toBigDecimalDigit(info.getMaxRise(), 2));
		result.setMaxRise(toBigDecimalDigit(info.getMaxFall(), 2));
		return result;
	}

	public StockStateInfo readStockState(String symbol, Date startDate, Date endDate) {
		List<Double> prices = tradeRepository.readPricesBySymbolAndDateRangeOrderByTime(symbol, startDate, endDate);
		if (prices.isEmpty()) {
			return new StockStateInfo (symbol, null);
		}
		List<Double> prices2 = filterSamePriceFollowingDay(prices);
		if (prices2.size()==1) {
			return new StockStateInfo(symbol, new FluctuationInfo(0, 0.0, 0.0));
		}
		return new StockStateInfo(symbol, readStockStateInfo(prices2));
	}

	static private BigDecimal toBigDecimalDigit(Double amount, int digits) {
		return new BigDecimal(amount).setScale(digits, BigDecimal.ROUND_HALF_UP);
	}

	//TODO: Could this be written as a stream?
	private List<Double> filterSamePriceFollowingDay(List<Double> prices) {
		List<Double> results = new ArrayList<>();
		results.add(prices.get(0));
		for (int i = 1; i<prices.size(); i++) {
			if (prices.get(i-1)!=prices.get(i)) {
				results.add(prices.get(i));
			}
		}
		return results;
	}

	private FluctuationInfo readStockStateInfo(List<Double> prices) {
		Double maxRise = 0.0;
		Double maxFall = 0.0;
		int count = 0;
		Double prev = prices.get(0);
		Double current = prices.get(1);
		Boolean oldDirection = prev<current;
		for (int i = 1; i<prices.size(); i++) {
			current = prices.get(i);
			Boolean newDirection = prev<current;
			if (oldDirection!=newDirection) {
				oldDirection=newDirection;
				count++;
			}
			Double diff = Math.abs(current - prev);
			if (!newDirection && maxRise < diff) {
				maxRise = diff;
			}
			if (newDirection && maxFall < diff) {
				maxFall = diff;
			}
			prev = current;
		}
		return new FluctuationInfo(count, maxRise, maxFall);
	}

	public Long countTradeBySymbol(String symbol) {
		return tradeRepository.countByStockSymbol(symbol);
	}

}