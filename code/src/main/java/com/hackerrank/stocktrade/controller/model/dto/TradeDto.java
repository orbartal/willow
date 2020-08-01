package com.hackerrank.stocktrade.controller.model.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TradeDto {
    private Long id;
    private String type;
    private UserDto user;
    private String stockSymbol;
    private Integer stockQuantity;
    private Double stockPrice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp tradeTimestamp;

    public TradeDto() {
    }

    public TradeDto(Long id, String type, UserDto user, String stockSymbol, Integer stockQuantity, Double stockPrice, Timestamp tradeTimestamp) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
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
