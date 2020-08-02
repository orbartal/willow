package com.hackerrank.stocktrade.application;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.controller.model.dto.UserDto;
import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.logic.model.TradeType;
import com.hackerrank.stocktrade.logic.model.UserInfo;

@Service
public class ApplicationMapper {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public TradeInfo tradeRequestToInfo(AddTradeRequest input) {
		UserDto userDto = input.getUser();
		UserInfo userInfo = new UserInfo(userDto.getId(), userDto.getName());
		TradeType type = TradeType.valueOf(input.getType().toUpperCase());
		Timestamp time = input.getTradeTimestamp();
		return new TradeInfo(input.getId(), type, userInfo, input.getStockSymbol(), input.getStockQuantity(), input.getStockPrice(), time);
	}

	public TradeResponse tradeInfoToResponse(TradeInfo input) {
		UserInfo userInfo = input.getUser();
		UserDto userDto = new UserDto();
		userDto.setId(userInfo.getId());
		userDto.setName(userInfo.getName());
		
		TradeResponse output = new TradeResponse();
		output.setId(input.getId());
		output.setStockPrice(input.getStockPrice());
		output.setStockQuantity(input.getStockQuantity());
		output.setStockSymbol(input.getStockSymbol());
		output.setTradeTimestamp(input.getTradeTimestamp());
		output.setType(input.getType().name().toLowerCase());
		output.setUser(userDto);
		return output;
	}

	public Date stringToDate(String input) {
		try {
			return DATE_FORMAT.parse(input);
		}catch(ParseException e) {
			throw new RuntimeException();//TODO
		}
	}

}
