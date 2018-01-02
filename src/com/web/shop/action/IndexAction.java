package com.web.shop.action;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Good;
import com.web.shop.service.BannerService;
import com.web.shop.service.GoodService;

@Controller
@Scope(value = "prototype")
public class IndexAction extends ActionSupport {
	@Autowired
	private GoodService service;
	@Autowired
	private BannerService bannerService;

	@Override
	public String execute() throws Exception {
		ArrayList<Good> twoHandGoodList = service.getLatestGoods(10, false);
		ArrayList<Good> newGoodList = service.getLatestGoods(10, true);
		((Map<String, Object>) ActionContext.getContext().get("request")).put(
				"twoHandGoodList", twoHandGoodList);
		((Map<String, Object>) ActionContext.getContext().get("request")).put(
				"newGoodList", newGoodList);
		((Map<String, Object>) ActionContext.getContext().get("request")).put(
				"images", bannerService.getBanners());
		return SUCCESS;
	}
}
