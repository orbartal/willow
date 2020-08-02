package com.hackerrank.stocktrade.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.data.TradeDataWriter;
import com.hackerrank.stocktrade.logic.model.TradeInfo;

@Service
public class TradeLogicWriter {

	@Autowired
	private TradeDataWriter tradeDataWriter;

	public void deleteAllTrades() {
		tradeDataWriter.deleteAllTrades();
	}

	public void addTrade(TradeInfo info) {
		tradeDataWriter.addTrade(info);
	}

}