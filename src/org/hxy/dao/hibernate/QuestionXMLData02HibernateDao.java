package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData02Dao;
import org.hxy.model.QuestionXMLData02;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData02HibernateDao  extends BaseHibernateDao<QuestionXMLData02, Integer> implements IQuestionXMLData02Dao{

	@Override
	public QuestionXMLData02 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
