package com.hackerrank.stocktrade.logic;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return entities.stream().map(e->dataMapper.tradeEntityToInfo(e)).collect(Collectors.toList());
	}

	public Double readHighestPriceBySymbolAndDateRange(String symbol, Date startDate, Date endDate) {
		return tradeRepository.readHighestPriceBySymbolAndDateRange(symbol, startDate, endDate);
	}

	public Double readLowestPriceBySymbolAndDateRange(String symbol, Date startDate, Date endDate) {
		return tradeRepository.readLowestPriceBySymbolAndDateRange(symbol, startDate, endDate);
	}

}