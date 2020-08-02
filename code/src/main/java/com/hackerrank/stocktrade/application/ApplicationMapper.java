package com.hackerrank.stocktrade.application;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hackerrank.stocktrade.controller.model.dto.UserDto;
import com.hackerrank.stocktrade.controller.model.request.AddTradeRequest;
import com.hackerrank.stocktrade.controller.model.response.StockPricesResponse;
import com.hackerrank.stocktrade.controller.model.response.StockStateResponse;
import com.hackerrank.stocktrade.controller.model.response.TradeResponse;
import com.hackerrank.stocktrade.exceptions.StockSymbolNotFoundException;
import com.hackerrank.stocktrade.logic.model.StockPricesInfo;
import com.hackerrank.stocktrade.logic.model.StockStateInfo;
import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.logic.model.TradeType;
import com.hackerrank.stocktrade.logic.model.UserInfo;

@Component
public class ApplicationMapper {

	@Autowired
	private ResponseMapper responseMapper;

	public TradeInfo tradeRequestToInfo(AddTradeRequest input) {
		UserDto userDto = input.getUser();
		UserInfo userInfo = new UserInfo(userDto.getId(), userDto.getName());
		TradeType type = TradeType.valueOf(input.getType().toUpperCase());
		Timestamp time = input.getTimestamp();
		return new TradeInfo(input.getId(), type, userInfo, input.getSymbol(), input.getShares(), input.getPrice(), time);
	}

	public TradeResponse tradeInfoToResponse(TradeInfo input) {
		UserDto userDto = responseMapper.toUserDto(input);
		return responseMapper.toTradeResponse(input, userDto);
	}

	public Date stringToStartDate(String date) {
		return responseMapper.stringToDate("start", date);
	}

	public Date stringToEndDate(String date) {
		Date morning = responseMapper.stringToDate("end", date);
		return new Date(morning.getTime() + (1000 * 60 * 60 * 24)-1); //Until end of day
	}

	public StockStateResponse stockStateInfoToResponse(StockStateInfo input) {
		if (!input.getFluctuation().isPresent()) {
			return responseMapper.toStockStateNotFoundResponse(input);
		}
		return responseMapper.toStockStateFoundResponse(input);
	}

	public StockPricesResponse stocksPricesInfoToResponse(String symbol, StockPricesInfo info) {
		if (!info.getHighest().isPresent() || !info.getLowest().isPresent()) {
			if (info.getCount() == 0) {
				throw new StockSymbolNotFoundException();
			}
			return responseMapper.toStockPricesNotFoundResponse();
		}
		return responseMapper.toStockPricesFoundResponse(symbol, info);
	}

}
