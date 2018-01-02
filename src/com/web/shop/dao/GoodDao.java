package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.Good;

@Repository
public interface GoodDao {
	/**
	 * 
	 * @param queryCount
	 *            每次查询的数量
	 * @param pageCount
	 *            当前需要查询的页数
	 * @return
	 */
	public ArrayList<Good> getUserGoods(int queryCount, int pageCount);

	/**
	 * 查询用户已售出的商品
	 * 
	 * @param queryCount
	 *            每次查询的数量
	 * @param pageCount
	 *            当前需要查询的页数
	 * @return
	 */
	public ArrayList<Good> getUserSaledGoods(int queryCount, int pageCount);

	/**
	 * 查询所有用户发布的商品
	 * 
	 * @param queryCount
	 *            每次查询的数量
	 * @param pageCount
	 *            当前需要查询的页数
	 * @param type
	 *            //所选的类型 0 代表所有类型
	 * @return
	 */
	public ArrayList<Good> getUserGoodsByType(int queryCount, int pageCount,
			int type);

	public int getUserGoodsSaleNum();

	public int getUserGoodsNum();

	/**
	 * 
	 * @param queryCount
	 *            每次查询的数量
	 * @param pageCount
	 * @param flag
	 *            true:全新闲置 false:最新闲置
	 * @return
	 */
	public ArrayList<Good> getLatestGoods(int queryCount, boolean flag);

	/**
	 * 根据订单号查询good对象
	 * 
	 * @param id
	 * @return
	 */
	public Good getGoodById(int id);

	/**
	 * 
	 * @param queryCount
	 *            每次查询的数量
	 * @param flag
	 *            true:全新闲置 false:最新闲置
	 * @param userId
	 *            用户编号
	 * @return
	 */
	public ArrayList<Good> getLatestGoodsByUser(int queryCount, int userId);

	public boolean delGood(int goodId);

	/**
	 * 删除有关于用户的所有商品
	 */
	public boolean delGoodsByUserId(long userId);

	public int getUserFindByNameCount(String regex);

	public int getUserFindByTypeCount(int type);

	public ArrayList<Good> getGoodsByFindName(String regex, int queryCount,
			int pageCount);

	public Good getGood(int goodId);

	public boolean saleComplete(Integer goodId, Integer userId);

}
