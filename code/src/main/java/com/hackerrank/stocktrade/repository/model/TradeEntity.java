package com.hackerrank.stocktrade.repository.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hackerrank.stocktrade.logic.model.TradeType;

@Entity
@Table(name = "trade")
public class TradeEntity extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private TradeType type;
	
	@JoinColumn(name = "user_id", nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity user;
	
	@Column(name = "stockSymbol")
	private String stockSymbol;
	
	@Column(name = "stockQuantity")
	private Integer stockQuantity;
	
	@Column(name = "stockPrice")
	private Double stockPrice;
	
	@Column(name = "tradeTimestamp")
	private Timestamp tradeTimestamp;

	public TradeType getType() {
		return type;
	}

	public void setType(TradeType type) {
		this.type = type;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(Integer stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public Double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(Double stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Timestamp getTradeTimestamp() {
		return tradeTimestamp;
	}

	public void setTradeTimestamp(Timestamp tradeTimestamp) {
		this.tradeTimestamp = tradeTimestamp;
	}

}
