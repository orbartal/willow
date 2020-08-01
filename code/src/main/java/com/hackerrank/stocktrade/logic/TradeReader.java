package com.hackerrank.stocktrade.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.repository.TradeRepository;


@Service
@Transactional(readOnly = true)
public class TradeReader {

	@Autowired
	private TradeRepository tradeRepository;

	public List<TradeInfo> readAllTrades() {
		return new ArrayList<>();
	}

	public List<TradeInfo> readAllTradesByUser(Long userID) {
		return new ArrayList<>();
	}

	public Double readHighestPriceBySymbolAndDateRange(String symbol, Date startDate, Date endDate) {
		return null;
	}

	public Double readLowestPriceBySymbolAndDateRange(String symbol, Date startDate, Date endDate) {
		return null;
	}

}