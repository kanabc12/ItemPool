package org.hxy.dao;



import org.hxy.model.QuestionXMLData09;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData09Dao extends IBaseDao<QuestionXMLData09, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData09 queryQuestionDataById(Integer id);
}
