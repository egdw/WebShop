package com.web.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.fabric.xmlrpc.base.Array;
import com.web.shop.daoImpl.StudentDaoImpl;
import com.web.shop.model.Student;

@Service
public class StudentService {
	@Autowired
	private StudentDaoImpl daoImpl;

	@Transactional
	public ArrayList<Student> getStudentsByName(String name) {
		ArrayList<Student> student = daoImpl.getStudent(name);
		return student;
	}

	@Transactional
	public Student getStudent(long sid) {
		Student student = daoImpl.getStudent(sid);
		return student;
	}

	@Transactional
	public boolean delStudent(long sid) {
		boolean student = daoImpl.delStudent(sid);
		return student;
	}

	@Transactional
	public boolean saveStudents(ArrayList<String> ids, ArrayList<String> names) {
		boolean b = daoImpl.addStudents(ids, names);
		return b;
	}
}
