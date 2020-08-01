package com.hackerrank.stocktrade.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackerrank.stocktrade.logic.model.TradeInfo;
import com.hackerrank.stocktrade.repository.TradeRepository;
import com.hackerrank.stocktrade.repository.UserRepository;
import com.hackerrank.stocktrade.repository.model.TradeEntity;
import com.hackerrank.stocktrade.repository.model.UserEntity;


@Service
@Transactional(readOnly = false)
public class TradeWriter {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DataMapper dataMapper;

	public void deleteAllTrades() {
		tradeRepository.deleteAll();
	}

	public void addTrade(TradeInfo info) {
		TradeEntity entity = dataMapper.tradeInfoToEntity(info);
		UserEntity user = saveUser(entity);
		entity.setUser(user);
		tradeRepository.save(entity);
	}

	//We could make jpa to do it auto. But it easier to write for now.
	private UserEntity saveUser(TradeEntity entity) {
		List<UserEntity> users = userRepository.findByUid(entity.getUser().getUid());
		if (!users.isEmpty()) {
			return users.get(0);
		}
		return userRepository.save(entity.getUser()); 
	}

	
}