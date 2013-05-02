package org.hxy.dao;



import org.hxy.model.QuestionXMLData03;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData03Dao extends IBaseDao<QuestionXMLData03, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData03 queryQuestionDataById(Integer id);
}
