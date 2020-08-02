package com.hackerrank.stocktrade.logic.model;

import java.util.Optional;

public class StockPricesInfo {

	private final Long count;
	private final Optional<Double> highest;
	private final Optional<Double> lowest;

	public StockPricesInfo(Long count, Double highest, Double lowest) {
		this.count = count;
		this.highest = Optional.ofNullable(highest);
		this.lowest = Optional.ofNullable(lowest);
	}

	public Long getCount() {
		return count;
	}

	public Optional<Double> getHighest() {
		return highest;
	}

	public Optional<Double> getLowest() {
		return lowest;
	}

}
