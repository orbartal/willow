package com.hackerrank.stocktrade.controller.model.response;

public class StockPricesNotFoundResponse implements StockPricesResponse {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
