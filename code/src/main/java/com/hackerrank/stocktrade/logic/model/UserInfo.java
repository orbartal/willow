package com.hackerrank.stocktrade.logic.model;

public class UserInfo {

    private final Long id;
    private final String name;

	public UserInfo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
