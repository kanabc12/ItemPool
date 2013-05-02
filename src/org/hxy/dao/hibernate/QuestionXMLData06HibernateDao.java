package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData06Dao;
import org.hxy.model.QuestionXMLData06;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData06HibernateDao  extends BaseHibernateDao<QuestionXMLData06, Integer> implements IQuestionXMLData06Dao{

	@Override
	public QuestionXMLData06 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
