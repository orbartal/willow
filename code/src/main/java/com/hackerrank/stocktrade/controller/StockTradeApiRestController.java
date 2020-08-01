package com.hackerrank.stocktrade.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class StockTradeApiRestController {
    public StockTradeApiRestController() {
        ArrayList<String> trades = new ArrayList<String>();
        ArrayList<String> users = new ArrayList<String>();
    }

    private ArrayList<String> trades;
    private ArrayList<String> users;

    @DeleteMapping("/erase")
    void erase() {
        trades.clear();
    }
}
