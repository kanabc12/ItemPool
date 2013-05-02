package org.hxy.dao;



import org.hxy.model.QuestionXMLData06;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData06Dao extends IBaseDao<QuestionXMLData06, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData06 queryQuestionDataById(Integer id);
}
