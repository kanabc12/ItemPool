package org.hxy.dao;

import java.util.List;

import org.hxy.model.Article;

import cn.javass.commons.dao.IBaseDao;

public interface IArticleDao extends IBaseDao<Article, Integer> {
	/**
	 * 查询所有的文章
	 * @author kanabc12@126.com
	 * @return
	 */
	public int countAllArticle();
	
	public int count(String title,int proviceId,int subjectId);
	
	public List<Article> getArticlesByTitle(String title,int pn,int pageSize);
	
	public List<Article> getArticles(String title,int pn,int pageSize,int proviceId,int subjectId);
	
	public List<Article> getArticleBySubject(int subjectID,int pn,int pageSize);
}
