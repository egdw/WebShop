package com.web.shop.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.StudentService;

@Controller
@Scope(value = "prototype")
public class DelStudentAction extends ActionSupport {
	private Long sid;
	@Autowired
	private StudentService service;
	private String result;

	@Override
	public String execute() throws Exception {
		Map<String, Object> map = null;
		if (sid != null) {
			boolean delStudent = service.delStudent(sid);
			if (delStudent) {
				map = new HashMap<String, Object>();
				map.put("meesage", "true");
				result = map.toString();
				return SUCCESS;
			} else {
				map = new HashMap<String, Object>();
				map.put("meesage", "false");
			}
		} else {
			map = new HashMap<String, Object>();
			map.put("meesage", "false");
		}
		addActionError("删除学生失败");
		result = map.toString();
		return ERROR;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
