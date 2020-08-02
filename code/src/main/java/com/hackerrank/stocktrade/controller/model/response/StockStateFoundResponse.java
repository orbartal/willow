package com.hackerrank.stocktrade.controller.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockStateFoundResponse implements StockStateResponse {

	private String symbol; // The stock symbol for the requested stock.
	private int fluctuations; // This field describes the number of fluctuations
	@JsonProperty("max_rise")
	private BigDecimal maxRise; // This field is the maximum daily price increase for the period.
	@JsonProperty("max_fall")
	private BigDecimal maxFall; // This field is the maximum daily price decline for the period.

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

	public BigDecimal getMaxRise() {
		return maxRise;
	}

	public void setMaxRise(BigDecimal maxRise) {
		this.maxRise = maxRise;
	}

	public BigDecimal getMaxFall() {
		return maxFall;
	}

	public void setMaxFall(BigDecimal maxFall) {
		this.maxFall = maxFall;
	}

}
