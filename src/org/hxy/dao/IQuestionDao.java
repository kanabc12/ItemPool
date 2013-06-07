package org.hxy.dao;

import java.util.Date;
import java.util.List;

import org.hxy.model.Question;
import org.hxy.model.QuestionMini;

import cn.javass.commons.dao.IBaseDao;

public interface IQuestionDao extends IBaseDao<Question, Integer>  {
	public List<QuestionMini>  queryQuestion(String discipline,String know,int limit,int start);
	
	/**
	 * 根据学科获取前20个试题
	 * @param discipline 学科编号
	 * @return
	 */
	public List<QuestionMini>  queryQuestionByDiscipline(String discipline,int pn,int pageSize);
	
	/**
	 * 根据学科类别和知识点查询符合条件的总数
	 * @param discipline
	 * @param know
	 * @return
	 */
	public int queryQuestionAmount(String discipline,String know);
	
	/**
	 * 查询具体上传日期的总试题数
	 * @param date 上传日期
	 * @return 
	 */
	public int countQuestionByPublishDate(String dateStr);
	
	/**
	 * 根据答案查询试题
	 * @param answerText
	 * @param pn  
	 * @param pageSize
	 * @return
	 */
	public List<QuestionMini> queryQuestionByAnswerText(String answerText,int pn,int pageSize);
	
	/**
	 * 根据答案和学科查询试题
	 * @param answerText
	 * @param discipline
	 * @param pn
	 * @param pageSize
	 * @return
	 */
	public List<QuestionMini> queryQuestionsByAnswerAndDiscipline(String answerText,String discipline,int pn,int pageSize);

	/**
	 * 查询搜索量最多的试题
	 * @param size 要显示的条数
	 * @return
	 */
	public List<QuestionMini> queryMostHotSearch(int size);
	/**
	 * 查询当天新增的试题
	 * @param regTime
	 * @return
	 */
	public List<QuestionMini> queryCurrentQuestion(String regTime);
}
