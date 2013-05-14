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
	
	public List<Article> getArticlesByTitle(String title,int pn,int pageSize);
	

}
