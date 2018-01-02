package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.GoodDaoImpl;
import com.web.shop.model.Good;

@Service
public class GoodService {

	@Autowired
	private GoodDaoImpl daoImpl;

	@Transactional
	public ArrayList<Good> getUserGoods(int queryCount, int pageCount) {
		ArrayList<Good> goods = daoImpl.getUserGoods(queryCount, pageCount);
		return goods;
	}

	/**
	 * 获取用户的商品数量
	 * 
	 * @return
	 */
	@Transactional
	public int getUserGoodsNum() {
		int num = daoImpl.getUserGoodsNum();
		return num;
	}

	@Transactional
	public ArrayList<Good> getLatestGoods(int queryCount, boolean flag) {
		ArrayList<Good> list = daoImpl.getLatestGoods(queryCount, flag);
		return list;
	}

	@Transactional
	public Good getGoodById(int id) {
		Good goodById = daoImpl.getGoodById(id);
		return goodById;
	}

	@Transactional
	public ArrayList<Good> getLatestGoodsByUser(int queryCount, int userId) {
		ArrayList<Good> latestGoodsByUser = daoImpl.getLatestGoodsByUser(
				queryCount, userId);
		return latestGoodsByUser;
	}

	@Transactional
	public boolean delGood(int goodId) {
		boolean delGood = daoImpl.delGood(goodId);
		return delGood;
	}

	@Transactional
	public boolean delGoodsByUserId(int userId) {
		boolean delGoodsByUserId = daoImpl.delGoodsByUserId(userId);
		return delGoodsByUserId;
	}

	@Transactional
	public ArrayList<Good> getUserGoodsByType(int queryCount, int pageCount,
			int type) {
		ArrayList<Good> userGoodsByType = daoImpl.getUserGoodsByType(
				queryCount, pageCount, type);
		return userGoodsByType;
	}

	@Transactional
	public int getUserFindByNameCount(String regex) {
		return daoImpl.getUserFindByNameCount(regex);
	}

	@Transactional
	public int getUserFindByTypeCount(int type) {
		int typeCount = daoImpl.getUserFindByTypeCount(type);
		return typeCount;
	}

	@Transactional
	public ArrayList<Good> getGoodsByFindName(String regex, int queryCount,
			int pageCount) {
		ArrayList<Good> arrayList = daoImpl.getGoodsByFindName(regex,
				queryCount, pageCount);
		return arrayList;
	}

	@Transactional
	public boolean saleComplete(int goodId, int userId) {
		boolean saleComplete = daoImpl.saleComplete(goodId, userId);
		return saleComplete;
	}

	@Transactional
	public ArrayList<Good> getUserSaledGoods(int queryCount, int pageCount) {
		ArrayList<Good> saledGoods = daoImpl.getUserSaledGoods(queryCount,
				pageCount);
		return saledGoods;
	}

	@Transactional
	public int getUserGoodsSaleNum() {
		int saleNum = daoImpl.getUserGoodsSaleNum();
		return saleNum;
	}
}
