package com.hackerrank.stocktrade.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hackerrank.stocktrade.logic.model.FluctuationInfo;

@Component
public class StockStateReader {

	//TODO: Could this be written as a stream?
	public List<Double> filterSamePriceFollowingDay(List<Double> prices) {
		List<Double> results = new ArrayList<>();
		results.add(prices.get(0));
		for (int i = 1; i<prices.size(); i++) {
			if (prices.get(i-1)!=prices.get(i)) {
				results.add(prices.get(i));
			}
		}
		return results;
	}

	public FluctuationInfo readStockStateInfo(List<Double> prices) {
		Double maxRise = 0.0;
		Double maxFall = 0.0;
		int count = 0;
		Double prev = prices.get(0);
		Double current = prices.get(1);
		Boolean oldDirection = prev<current;
		for (int i = 1; i<prices.size(); i++) {
			current = prices.get(i);
			Boolean newDirection = prev<current;
			if (oldDirection!=newDirection) {
				oldDirection=newDirection;
				count++;
			}
			Double diff = Math.abs(current - prev);
			if (!newDirection && maxRise < diff) {
				maxRise = diff;
			}
			if (newDirection && maxFall < diff) {
				maxFall = diff;
			}
			prev = current;
		}
		return new FluctuationInfo(count, maxRise, maxFall);
	}
}
