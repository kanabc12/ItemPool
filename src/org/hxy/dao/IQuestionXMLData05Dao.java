package org.hxy.dao;



import org.hxy.model.QuestionXMLData05;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData05Dao extends IBaseDao<QuestionXMLData05, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData05 queryQuestionDataById(Integer id);
}
