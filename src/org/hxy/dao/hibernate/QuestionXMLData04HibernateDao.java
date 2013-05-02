package org.hxy.dao.hibernate;



import org.hxy.dao.IQuestionXMLData04Dao;
import org.hxy.model.QuestionXMLData04;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionXMLData04HibernateDao  extends BaseHibernateDao<QuestionXMLData04, Integer> implements IQuestionXMLData04Dao{

	@Override
	public QuestionXMLData04 queryQuestionDataById(Integer id) {
		
		return get(id);
	}



}
