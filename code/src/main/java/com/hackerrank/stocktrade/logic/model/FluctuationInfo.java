package com.hackerrank.stocktrade.logic.model;

public class FluctuationInfo {

	private final int fluctuations; // This field describes the number of fluctuations
	private final Double maxRise; // This field is the maximum daily price increase for the period.
	private final Double maxFall; // This field is the maximum daily price decline for the period.

	public FluctuationInfo(int fluctuations, Double maxRise, Double maxFall) {
		this.fluctuations = fluctuations;
		this.maxRise = maxRise;
		this.maxFall = maxFall;
	}

	public int getFluctuations() {
		return fluctuations;
	}

	public Double getMaxRise() {
		return maxRise;
	}

	public Double getMaxFall() {
		return maxFall;
	}

}
