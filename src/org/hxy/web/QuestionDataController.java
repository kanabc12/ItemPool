package org.hxy.web;

import java.io.File;
import java.util.Date;

import org.hxy.dao.IQuestionXMLData01Dao;
import org.hxy.dao.IQuestionXMLData02Dao;
import org.hxy.dao.IQuestionXMLData03Dao;
import org.hxy.dao.IQuestionXMLData04Dao;
import org.hxy.dao.IQuestionXMLData05Dao;
import org.hxy.dao.IQuestionXMLData06Dao;
import org.hxy.dao.IQuestionXMLData07Dao;
import org.hxy.dao.IQuestionXMLData08Dao;
import org.hxy.dao.IQuestionXMLData09Dao;
import org.hxy.model.Discipline;
import org.hxy.model.Question;
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
import org.hxy.service.IDisciplineService;
import org.hxy.service.IQuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.javass.commons.Constants;
import cn.javass.commons.date.util.DateUtil;
import cn.javass.commons.file.util.FileManager;
import cn.javass.commons.file.util.FileOperateUtils;
import cn.javass.commons.file.util.QuestionCopy;

@Controller
@RequestMapping("/questionData")
public class QuestionDataController {
	@Autowired
	IQuestionService questionService;
	@Autowired
	IDisciplineService disciplineService;
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

