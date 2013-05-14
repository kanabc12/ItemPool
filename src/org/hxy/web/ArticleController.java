package org.hxy.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hxy.model.Article;
import org.hxy.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;
	
	@RequestMapping("getArticlesByTitle")
	@ResponseBody
	public Map getArticlesByTitle(@RequestParam(value = "title") String title,
			@RequestParam(value = "start", defaultValue = "0") int pn,
			@RequestParam(value = "limit", defaultValue = "10") int pageSize) {
		int count = articleService.getArticlesByTitle(title, -1, -1).size();
		pn = pn / 10 == 0 ? 1 : pn / 10 + 1;
		List<Article> result = articleService.getArticlesByTitle(title, pn, pageSize);
		Map<String, Object> grid = new HashMap<String, Object>();
		grid.put("total", count);
		grid.put("rows", result);
		return grid;
	}

}
