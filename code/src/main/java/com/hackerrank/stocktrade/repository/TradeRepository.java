package com.hackerrank.stocktrade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hackerrank.stocktrade.repository.model.TradeEntity;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

	@Query(value = "SELECT t FROM TradeEntity t WHERE t.user.uid = :userUid")
	public List<TradeEntity> readAllByUserId(@Param("userUid") Long userUid);

}
