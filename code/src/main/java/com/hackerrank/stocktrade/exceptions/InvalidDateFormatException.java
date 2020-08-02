package com.hackerrank.stocktrade.exceptions;

@SuppressWarnings("serial")
public class InvalidDateFormatException extends RuntimeException {

	public InvalidDateFormatException(String context, String input) {
		super("input " + context + " has invalid date format: " + input);
	}

}
