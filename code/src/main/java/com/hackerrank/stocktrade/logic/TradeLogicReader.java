package com.hackerrank.stocktrade.logic;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.data.TradeDataReader;
import com.hackerrank.stocktrade.exceptions.UserNotFoundException;
import com.hackerrank.stocktrade.logic.model.FluctuationInfo;
import com.hackerrank.stocktrade.logic.model.StockPricesInfo;
import com.hackerrank.stocktrade.logic.model.StockStateInfo;
import com.hackerrank.stocktrade.logic.model.TradeInfo;

@Service
public class TradeLogicReader {

	@Autowired
	private TradeDataReader tradeDataReader;

	@Autowired
	private StockStateReader stockStateReader;

	public List<TradeInfo> readAllTrades() {
		return tradeDataReader.readAllTrades();
	}

	public StockPricesInfo readStocksPricesByDateRange(String symbol, Date startDate, Date endDate) {
		return tradeDataReader.readStocksPricesByDateRange(symbol, startDate, endDate);
	}

	public List<String> readAllStocksSymbols() {
		return tradeDataReader.readAllStocksSymbols();
	}

	public List<TradeInfo> readAllTradesByUser(Long userUid) {
		List<TradeInfo> info = tradeDataReader.readAllTradesByUser(userUid);
		if (info.isEmpty()) {
			throw new UserNotFoundException();
		}
		return info;
	}

	public StockStateInfo readStockState(String symbol, Date startDate, Date endDate) {
		List<Double> prices = tradeDataReader.readPricesBySymbolAndDateRangeOrderByTime(symbol, startDate, endDate);
		if (prices.isEmpty()) {
			return new StockStateInfo (symbol, null);
		}
		List<Double> distinctPrices = stockStateReader.filterSamePriceFollowingDay(prices);
		if (distinctPrices.size()==1) {
			return new StockStateInfo(symbol, new FluctuationInfo(0, 0.0, 0.0));
		}
		return new StockStateInfo(symbol, stockStateReader.readStockStateInfo(distinctPrices));
	}

}