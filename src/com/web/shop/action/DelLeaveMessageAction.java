package com.web.shop.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.LeaveMessageService;

/**
 * 删除留言的action
 * 
 * @author hdy
 * 
 */
@Controller
@Scope(value = "prototype")
public class DelLeaveMessageAction extends ActionSupport {
	@Autowired
	private LeaveMessageService service;
	private static final long serialVersionUID = 1L;
	// 留言的id
	private Integer leaveId;
	// 商品的id地址
	private Integer goodId;

	@Override
	public String execute() throws Exception {
		String string = service.deleteLeaveMessage(leaveId);
		return string;
	}

	public Integer getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(Integer leaveId) {
		this.leaveId = leaveId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	@Override
	public void validate() {
		if (goodId == null || leaveId == null) {
			addActionError("输入的参数有误");
		}
	}
}
