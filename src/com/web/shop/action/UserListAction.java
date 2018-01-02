package com.web.shop.action;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Banner;
import com.web.shop.model.User;
import com.web.shop.service.BannerService;
import com.web.shop.service.UserManagerService;

@Controller
@Scope(value = "prototype")
public class UserListAction extends ActionSupport {
	private final int QUERYCOUNT = 10;
	private int pageCount;
	private int groupType;

	@Autowired
	private UserManagerService service;
	@Autowired
	private BannerService bannerService;

	@Override
	public String execute() throws Exception {
		if (groupType < 0 || groupType > 3) {
			return ERROR;
		}
		int userNum = service.getUserNum(groupType);
		double temp = userNum / QUERYCOUNT;
		int pageNum = 1;
		if (temp % 1 != 0) {
			pageNum = (int) (temp + 1);
		} else {
			pageNum = (int) temp;
		}
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().get("request");
		ArrayList<Banner> banners = bannerService.getBanners();
		request.put("images", banners);
		request.put("pageNum", pageNum);
		ArrayList<User> users = service.getUser(QUERYCOUNT, pageCount,
				groupType);
		request.put("users", users);

		Integer object = (Integer) ActionContext.getContext().getSession()
				.get("newPasswdTimes");
		if (object == null || object == 0) {
			ActionContext.getContext().getSession().put("newPasswdTimes", 1);
		} else {
			ActionContext.getContext().getSession().put("newPasswd", "");
			ActionContext.getContext().getSession().put("newPasswdTimes", 0);
		}
		return SUCCESS;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getGroupType() {
		return groupType;
	}

	public void setGroupType(int groupType) {
		this.groupType = groupType;
	}

}
