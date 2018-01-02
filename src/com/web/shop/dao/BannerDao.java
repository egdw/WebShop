package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.Banner;

@Repository
public interface BannerDao {
	public ArrayList<Banner> getBanners();

	public boolean delBanner(int bannerId);
}
