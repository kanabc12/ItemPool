package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData05Dao;
import org.hxy.model.QuestionXMLData05;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData05HibernateDao  extends BaseHibernateDao<QuestionXMLData05, Integer> implements IQuestionXMLData05Dao{

	@Override
	public QuestionXMLData05 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
