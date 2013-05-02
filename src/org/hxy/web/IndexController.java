package org.hxy.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.hxy.model.Discipline;
import org.hxy.model.Question;
import org.hxy.model.QuestionMini;
import org.hxy.service.IDisciplineService;
import org.hxy.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.javass.commons.Constants;
import cn.javass.commons.date.util.DateStyle;
import cn.javass.commons.date.util.DateUtil;
import cn.javass.commons.json.util.JsonParser;

@Controller
public class IndexController {
	@Autowired
	IQuestionService questionService;
	
	@Autowired
	IDisciplineService disciplineService;
	
	@RequestMapping("/index.html")
	public ModelAndView showInit() throws JsonGenerationException, JsonMappingException, IOException{
		ModelAndView mv = new ModelAndView();
		int totayQuestion = disciplineService.getLastQuestion();
		int totalQuestion = disciplineService.getAllQuestion();

		List<QuestionMini>  result = questionService.queryMostHotSearch(Constants.DEFAULT_HOTSERCH_NUM);
		List<Discipline> disciplines = disciplineService.listAll();
		disciplines.remove(disciplines.size()-1);
		disciplines.remove(disciplines.size()-1);
		mv.addObject("totayQuestion", totayQuestion);
		mv.addObject("totalQuestion", totalQuestion);
		mv.addObject("hotSearch", result);
		mv.addObject("disciplines", disciplines);
		mv.addObject("disciplineStr", JsonParser.getJsonString(disciplines));
		mv.setViewName("index");
		return mv;
	}
}
