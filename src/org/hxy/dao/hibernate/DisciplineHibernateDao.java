package org.hxy.dao.hibernate;

import org.hxy.dao.IDisciplineDao;
import org.hxy.model.Discipline;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class DisciplineHibernateDao  extends BaseHibernateDao<Discipline, Integer> implements IDisciplineDao{

	@Override
	public int countAllDisciline() {
		String hql = getCountAllHql();
		Number result = unique(hql);
        return result.intValue();
	}

	@Override
	public int getAllQuestion() {
		String hql = "select sum(amountId)" + getListAllHql();
		Number result = unique(hql);
        return result.intValue();
	}

	@Override
	public int getLastQuestion() {
		String hql = "select sum(newQueAmount)" + getListAllHql();
		Number result = unique(hql);
        return result.intValue();
	}

}
