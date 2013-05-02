package org.hxy.dao;



import org.hxy.model.QuestionXMLData08;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData08Dao extends IBaseDao<QuestionXMLData08, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData08 queryQuestionDataById(Integer id);
}
