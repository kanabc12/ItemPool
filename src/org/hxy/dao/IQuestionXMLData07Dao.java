package org.hxy.dao;



import org.hxy.model.QuestionXMLData07;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData07Dao extends IBaseDao<QuestionXMLData07, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData07 queryQuestionDataById(Integer id);
}
