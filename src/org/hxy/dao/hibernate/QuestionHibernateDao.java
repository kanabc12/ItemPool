package org.hxy.dao.hibernate;

import java.util.List;

import org.hxy.dao.IQuestionDao;
import org.hxy.model.Question;
import org.hxy.model.QuestionMini;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class QuestionHibernateDao  extends BaseHibernateDao<Question, Integer> implements IQuestionDao{
	@Override
	public List<QuestionMini> queryQuestion(String discipline, String know,int limit,int start) {
		String hql = "select new org.hxy.model.QuestionMini(id,answerText,bodyText,disciplineId,regTime,queSoruce,knowledgeName,topicName) from org.hxy.model.Question where knowledgeName like '%"+know+"%' and disciplineId = "+ new Integer(discipline);
		List<QuestionMini> results = list(hql, start, limit, null);
		return results;
	}

	@Override
	public List<QuestionMini> queryQuestionByDiscipline(String discipline,int pn,int pageSize) {
		String hql = "select new org.hxy.model.QuestionMini(id,answerText,bodyText,disciplineId,regTime,queSoruce,knowledgeName,topicName) from org.hxy.model.Question where disciplineId = "+ new Integer(discipline) +" and isChecked=1 and status= 0 order by regTime desc";
		List<QuestionMini> results = list(hql, pn, pageSize, null);
		return results;
	}

	@Override
	public int queryQuestionAmount(String discipline, String know) {
		String hql = getCountAllHql()+" where knowledgeName like '%"+know+"%' and disciplineId = "+ new Integer(discipline);
		Number total = unique(hql);
		return total.intValue();
	}

	@Override
	public int countQuestionByPublishDate(String dateStr) {
		String hql =  getCountAllHql() + " where to_char(regTime,'yyyy-MM-dd')"+"='"+dateStr+"'";		
		Number total = unique(hql, null);
		return total.intValue();
	}

	@Override
	public List<QuestionMini> queryQuestionByAnswerText(String answerText,int pn,int pageSize) {
		String hql = "select new org.hxy.model.QuestionMini(id,answerText,bodyText,disciplineId,regTime,queSoruce,knowledgeName,topicName) from org.hxy.model.Question where answerText like '%"+answerText+"%'";
		List<QuestionMini> results = list(hql, pn, pageSize, null);
		return results;
	}

	@Override
	public List<QuestionMini> queryMostHotSearch(int size) {
		String hql =  "select new org.hxy.model.QuestionMini(id,answerText,bodyText,disciplineId,regTime,queSoruce,knowledgeName,topicName,searchNum) from org.hxy.model.Question where searchNum is not null and isChecked=1 and queSoruce is not null and status= 0 order by searchNum desc";
		List<QuestionMini>  results = list(hql, 1, size, null);
		return results;
	}

	@Override
	public List<QuestionMini> queryQuestionsByAnswerAndDiscipline(
			String answerText, String discipline, int pn, int pageSize) {
		String hql = "select new org.hxy.model.QuestionMini(id,answerText,bodyText,disciplineId,regTime,queSoruce,knowledgeName,topicName) from org.hxy.model.Question where bodyText like '%"+answerText+"%' and disciplineId = " + new Integer(discipline)
				+" and isChecked='1' and status='0'";
		List<QuestionMini> results = list(hql, pn, pageSize, null);
		return results;
	}

	@Override
	public List<QuestionMini> queryCurrentQuestion(String regTime) {
		String hql =  "select new org.hxy.model.QuestionMini(id,disciplineId,regTime) from org.hxy.model.Question where to_char(regTime,'yyyy-MM-dd')=?";
		List<QuestionMini> results = list(hql, -1, -1, new String[]{regTime});
		return results;
	}

}
