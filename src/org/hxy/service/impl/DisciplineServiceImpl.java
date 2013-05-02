package org.hxy.service.impl;

import java.util.List;

import org.hxy.dao.IDisciplineDao;
import org.hxy.model.Discipline;
import org.hxy.service.IDisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javass.commons.pagination.Page;

@Service("disciplineService")
public class DisciplineServiceImpl implements IDisciplineService {

	@Autowired
	private IDisciplineDao disciplineHibernateDao;
	
	@Override
	public Discipline save(Discipline model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Discipline model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Discipline model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void merge(Discipline model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Discipline get(Integer id) {
		// TODO Auto-generated method stub
		return disciplineHibernateDao.get(id);
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return disciplineHibernateDao.countAllDisciline();
	}

	@Override
	public List<Discipline> listAll() {
		// TODO Auto-generated method stub
		return disciplineHibernateDao.listAll();
	}

	@Override
	public Page<Discipline> listAll(int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Discipline> listAll(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAllQuestion() {
		// TODO Auto-generated method stub
		return disciplineHibernateDao.getAllQuestion();
	}

	@Override
	public int getLastQuestion() {
		// TODO Auto-generated method stub
		return disciplineHibernateDao.getLastQuestion();
	}

}
