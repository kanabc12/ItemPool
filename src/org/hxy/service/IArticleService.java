package org.hxy.service;

import java.util.List;

import org.hxy.model.Article;

import cn.javass.commons.service.IBaseService;

public interface IArticleService extends IBaseService<Article, Integer> {
	
	public List<Article> getArticlesByTitle(String title,int pn,int pageSize);
	
	public List<Article> getArticles(String title,int pn,int pageSize,int proviceId,int subjectId);

	/**
	 * 查询所有的文章
	 * @author kanabc12@126.com
	 * @return
	 */
	public int countAllArticle();
	
	
	public int count(String title,int proviceId,int subjectId);

}