	@RequestMapping(value = "/showData", method = RequestMethod.GET)
	public ModelAndView getQuestionData(
			@RequestParam(value = "questionId", required = true) Integer questionId,
			@RequestParam(value = "discipline", required = true) int disciplineId,
			@RequestParam("regTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date regTime) {
		ModelAndView mv = new ModelAndView();
		String root = FileManager.getUrlRootPath();
		String rootPath = root.substring(0, root.length() - 9);// 去掉路径中etsClient
		String htmlFolderPath = rootPath + "/etsdoc/" + disciplineId
				+ "/quehtml/" + DateUtil.DateToString(regTime, "yyyyMMdd");
		File questionBody = new File(htmlFolderPath+"/body"+questionId+".html");
		QuestionXMLData question = new QuestionXMLData();
		if (!questionBody.exists()){
			switch (disciplineId) {
			case 20:
				QuestionXMLData01 question1 = questionXMLData01HibernateDao
						.get(questionId);
				if(question1!=null){
					BeanUtils.copyProperties(question1, question);
				}
				break;
			case 21:
				QuestionXMLData02 question2 = questionXMLData02HibernateDao
						.get(questionId);
				if(question2!=null){
					BeanUtils.copyProperties(question2, question);
				}
				break;
			case 22:
				QuestionXMLData03 question3 = questionXMLData03HibernateDao
						.get(questionId);
				if(question3!=null){
					BeanUtils.copyProperties(question3, question);
				}
			case 23:				
				QuestionXMLData04 question4 = questionXMLData04HibernateDao
						.get(questionId);
				if(question4!=null){
					BeanUtils.copyProperties(question4, question);
				}
				break;
			case 24:
				QuestionXMLData05 question5 = questionXMLData05HibernateDao
						.get(questionId);
				if(question5!=null){
					BeanUtils.copyProperties(question5, question);
				}
				break;
			case 25:
				QuestionXMLData06 question6 = questionXMLData06HibernateDao
						.get(questionId);
				if(question6!=null){
					BeanUtils.copyProperties(question6, question);
				}
				break;
			case 26:
				QuestionXMLData07 question7 = questionXMLData07HibernateDao
						.get(questionId);
				if(question7!=null){
					BeanUtils.copyProperties(question7, question);
				}
				break;
			case 27:
				QuestionXMLData08 question8 = questionXMLData08HibernateDao
						.get(questionId);
				if(question8!=null){
					BeanUtils.copyProperties(question8, question);
				}
				break;
			case 28:
				QuestionXMLData09 question9 = questionXMLData09HibernateDao
						.get(questionId);
				if(question9!=null){
					BeanUtils.copyProperties(question9, question);
				}
				break;
			}
			if(question !=null && !"".equals(question.getZquestionBody())&& question.getZquestionBody()!=null){
				QuestionCopy.genQuestionDocAndHtml(question, disciplineId + "",
						regTime);
				String document = Constants.QUESTIONDOCUMENT_HOME;
				String dateString = DateUtil.DateToString(regTime, "yyyyMMdd");
				mv.addObject("regTime", dateString);
				mv.addObject("questionId", questionId);
				mv.addObject("code", disciplineId);
				mv.addObject("docSource", document);
				mv.setViewName("questionAccordion");
			}else{
				mv.setViewName("questionAccordion1");
			}
		}else{
			String document = Constants.QUESTIONDOCUMENT_HOME;
			String dateString = DateUtil.DateToString(regTime, "yyyyMMdd");
			mv.addObject("regTime", dateString);
			mv.addObject("questionId", questionId);
			mv.addObject("code", disciplineId);
			mv.addObject("docSource", document);
			mv.setViewName("questionAccordion");
		}
		return mv;
	}
	
	@RequestMapping(value = "/addSearchNum")
	public ModelAndView addSearchNum(@RequestParam("questionId") int questionId,@RequestParam("discipline") Integer disciplineId,
			@RequestParam("regTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date regTime) {
		ModelAndView mv = new ModelAndView();
		String root = FileManager.getUrlRootPath();
		String rootPath = root.substring(0, root.length() - 9);// 去掉路径中etsClient
		String htmlFolderPath = rootPath + "/etsdoc/" + disciplineId
				+ "/quehtml/" + DateUtil.DateToString(regTime, "yyyyMMdd");
		File questionBody = new File(htmlFolderPath+"/body"+questionId+".html");
		QuestionXMLData question = new QuestionXMLData();
		if (!questionBody.exists()){
			switch (disciplineId) {
			case 20:
				QuestionXMLData01 question1 = questionXMLData01HibernateDao
						.get(questionId);
				if(question1!=null){
					BeanUtils.copyProperties(question1, question);
				}
				break;
			case 21:
				QuestionXMLData02 question2 = questionXMLData02HibernateDao
						.get(questionId);
				if(question2!=null){
					BeanUtils.copyProperties(question2, question);
				}
				break;
			case 22:
				QuestionXMLData03 question3 = questionXMLData03HibernateDao
						.get(questionId);
				if(question3!=null){
					BeanUtils.copyProperties(question3, question);
				}
				break;
			case 23:			
				QuestionXMLData04 question4 = questionXMLData04HibernateDao
						.get(questionId);
				
				if(question4!=null){
					BeanUtils.copyProperties(question4, question);
				}
				break;
			case 24:
				QuestionXMLData05 question5 = questionXMLData05HibernateDao
						.get(questionId);
				if(question5!=null){
					BeanUtils.copyProperties(question5, question);
				}
				break;
			case 25:
				QuestionXMLData06 question6 = questionXMLData06HibernateDao
						.get(questionId);
				if(question6!=null){
					BeanUtils.copyProperties(question6, question);
				}
				break;
			case 26:
				QuestionXMLData07 question7 = questionXMLData07HibernateDao
						.get(questionId);
				if(question7!=null){
					BeanUtils.copyProperties(question7, question);
				}
				break;
			case 27:
				QuestionXMLData08 question8 = questionXMLData08HibernateDao
						.get(questionId);
				if(question8!=null){
					BeanUtils.copyProperties(question8, question);
				}
				break;
			case 28:
				QuestionXMLData09 question9 = questionXMLData09HibernateDao
						.get(questionId);
				if(question9!=null){
					BeanUtils.copyProperties(question9, question);
				}
				break;
			}
			if(question !=null&&!"".equals(question.getZquestionBody())&& question.getZquestionBody()!=null){
				QuestionCopy.genQuestionDocAndHtml(question, disciplineId + "",
						regTime);
				String document = Constants.QUESTIONDOCUMENT_HOME;
				String dateString = DateUtil.DateToString(regTime, "yyyyMMdd");
				mv.addObject("regTime", dateString);
				mv.addObject("questionId", questionId);
				mv.addObject("code", disciplineId);
				mv.addObject("docSource", document);
				mv.setViewName("questionAccordion");
			}else{
				mv.setViewName("questionAccordion1");
			}
		}else{
			String document = Constants.QUESTIONDOCUMENT_HOME;
			String dateString = DateUtil.DateToString(regTime, "yyyyMMdd");
			mv.addObject("regTime", dateString);
			mv.addObject("questionId", questionId);
			mv.addObject("code", disciplineId);
			mv.addObject("docSource", document);
			mv.setViewName("questionAccordion");
		}
		Question question1 = questionService.get(questionId);
		int searchNum = question1.getSearchNum() != null ? question1
				.getSearchNum() + 1 : 1;
		question1.setSearchNum(searchNum);
		questionService.update(question1);
		return mv;
	}
}
