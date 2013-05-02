package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData03Dao;
import org.hxy.model.QuestionXMLData03;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData03HibernateDao  extends BaseHibernateDao<QuestionXMLData03, Integer> implements IQuestionXMLData03Dao{

	@Override
	public QuestionXMLData03 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
