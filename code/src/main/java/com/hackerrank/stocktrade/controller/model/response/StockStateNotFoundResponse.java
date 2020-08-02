package com.hackerrank.stocktrade.controller.model.response;

public class StockStateNotFoundResponse  implements StockStateResponse {

	private String symbol; // The stock symbol for the requested stock.
	private String message; //Message in case no data for the symbol

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
