package org.hxy.service.impl;

import java.util.List;

import org.hxy.dao.IQuestionDao;
import org.hxy.dao.hibernate.QuestionHibernateDao;
import org.hxy.model.Question;
import org.hxy.model.QuestionMini;
import org.hxy.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javass.commons.pagination.Page;

@Service("questionService")
public class QuestionServiceImpl implements IQuestionService {

	@Autowired
	private IQuestionDao questionHibernateDao;

	@Override
	public Question save(Question model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Question model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Question model) {
		questionHibernateDao.update(model);
	}

	@Override
	public void merge(Question model) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Question get(Integer id) {
		return questionHibernateDao.get(id);
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return questionHibernateDao.countAll();
	}

	@Override
	public List<Question> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Question> listAll(int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Question> listAll(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<QuestionMini> queryQuestion(String discipline, String know,int limit,int start) {
		List<QuestionMini>  result = null;
		if("".equals(know)){
			result = questionHibernateDao.queryQuestionByDiscipline(discipline, start, limit);
		}else{
			result = questionHibernateDao.queryQuestion(discipline, know, limit, start);
		}
		return result;
	}

	@Override
	public List<QuestionMini> queryQuestionByDiscipline(String discipline,int pn,int pageSize) {
		List<QuestionMini>  result = questionHibernateDao.queryQuestionByDiscipline(discipline,pn,pageSize);
		return result;
	}

	@Override
	public int queryQuestionAmount(String discipline, String know) {
		int result = questionHibernateDao.queryQuestionAmount(discipline, know);
		return result;
	}

	@Override
	public int countQuestionByPublishDate(String dateStr) {
		return questionHibernateDao.countQuestionByPublishDate(dateStr);
	}

	@Override
	public List<QuestionMini> queryQuestionByAnswerText(String answerText, int pn,
			int pageSize) {
		// TODO Auto-generated method stub
		return questionHibernateDao.queryQuestionByAnswerText(answerText, pn, pageSize);
	}

	@Override
	public List<QuestionMini> queryMostHotSearch(int size) {
		// TODO Auto-generated method stub
		return questionHibernateDao.queryMostHotSearch(size);
	}

	@Override
	public List<QuestionMini> queryQuestionsByAnswerAndDiscipline(
			String answerText, String discipline, int pn, int pageSize) {
		List<QuestionMini>  result = null;
		if(!"".equals(discipline)&& discipline!=null){
			result = questionHibernateDao.queryQuestionsByAnswerAndDiscipline(answerText, discipline, pn, pageSize);
		}else{
			result =  questionHibernateDao.queryQuestionByAnswerText(answerText, pn, pageSize);
		}
		return result;
	}

	@Override
	public List<QuestionMini> queryCurrentQuestion(String regTime) {
		// TODO Auto-generated method stub
		return questionHibernateDao.queryCurrentQuestion(regTime);
	}

	
}
