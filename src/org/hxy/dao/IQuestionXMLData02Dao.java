package org.hxy.dao;



import org.hxy.model.QuestionXMLData02;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData02Dao extends IBaseDao<QuestionXMLData02, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData02 queryQuestionDataById(Integer id);
}
