package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.GoodCompleteDaoImpl;
import com.web.shop.model.GoodComplete;

@Service
public class GoodCompleteService {
	@Autowired
	private GoodCompleteDaoImpl impl;

	@Transactional
	public ArrayList<GoodComplete> getCompleteGoods(int queryCount, int pageCount) {
		ArrayList<GoodComplete> completeGoods = impl.getCompleteGoods(queryCount,
				pageCount);
		return completeGoods;
	}

	@Transactional
	public int getCompleteGoodsNum() {
		int completeGoodsNum = impl.getCompleteGoodsNum();
		return completeGoodsNum;
	}

}
