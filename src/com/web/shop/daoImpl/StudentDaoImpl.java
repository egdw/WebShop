package com.web.shop.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.web.shop.dao.StudentDao;
import com.web.shop.model.Student;

@Component
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ArrayList<Student> getStudents(int queryCount, int pageCount) {
		return null;
	}

	@Override
	public boolean delStudent(long userId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Object object = currentSession.get(Student.class, userId);
		if (object != null) {
			currentSession.delete(object);
			currentSession.flush();
			return true;
		}
		return false;
	}

	@Override
	public boolean addStudent(Student student) {
		Student student2 = getStudent(student.getSid());
		Session session = sessionFactory.getCurrentSession();
		if (student2 == null) {
			session.save(student);
			session.flush();
			return true;
		}
		return false;
	}

	@Override
	public Student getStudent(long id) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student) session.get(Student.class, id);
		return student;
	}

	@Override
	public ArrayList<Student> getStudent(String name) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session
				.createSQLQuery("select * from STUDENT where STUDENTNAME = ?");
		Query string = query.setString(0, name);
		query.addEntity(Student.class);
		ArrayList<Student> list = (ArrayList<Student>) string.list();
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public boolean updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(student);
		session.flush();
		return false;
	}

	@Override
	public boolean addStudents(ArrayList<String> ids, ArrayList<String> names) {
		Session currentSession = sessionFactory.getCurrentSession();
		if (ids != null && names != null) {
			for (int i = 0; i < ids.size(); i++) {
				Long integer = Long.valueOf(ids.get(i));
				String name = names.get(i);
				if (integer != null && name != null && !name.isEmpty()) {
					Object object = currentSession.get(Student.class, integer);
					if (object == null) {
						Student student = new Student(integer, name);
						currentSession.save(student);
					}
				}
			}
			return true;
		}
		currentSession.flush();
		return false;
	}

}
