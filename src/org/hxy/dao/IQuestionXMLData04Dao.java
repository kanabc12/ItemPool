package org.hxy.dao;



import org.hxy.model.QuestionXMLData04;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionXMLData04Dao extends IBaseDao<QuestionXMLData04, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public QuestionXMLData04 queryQuestionDataById(Integer id);
}
