package com.hackerrank.stocktrade.logic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.exceptions.UserNotFoundException;
import com.hackerrank.stocktrade.logic.model.FluctuationInfo;
import com.hackerrank.stocktrade.logic.model.StockPricesInfo;
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

	@Autowired
	private StockStateReader stockStateReader;

	public List<TradeInfo> readAllTrades() {
		List<TradeEntity> entities = tradeRepository.findAll();
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public List<TradeInfo> readAllTradesByUser(Long userUid) {
		List<TradeEntity> entities = tradeRepository.readAllByUserUid(userUid);
		if (entities.isEmpty()) {
			throw new UserNotFoundException();
		}
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public List<String> readAllStocksSymbols() {
		return tradeRepository.readAllStocksSymbols();
	}

	public StockStateInfo readStockState(String symbol, Date startDate, Date endDate) {
		List<Double> prices = tradeRepository.readPricesBySymbolAndDateRangeOrderByTime(symbol, startDate, endDate);
		if (prices.isEmpty()) {
			return new StockStateInfo (symbol, null);
		}
		List<Double> prices2 = stockStateReader.filterSamePriceFollowingDay(prices);
		if (prices2.size()==1) {
			return new StockStateInfo(symbol, new FluctuationInfo(0, 0.0, 0.0));
		}
		return new StockStateInfo(symbol, stockStateReader.readStockStateInfo(prices2));
	}

	public StockPricesInfo readStocksPricesByDateRange(String symbol, Date startDate, Date endDate) {
		Long count = tradeRepository.countByStockSymbol(symbol);
		Double highest = tradeRepository.readHighestPriceBySymbolAndDateRange(symbol, startDate, endDate);
		Double lowest = tradeRepository.readLowestPriceBySymbolAndDateRange(symbol, startDate, endDate);
		return new StockPricesInfo(count, highest, lowest);
	}

}