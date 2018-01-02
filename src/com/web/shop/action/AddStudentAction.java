package com.web.shop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.service.StudentService;

@Controller
@Scope(value = "prototype")
public class AddStudentAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> ids;
	private ArrayList<String> names;
	@Autowired
	private StudentService service;
	private String result;

	@Override
	public String execute() throws Exception {
		Map<String, Object> map = null;
		boolean students = false;
		if (ids != null && names != null) {
			students = service.saveStudents(getIds(), getNames());
		}
		if (students) {
			map = new HashMap<String, Object>();
			map.put("meesage", "true");
			result = map.toString();
			return SUCCESS;
		} else {
			map = new HashMap<String, Object>();
			map.put("meesage", "false");
			result = map.toString();
			return SUCCESS;
		}
	}

	public ArrayList<String> getIds() {
		return ids;
	}

	public void setIds(ArrayList<String> ids) {
		this.ids = ids;
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
