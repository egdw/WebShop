package com.web.shop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.ContactLeave;
import com.web.shop.model.Good;
import com.web.shop.service.ContactLeaveService;
import com.web.shop.service.GoodService;

@Controller
@Scope(value = "prototype")
public class UserGoodAction extends ActionSupport {
	private final int QUERYCOUNT = 4;
	private int pageCount;
	@Autowired
	private GoodService service;
	@Autowired
	private ContactLeaveService contactLeaveService;

	@Override
	public String execute() throws Exception {
		int goodsNum = service.getUserGoodsNum();
		double temp = goodsNum / QUERYCOUNT;
		int pageNum = 1;
		if (temp % 1 != 0) {
			pageNum = (int) (temp + 1);
		} else {
			pageNum = (int) temp;
		}
		Map<String, Object> request = (Map<String, Object>) ActionContext
				.getContext().get("request");
		request.put("pageNum", pageNum);
		ArrayList<Good> goods = service.getUserGoods(QUERYCOUNT, pageCount);
		request.put("goods", goods);
		Map<String, ArrayList<ContactLeave>> maps = new HashMap<>();
		for (int i = 0; i < goods.size(); i++) {
			ArrayList<ContactLeave> arrayList = contactLeaveService
					.getContactLeave(goods.get(i).getGoodId());
			maps.put("contactList" + i, arrayList);
		}
		request.put("maps", maps);
		return SUCCESS;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
