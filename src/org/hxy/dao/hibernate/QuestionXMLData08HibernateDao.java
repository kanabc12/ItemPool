package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData08Dao;
import org.hxy.model.QuestionXMLData08;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData08HibernateDao  extends BaseHibernateDao<QuestionXMLData08, Integer> implements IQuestionXMLData08Dao{

	@Override
	public QuestionXMLData08 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
