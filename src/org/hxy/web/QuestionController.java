package org.hxy.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.hxy.model.Discipline;
import org.hxy.model.Question;
import org.hxy.model.QuestionMini;
import org.hxy.service.IDisciplineService;
import org.hxy.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import cn.javass.commons.Constants;
import cn.javass.commons.date.util.DateUtil;
import cn.javass.commons.json.util.JsonParser;

@Controller
@RequestMapping("/question")
public class QuestionController {
	@Autowired
	IQuestionService questionService;

	@Autowired
	IDisciplineService disciplineService;

	@RequestMapping(value = "/getQuestions/{disicipline}")
	public ModelAndView getQuestion(
			@PathVariable(value = "disicipline") String disicipline) {
		ModelAndView model = new ModelAndView();
		model.addObject("disicipline", disicipline);
		model.setViewName("grid/grid");
		return model;
	}

	@RequestMapping(value = "/getQuestion", method = RequestMethod.GET)
	@ResponseBody
	public Map getQuestion(@RequestParam("discipline") String discipline,
			@RequestParam(value = "know", defaultValue = "") String know,
			@RequestParam(value = "start", defaultValue = "1") int start,
			@RequestParam(value = "limit", defaultValue = "10") int limit)
			throws Exception {
		int pn = start / 10 == 0 ? 1 : (start / 10 + 1);
		List<QuestionMini> result = questionService.queryQuestion(discipline, know,
				limit, pn);
		int total = questionService.queryQuestionAmount(discipline, know);		
			Map<String, Object> grid = new HashMap<String, Object>();
			//total = questionService.queryQuestionAmount(discipline, know);
			grid.put("total", total);
			grid.put("rows", result);
			return grid;		
		
	}

	@ResponseBody
	@RequestMapping(value = "/get20thQuestions/{disicipline}")
	public ModelAndView get20thQuestion(
			@PathVariable(value = "disicipline") String disicipline) {
		ModelAndView model = new ModelAndView();
		WebApplicationContext webApplicationContext = ContextLoader
				.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext
				.getServletContext();
		List<QuestionMini> result = null;
		if (servletContext.getAttribute(disicipline) == null) {
			result = questionService.queryQuestionByDiscipline(
					disicipline, 1, 20);
			servletContext.setAttribute(disicipline, result);
		} else {
			result = (List<QuestionMini>) servletContext.getAttribute(disicipline);
		}
		model.addObject("questions", result);
		model.setViewName("grid/grid");
		return model;
	}

	@RequestMapping(value = "/getQuestionByAnswer")
	@ResponseBody
	public Map getQuestionByAnswer(@RequestParam("answerText") String answer,
			@RequestParam(value="discipline",defaultValue="")String discipline,
			@RequestParam(value = "start", defaultValue = "0") int pn,
			@RequestParam(value = "limit", defaultValue = "10") int pageSize) throws Exception {
		pn = pn / 10 == 0 ? 1 : pn / 10 + 1;
		List<QuestionMini> result = questionService.queryQuestionsByAnswerAndDiscipline(
				answer, discipline,pn, pageSize);
		int total = questionService.queryQuestionsByAnswerAndDiscipline(answer,discipline, -1, -1)
				.size();
		Map<String, Object> grid = new HashMap<String, Object>();
		grid.put("total", total);
		grid.put("rows", result);
		return grid;
	}


	@RequestMapping(value = "/showDetail")
	public ModelAndView getQuestion(@RequestParam("questionId") int questionId) {
		ModelAndView model = new ModelAndView();
		Question question = questionService.get(questionId);
		model.addObject("question", question);
		model.setViewName("accordion");
		return model;
	}

	@RequestMapping(value = "/addSearchNum")
	public ModelAndView addSearchNum(@RequestParam("questionId") int questionId,@RequestParam("disicipline") String disciplineId,
			@RequestParam("regTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date regTime) {
		ModelAndView model = new ModelAndView();
		String document = Constants.QUESTIONDOCUMENT_HOME;
		String dateString = DateUtil.DateToString(regTime, "yyyyMMdd");
		Discipline d = disciplineService.get(new Integer(disciplineId));
		String code = null;
		if (d != null) {
			if (d.getCode() != null && !"".equals(d.getCode())) {
				if (d.getCode().length() == 2 && d.getCode().startsWith("0")) {
					code = d.getCode().substring(1, 2);
				} else {
					code = d.getCode();
				}
			}
		}
		model.addObject("regTime", dateString);
		model.addObject("questionId", questionId);
		model.addObject("code", code);
		model.addObject("docSource", document);
		Question question = questionService.get(questionId);
		int searchNum = question.getSearchNum() != null ? question
				.getSearchNum() + 1 : 1;
		question.setSearchNum(searchNum);
		questionService.update(question);
		model.setViewName("questionAccordion");
		return model;
	}

	@RequestMapping(value = "/showQuestion", method = RequestMethod.GET)
	public ModelAndView showQuestion(
			@RequestParam("questionId") String questionId,
			@RequestParam("disicipline") String disciplineId,
			@RequestParam("regTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date regTime) {
		ModelAndView model = new ModelAndView();
		String document = Constants.QUESTIONDOCUMENT_HOME;
		String dateString = DateUtil.DateToString(regTime, "yyyyMMdd");
		Discipline d = disciplineService.get(new Integer(disciplineId));
		String code = null;
		if (d != null) {
			if (d.getCode() != null && !"".equals(d.getCode())) {
				if (d.getCode().length() == 2 && d.getCode().startsWith("0")) {
					code = d.getCode().substring(1, 2);
				} else {
					code = d.getCode();
				}
			}
		}
		model.addObject("regTime", dateString);
		model.addObject("questionId", questionId);
		model.addObject("code", code);
		model.addObject("docSource", document);
		model.setViewName("questionAccordion");
		return model;
	}
	@RequestMapping(value = "/errorCorrect", method = RequestMethod.GET)
	public String errorCorrect(@RequestParam("questionId") String questionId,
			@RequestParam("discipline") String disciplineId,
			@RequestParam("regTime") @DateTimeFormat(pattern = "yyyy-MM-dd") Date regTime){
		
		return "errcorrection";
	}
	
	@RequestMapping(value = "/login")
	public String login(){		
		return "login";
	}
	@RequestMapping(value = "/register")
	public String register(){		
		return "reg";
	}
}
