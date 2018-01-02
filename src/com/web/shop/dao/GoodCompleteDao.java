package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.GoodComplete;

@Repository
public interface GoodCompleteDao {
	public ArrayList<GoodComplete> getCompleteGoods(int queryCount, int pageCount);

	public int getCompleteGoodsNum();
}
