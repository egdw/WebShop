package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.BannerService;

@Controller
@Scope(value = "prototype")
public class BannerDelAction extends ActionSupport {
	private int bannerId;
	@Autowired
	private BannerService bannerService;

	@Override
	public String execute() throws Exception {
		boolean delBanner = bannerService.delBanner(bannerId);
		if (delBanner) {
			return SUCCESS;
		} else {
			addActionError("删除图片成功");
			return ERROR;
		}
	}

	public int getBannerId() {
		return bannerId;
	}

	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

}
