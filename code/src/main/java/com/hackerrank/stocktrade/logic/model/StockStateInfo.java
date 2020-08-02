package com.hackerrank.stocktrade.logic.model;

import java.util.Optional;

public class StockStateInfo {

	private final String symbol;
	private final Optional<FluctuationInfo> fluctuation;

	public StockStateInfo(String symbol, FluctuationInfo fluctuation) {
		this.symbol = symbol;
		this.fluctuation = Optional.ofNullable(fluctuation);
	}

	public String getSymbol() {
		return symbol;
	}

	public Optional<FluctuationInfo> getFluctuation() {
		return fluctuation;
	}

}
