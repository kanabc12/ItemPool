package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData01Dao;
import org.hxy.model.QuestionXMLData01;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData01HibernateDao  extends BaseHibernateDao<QuestionXMLData01, Integer> implements IQuestionXMLData01Dao{

	@Override
	public QuestionXMLData01 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
