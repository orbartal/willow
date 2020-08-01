package com.hackerrank.stocktrade.logic.model;

import java.sql.Timestamp;

public class TradeInfo {
	
 	private final Long id;
    private final TradeType type;
    private final UserInfo user;
    private final String stockSymbol;
    private final Integer stockQuantity;
    private final Double stockPrice;
    private final Timestamp tradeTimestamp;
    
	public TradeInfo(Long id, TradeType type, UserInfo user, String stockSymbol, Integer stockQuantity, Double stockPrice, Timestamp tradeTimestamp) {
		this.id = id;
		this.type = type;
		this.user = user;
		this.stockSymbol = stockSymbol;
		this.stockQuantity = stockQuantity;
		this.stockPrice = stockPrice;
		this.tradeTimestamp = tradeTimestamp;
	}

	public Long getId() {
		return id;
	}

	public TradeType getType() {
		return type;
	}

	public UserInfo getUser() {
		return user;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public Timestamp getTradeTimestamp() {
		return tradeTimestamp;
	}

}
