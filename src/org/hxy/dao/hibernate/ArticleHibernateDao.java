package org.hxy.dao.hibernate;

import java.util.List;

import org.hxy.dao.IArticleDao;
import org.hxy.model.Article;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class ArticleHibernateDao extends BaseHibernateDao<Article, Integer> implements
		IArticleDao {

	@Override
	public void save(Article model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdate(Article model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Article model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void merge(Article model) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Article get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Article> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> listAll(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countAllArticle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Article> getArticlesByTitle(String title,int pn,int pageSize) {
		String hql = "from Article where title  like '%"+title+"%'";
		List<Article> result = list(hql,pn,pageSize,null);
		return result;
	}

}