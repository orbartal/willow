package com.hackerrank.stocktrade.data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.logic.model.StockPricesInfo;
import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.model.TradeEntity;

@Service
@Transactional(readOnly = true)
public class TradeDataReader {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private DataMapper dataMapper;

	public List<TradeInfo> readAllTrades() {
		List<TradeEntity> entities = tradeRepository.findAll();
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public List<TradeInfo> readAllTradesByUser(Long userUid) {
		List<TradeEntity> entities = tradeRepository.readAllByUserUid(userUid);
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public List<String> readAllStocksSymbols() {
		return tradeRepository.readAllStocksSymbols();
	}

	public List<Double> readPricesBySymbolAndDateRangeOrderByTime(String symbol, Date startDate, Date endDate) {
		return tradeRepository.readPricesBySymbolAndDateRangeOrderByTime(symbol, startDate, endDate);
	}

	public StockPricesInfo readStocksPricesByDateRange(String symbol, Date startDate, Date endDate) {
		Long count = tradeRepository.countByStockSymbol(symbol);
		Double highest = tradeRepository.readHighestPriceBySymbolAndDateRange(symbol, startDate, endDate);
		Double lowest = tradeRepository.readLowestPriceBySymbolAndDateRange(symbol, startDate, endDate);
		return new StockPricesInfo(count, highest, lowest);
	}

}