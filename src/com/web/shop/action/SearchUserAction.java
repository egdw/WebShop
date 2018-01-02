package com.web.shop.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.web.shop.model.Student;
import com.web.shop.service.StudentService;

@Controller
@Scope(value = "prototype")
public class SearchUserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long sid;
	private String studentName;
	private String result;

	@Autowired
	private StudentService service;

	@Override
	public String execute() throws Exception {
		JSONArray jsonArray = new JSONArray();
		if (studentName == null || studentName.isEmpty()) {
			Student student = service.getStudent(sid);
			if (student != null) {
				Map<String, Object> m = new HashMap<>();
				m.put("sid", student.getSid());
				m.put("studentName", student.getStudentName());
				JSONObject fromObject = JSONObject.fromObject(m);
				jsonArray.add(fromObject);
			}
		} else {
			ArrayList<Student> list = service.getStudentsByName(studentName);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					Student student = list.get(i);
					Map<String, Object> m = new HashMap<>();
					m.put("sid", student.getSid());
					m.put("studentName", student.getStudentName());
					JSONObject fromObject = JSONObject.fromObject(m);
					jsonArray.add(fromObject);
				}
			} else {
				JSONObject fromObject = JSONObject.fromObject(new HashMap<>());
				jsonArray.add(fromObject);
			}
		}
		result = jsonArray.toString();
		return SUCCESS;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
