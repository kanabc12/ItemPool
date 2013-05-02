package org.hxy.service;

import java.util.List;

import org.hxy.model.Question;
import org.hxy.model.QuestionMini;

import cn.javass.commons.service.IBaseService;

public interface IQuestionService extends IBaseService<Question,Integer> {
	public int queryQuestionAmount(String discipline, String know);
	public List<QuestionMini> queryQuestion(String discipline, String know,int limit,int start);
	public List<QuestionMini>  queryQuestionByDiscipline(String discipline,int pn,int pageSize);
	public int countQuestionByPublishDate(String dataStr) ;
	
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
}
