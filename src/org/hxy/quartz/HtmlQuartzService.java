package org.hxy.quartz;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.hxy.dao.IQuestionXMLData01Dao;
import org.hxy.dao.IQuestionXMLData02Dao;
import org.hxy.dao.IQuestionXMLData03Dao;
import org.hxy.dao.IQuestionXMLData04Dao;
import org.hxy.dao.IQuestionXMLData05Dao;
import org.hxy.dao.IQuestionXMLData06Dao;
import org.hxy.dao.IQuestionXMLData07Dao;
import org.hxy.dao.IQuestionXMLData08Dao;
import org.hxy.dao.IQuestionXMLData09Dao;
import org.hxy.model.QuestionMini;
import org.hxy.model.QuestionXMLData;
import org.hxy.model.QuestionXMLData01;
import org.hxy.model.QuestionXMLData02;
import org.hxy.model.QuestionXMLData03;
import org.hxy.model.QuestionXMLData04;
import org.hxy.model.QuestionXMLData05;
import org.hxy.model.QuestionXMLData06;
import org.hxy.model.QuestionXMLData07;
import org.hxy.model.QuestionXMLData08;
import org.hxy.model.QuestionXMLData09;
import org.hxy.service.IQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javass.commons.date.util.DateStyle;
import cn.javass.commons.date.util.DateUtil;
import cn.javass.commons.file.util.FileManager;
import cn.javass.commons.file.util.QuestionCopy;

@Service("relationClearQuartzService")
public class HtmlQuartzService {
	public static final Logger ERROR_LOG = LoggerFactory
			.getLogger("itempool-error");
	public static final Logger ACCESS_LOG = LoggerFactory
			.getLogger("itempool-access");

	@Autowired
	IQuestionService questionService;

	@Autowired
	IQuestionXMLData01Dao questionXMLData01HibernateDao;
	@Autowired
	IQuestionXMLData02Dao questionXMLData02HibernateDao;
	@Autowired
	IQuestionXMLData03Dao questionXMLData03HibernateDao;
	@Autowired
	IQuestionXMLData04Dao questionXMLData04HibernateDao;
	@Autowired
	IQuestionXMLData05Dao questionXMLData05HibernateDao;
	@Autowired
	IQuestionXMLData06Dao questionXMLData06HibernateDao;
	@Autowired
	IQuestionXMLData07Dao questionXMLData07HibernateDao;
	@Autowired
	IQuestionXMLData08Dao questionXMLData08HibernateDao;
	@Autowired
	IQuestionXMLData09Dao questionXMLData09HibernateDao;

	public void autoClearRelation() {
		String root = FileManager.getUrlRootPath();
		String rootPath = root.substring(0, root.length() - 9);// 去掉路径中etsClient
		long currentTime = System.currentTimeMillis();
		Date currentDate = new Date(currentTime);
		String regTime = DateUtil.DateToString(currentDate,
				DateStyle.YYYY_MM_DD);
		List<QuestionMini> currentQuestions = questionService
				.queryCurrentQuestion(regTime);
		for (QuestionMini question : currentQuestions) {
			Integer disciplineId = question.getDisciplineId();
			Integer questionId = question.getId();
			String htmlFolderPath = rootPath + "/etsdoc/" + (disciplineId - 19)
					+ "/quehtml/"
					+ DateUtil.DateToString(currentDate, "yyyyMMdd");
			File questionBody = new File(htmlFolderPath + "/body"
					+ question.getId() + ".html");
			QuestionXMLData questionData = new QuestionXMLData();
			if (!questionBody.exists()) {
				switch (disciplineId) {
				case 20:
					QuestionXMLData01 question1 = questionXMLData01HibernateDao
							.get(questionId);
					if (question1 != null) {
						BeanUtils.copyProperties(question1, questionData);
					}
					break;
				case 21:
					QuestionXMLData02 question2 = questionXMLData02HibernateDao
							.get(questionId);
					if (question2 != null) {
						BeanUtils.copyProperties(question2, questionData);
					}
					break;
				case 22:
					QuestionXMLData03 question3 = questionXMLData03HibernateDao
							.get(questionId);
					if (question3 != null) {
						BeanUtils.copyProperties(question3, questionData);
					}
				case 23:
					QuestionXMLData04 question4 = questionXMLData04HibernateDao
							.get(questionId);
					if (question4 != null) {
						BeanUtils.copyProperties(question4, questionData);
					}
					break;
				case 24:
					QuestionXMLData05 question5 = questionXMLData05HibernateDao
							.get(questionId);
					if (question5 != null) {
						BeanUtils.copyProperties(question5, questionData);
					}
					break;
				case 25:
					QuestionXMLData06 question6 = questionXMLData06HibernateDao
							.get(questionId);
					if (question6 != null) {
						BeanUtils.copyProperties(question6, questionData);
					}
					break;
				case 26:
					QuestionXMLData07 question7 = questionXMLData07HibernateDao
							.get(questionId);
					if (question7 != null) {
						BeanUtils.copyProperties(question7, questionData);
					}
					break;
				case 27:
					QuestionXMLData08 question8 = questionXMLData08HibernateDao
							.get(questionId);
					if (question8 != null) {
						BeanUtils.copyProperties(question8, questionData);
					}
					break;
				case 28:
					QuestionXMLData09 question9 = questionXMLData09HibernateDao
							.get(questionId);
					if (question9 != null) {
						BeanUtils.copyProperties(question9, questionData);
					}
					break;
				}
			}
			if(questionData !=null && !"".equals(questionData.getZquestionBody())&& questionData.getZquestionBody()!=null){
				QuestionCopy.genQuestionDocAndHtml(questionData, disciplineId + "",DateUtil.DateToString(currentDate, "yyyyMMdd"));
				ACCESS_LOG.info("试卷"+questionData.getQuestionId()+"生成了,时间为"+System.currentTimeMillis());
			}
		}
	}
}
