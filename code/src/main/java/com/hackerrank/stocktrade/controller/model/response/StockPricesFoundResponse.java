package com.hackerrank.stocktrade.controller.model.response;

public class StockPricesFoundResponse implements StockPricesResponse {

	private String symbol; // the symbol for the requested stock
	private Double highest; // the highest price for the requested stock symbol in the given date range
	private Double lowest; // the lowest price for the requested stock symbol in the given date range

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Double getHighest() {
		return highest;
	}
	public void setHighest(Double highest) {
		this.highest = highest;
	}
	public Double getLowest() {
		return lowest;
	}
	public void setLowest(Double lowest) {
		this.lowest = lowest;
	}

}
