package com.hackerrank.stocktrade.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hackerrank.stocktrade.repository.model.TradeEntity;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

	@Query(value = "SELECT t FROM TradeEntity t WHERE t.user.uid = :userUid")
	public List<TradeEntity> readAllByUserUid(@Param("userUid") Long userUid);
	
	@Query(value = "SELECT MAX (t.stockPrice) FROM TradeEntity t WHERE t.stockSymbol = :symbol AND "
			+ "t.tradeTimestamp >= :startDate AND t.tradeTimestamp <= :endDate")
	public Double readHighestPriceBySymbolAndDateRange(
			@Param("symbol") String symbol,
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate);
	
	@Query(value = "SELECT MIN (t.stockPrice) FROM TradeEntity t WHERE t.stockSymbol = :symbol AND "
			+ "t.tradeTimestamp >= :startDate AND t.tradeTimestamp <= :endDate")
	public Double readLowestPriceBySymbolAndDateRange(
			@Param("symbol") String symbol,
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate);

	@Query(value = "SELECT DISTINCT t.stockSymbol FROM TradeEntity t ORDER BY t.stockSymbol")
	public List<String> readAllStocksSymbols();

	@Query(value = "SELECT t.stockPrice FROM TradeEntity t WHERE t.stockSymbol = :symbol AND "
			+ "t.tradeTimestamp >= :startDate AND t.tradeTimestamp <= :endDate "
			+ "ORDER BY t.tradeTimestamp ASC")
	public List<Double> readPricesBySymbolAndDateRangeOrderByTime(
			@Param("symbol") String symbol,
			@Param("startDate") Date startDate, 
			@Param("endDate") Date endDate);

	@Query(value = "SELECT COUNT(t) FROM TradeEntity t WHERE t.stockSymbol = :symbol")
	public Long countByStockSymbol(@Param("symbol") String symbol);

}
