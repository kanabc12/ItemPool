package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData07Dao;
import org.hxy.model.QuestionXMLData07;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData07HibernateDao  extends BaseHibernateDao<QuestionXMLData07, Integer> implements IQuestionXMLData07Dao{

	@Override
	public QuestionXMLData07 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
