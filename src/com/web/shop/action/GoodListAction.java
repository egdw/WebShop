package com.web.shop.action;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Good;
import com.web.shop.model.User;
import com.web.shop.service.GoodService;

@Controller
@Scope(value = "prototype")
public class GoodListAction extends ActionSupport {

	@Autowired
	private GoodService service;
	private int type;
	private String essential;
	private int pageCount;
	private final int QUERYCOUNT = 8;

	@Override
	public String execute() throws Exception {
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
		request.put("essential", null);
		request.put("type", 0);
		if (essential == null) {
			// 说明是通过目录访问进去的
			int userNum = service.getUserFindByTypeCount(type);
			double temp = userNum / QUERYCOUNT;
			int pageNum = 1;
			if (temp % 1 != 0) {
				pageNum = (int) (temp + 1);
			} else {
				pageNum = (int) temp;
			}
			request.put("type", type);
			request.put("pageNum", pageNum);
			ArrayList<Good> list = service.getUserGoodsByType(QUERYCOUNT, pageCount, type);
			request.put("goods", list);
		} else {
			int userNum = service.getUserFindByNameCount(essential);
			double temp = userNum / QUERYCOUNT;
			int pageNum = 1;
			if (temp % 1 != 0) {
				pageNum = (int) (temp + 1);
			} else {
				pageNum = (int) temp;
			}
			request.put("pageNum", pageNum);
			ArrayList<Good> list = service.getGoodsByFindName(essential, QUERYCOUNT, pageCount);
			request.put("goods", list);
			request.put("essential", essential);
		}
		return SUCCESS;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEssential() {
		return essential;
	}

	public void setEssential(String essential) {
		this.essential = essential;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
