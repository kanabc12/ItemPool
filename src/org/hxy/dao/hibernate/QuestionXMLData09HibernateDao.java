package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData09Dao;
import org.hxy.model.QuestionXMLData09;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData09HibernateDao  extends BaseHibernateDao<QuestionXMLData09, Integer> implements IQuestionXMLData09Dao{

	@Override
	public QuestionXMLData09 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
