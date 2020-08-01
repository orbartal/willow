package com.hackerrank.stocktrade.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.repository.TradeRepository;


@Service
@Transactional(readOnly = false)
public class TradeWriter {

	@Autowired
	private TradeRepository tradeRepository;

	public void deleteAllTrades() {
		// TODO Auto-generated method stub
		
	}

	public void addTrade(TradeInfo tradeInfo) {
		// TODO Auto-generated method stub
		
	}

	
}