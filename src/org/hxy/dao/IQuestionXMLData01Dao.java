package org.hxy.dao;



import org.hxy.model.QuestionXMLData01;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData01Dao extends IBaseDao<QuestionXMLData01, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData01 queryQuestionDataById(Integer id);
}
