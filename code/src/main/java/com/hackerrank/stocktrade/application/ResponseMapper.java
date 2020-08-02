package com.hackerrank.stocktrade.application;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.hackerrank.stocktrade.controller.model.dto.UserDto;
import com.hackerrank.stocktrade.controller.model.response.StockPricesFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockPricesNotFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateNotFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.exceptions.InvalidDateFormatException;
import com.hackerrank.stocktrade.logic.model.FluctuationInfo;
import com.hackerrank.stocktrade.logic.model.StockPricesInfo;
import com.hackerrank.stocktrade.logic.model.StockStateInfo;
import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.logic.model.UserInfo;

@Component
public class ResponseMapper {
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public TradeResponse toTradeResponse(TradeInfo input, UserDto userDto) {
		TradeResponse output = new TradeResponse();
		output.setId(input.getId());
		output.setPrice(input.getStockPrice());
		output.setShares(input.getStockQuantity());
		output.setSymbol(input.getStockSymbol());
		output.setTimestamp(input.getTradeTimestamp());
		output.setType(input.getType().name().toLowerCase());
		output.setUser(userDto);
		return output;
	}

	public UserDto toUserDto(TradeInfo input) {
		UserInfo userInfo = input.getUser();
		UserDto userDto = new UserDto();
		userDto.setId(userInfo.getId());
		userDto.setName(userInfo.getName());
		return userDto;
	}

	public Date stringToDate(String context, String date) {
		try {
			return DATE_FORMAT.parse(date);
		}catch(ParseException e) {
			throw new InvalidDateFormatException(context, date);
		}
	}
	
	public StockStateResponse toStockStateFoundResponse(StockStateInfo input) {
		FluctuationInfo info = input.getFluctuation().get();
		StockStateFoundResponse result = new StockStateFoundResponse();
		result.setSymbol(input.getSymbol());
		result.setFluctuations(info.getFluctuations());
		result.setMaxFall(toBigDecimalDigit(info.getMaxRise(), 2));
		result.setMaxRise(toBigDecimalDigit(info.getMaxFall(), 2));
		return result;
	}

	public StockStateResponse toStockStateNotFoundResponse(StockStateInfo input) {
		StockStateNotFoundResponse result = new StockStateNotFoundResponse();
		result.setSymbol(input.getSymbol());
		result.setMessage("There are no trades in the given date range");
		return result;
	}

	private BigDecimal toBigDecimalDigit(Double amount, int digits) {
		return new BigDecimal(amount).setScale(digits, BigDecimal.ROUND_HALF_UP);
	}
	
	public StockPricesResponse toStockPricesNotFoundResponse() {
		StockPricesNotFoundResponse response = new StockPricesNotFoundResponse();
		response.setMessage("There are no trades in the given date range");
		return response;
	}

	public StockPricesResponse toStockPricesFoundResponse(String symbol, StockPricesInfo info) {
		StockPricesFoundResponse response = new StockPricesFoundResponse();
		response.setSymbol(symbol);
		response.setHighest(info.getHighest().get());
		response.setLowest(info.getLowest().get());
		return response;
	}

}
