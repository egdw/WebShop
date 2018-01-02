package com.web.shop.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.web.shop.model.Student;

@Repository
public interface StudentDao {
	public ArrayList<Student> getStudents(int queryCount, int pageCount);

	public boolean delStudent(long userId);

	public boolean addStudent(Student student);

	public Student getStudent(long id);

	public ArrayList<Student> getStudent(String name);

	public boolean updateStudent(Student student);

	public boolean addStudents(ArrayList<String> ids, ArrayList<String> names);
}
