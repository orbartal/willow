package com.hackerrank.stocktrade.controller.model.response;

public class StockStateResponse {

	private String symbol; // The stock symbol for the requested stock.
	private int fluctuations; // This field describes the number of fluctuations
	private Double maxRise; // This field is the maximum daily price increase for the period.
	private Double maxFall; // This field is the maximum daily price decline for the period.
	private String message; //Message in case no data for the symbol

	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getFluctuations() {
		return fluctuations;
	}
	public void setFluctuations(int fluctuations) {
		this.fluctuations = fluctuations;
	}
	public Double getMaxRise() {
		return maxRise;
	}
	public void setMaxRise(Double maxRise) {
		this.maxRise = maxRise;
	}
	public Double getMaxFall() {
		return maxFall;
	}
	public void setMaxFall(Double maxFall) {
		this.maxFall = maxFall;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
