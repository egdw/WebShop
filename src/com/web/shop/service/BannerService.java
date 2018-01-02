package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.shop.daoImpl.BannerDaoImpl;
import com.web.shop.model.Banner;

@Service
public class BannerService {
	@Autowired
	private BannerDaoImpl bannerDaoImpl;

	@Transactional
	public ArrayList<Banner> getBanners() {
		return bannerDaoImpl.getBanners();
	}

	@Transactional
	public boolean delBanner(int bannerId) {
		boolean delBanner = bannerDaoImpl.delBanner(bannerId);
		return delBanner;
	}
}
