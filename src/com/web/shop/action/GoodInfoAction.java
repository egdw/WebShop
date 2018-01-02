package com.web.shop.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Good;
import com.web.shop.model.UserLeaveMessage;
import com.web.shop.service.GoodService;
import com.web.shop.service.UserService;

@Controller
@Scope(value = "prototype")
public class GoodInfoAction extends ActionSupport {

	@Autowired
	private GoodService service;
	@Autowired
	private UserService userService;
	@Autowired
	private SessionFactory sessionFactory;
	private int goodId;

	@Override
	public String execute() throws Exception {
		Good good = service.getGoodById(goodId);
		if (good == null) {
			return ERROR;
		} else {
			int goodWatchNum = good.getGoodWatchNum();
			good.setGoodWatchNum(good.getGoodWatchNum() + 1);
			Session session = sessionFactory.openSession();
			session.update(good);
			session.flush();
			session.close();
			List<String> asList = null;
			if (good.getImages() != null && !good.getImages().isEmpty()) {
				String[] split = good.getImages().split(";");
				asList = Arrays.asList(split);
			}
			ArrayList<UserLeaveMessage> list = userService
					.getUserLeaveMessages(good.getGoodId());
			((Map<String, Object>) ActionContext.getContext().get("request"))
					.put("says", list);
			((Map<String, Object>) ActionContext.getContext().get("request"))
					.put("images", asList);
			((Map<String, Object>) ActionContext.getContext().get("request"))
					.put("goodObject", good);
			((Map<String, Object>) ActionContext.getContext().get("request")).put("isSale", good.isGet());
			return SUCCESS;
		}
	}

	public int getGoodId() {
		return goodId;
	}

	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}

}
