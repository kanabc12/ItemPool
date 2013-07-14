package org.hxy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.hxy.model.Article;
import org.hxy.model.QuestionMini;
import org.hxy.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import cn.javass.commons.util.DateTimeUitl;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private IArticleService articleService;

	@RequestMapping("getArticlesByTitle")
	@ResponseBody
	public Map getArticlesByTitle(@RequestParam(value = "title") String title,
			@RequestParam(value = "start", defaultValue = "0") int pn,
			@RequestParam(value = "limit", defaultValue = "10") int pageSize,
			@RequestParam(value = "dq", defaultValue = "0") int dq,
			@RequestParam(value = "xk", defaultValue = "0") int xk) {
		int count = articleService.count(title, dq, xk);
		pn = pn / 10 == 0 ? 1 : pn / 10 + 1;
		List<Article> result = articleService.getArticles(title, pn, pageSize,
				dq, xk);
		Map<String, Object> grid = new HashMap<String, Object>();
		grid.put("total", count);
		grid.put("rows", result);
		return grid;
	}

	@ResponseBody
	@RequestMapping(value = "/get20thArticles/{disciplineId}")
	public ModelAndView get20thArticle(
			@PathVariable(value = "disciplineId") int disciplineId) {
		ModelAndView model = new ModelAndView();
		WebApplicationContext webApplicationContext = ContextLoader
				.getCurrentWebApplicationContext();
		ServletContext servletContext = webApplicationContext
				.getServletContext();
		List<Article> result = null;
		int subjectId = 0;
		switch(disciplineId){
			case 20:
				subjectId=11;
				break;
			case 21:
				subjectId = 10;
				break;
			case 22:
				subjectId = 9;
				break;
			case 23:
				subjectId = 8;
				break;
			case 24:
				subjectId = 7;
				break;
			case 25:
				subjectId = 6;
				break;
			case 26:
				subjectId = 5;
				break;
			case 27:
				subjectId = 4;
				break;
			case 28:
				subjectId = 3;	
				break;
		}		

		
		if (servletContext.getAttribute(subjectId+"") == null) {
			result = articleService.getArticleBySubject(subjectId, 1, 20);
			servletContext.setAttribute(subjectId+"", result);
		} else {
			result = (List<Article>) servletContext.getAttribute(subjectId+"");
		}
		model.addObject("articles", result);
		model.setViewName("grid/article");
		return model;
	}
}
