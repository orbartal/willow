package com.hackerrank.stocktrade.logic;

import java.sql.Timestamp;

import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.logic.model.TradeType;
import com.hackerrank.stocktrade.logic.model.UserInfo;
import com.hackerrank.stocktrade.repository.model.TradeEntity;
import com.hackerrank.stocktrade.repository.model.UserEntity;

@Service
public class DataMapper {

	public TradeInfo tradeEntityToInfo(TradeEntity e) {
		Long id = e.getUid();
	    TradeType type = e.getType();
	    UserInfo user = new UserInfo (e.getUser().getUid(), e.getUser().getName());
	    String stockSymbol = e.getStockSymbol();
	    Integer stockQuantity = e.getStockQuantity();
	    Double stockPrice = e.getStockPrice();
	    Timestamp tradeTimestamp = e.getTradeTimestamp();
		return new TradeInfo(id, type, user, stockSymbol, stockQuantity, stockPrice, tradeTimestamp);
	}

	public TradeEntity tradeInfoToEntity(TradeInfo info) {
		UserEntity user = new UserEntity();
		user.setUid(info.getUser().getId());
		user.setName(info.getUser().getName());

		TradeEntity e = new TradeEntity();
		e.setUid(info.getId());
	    e.setType(info.getType());
	    e.setUser(user);
	    e.setStockSymbol(info.getStockSymbol());
	    e.setTradeTimestamp(info.getTradeTimestamp());
	    e.setStockPrice(info.getStockPrice());
	    e.setStockQuantity(info.getStockQuantity());
		return e;
	}

}
