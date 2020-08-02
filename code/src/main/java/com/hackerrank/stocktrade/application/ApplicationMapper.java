package com.hackerrank.stocktrade.application;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hackerrank.stocktrade.controller.model.dto.UserDto;
import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockStateFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateNotFoundResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.exceptions.InvalidDateFormatException;
import com.hackerrank.stocktrade.logic.model.FluctuationInfo;
import com.hackerrank.stocktrade.logic.model.StockStateInfo;
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
		Timestamp time = input.getTimestamp();
		return new TradeInfo(input.getId(), type, userInfo, input.getSymbol(), input.getShares(), input.getPrice(), time);
	}

	public TradeResponse tradeInfoToResponse(TradeInfo input) {
		UserInfo userInfo = input.getUser();
		UserDto userDto = new UserDto();
		userDto.setId(userInfo.getId());
		userDto.setName(userInfo.getName());
		
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

	public Date stringToStartDate(String date) {
		return stringToDate("start", date);
	}

	public Date stringToEndDate(String date) {
		Date morning = stringToDate("end", date);
		return new Date(morning.getTime() + (1000 * 60 * 60 * 24)-1); //Until end of day
	}

	private Date stringToDate(String context, String date) {
		try {
			return DATE_FORMAT.parse(date);
		}catch(ParseException e) {
			throw new InvalidDateFormatException(context, date);
		}
	}

	public StockStateResponse stockStateInfoToResponse(StockStateInfo input) {
		if (!input.getFluctuation().isPresent()) {
			StockStateNotFoundResponse result = new StockStateNotFoundResponse();
			result.setSymbol(input.getSymbol());
			result.setMessage("There are no trades in the given date range");
			return result;
		}
		FluctuationInfo info = input.getFluctuation().get();
		StockStateFoundResponse result = new StockStateFoundResponse();
		result.setSymbol(input.getSymbol());
		result.setFluctuations(info.getFluctuations());
		result.setMaxFall(toBigDecimalDigit(info.getMaxRise(), 2));
		result.setMaxRise(toBigDecimalDigit(info.getMaxFall(), 2));
		return result;
	}

	private BigDecimal toBigDecimalDigit(Double amount, int digits) {
		return new BigDecimal(amount).setScale(digits, BigDecimal.ROUND_HALF_UP);
	}

}
