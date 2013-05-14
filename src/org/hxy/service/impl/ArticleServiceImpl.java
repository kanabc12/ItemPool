package org.hxy.service.impl;

import java.util.List;

import org.hxy.dao.IArticleDao;
import org.hxy.model.Article;
import org.hxy.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.javass.commons.pagination.Page;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleDao articleDao;
	
	@Override
	public Article save(Article model) {
		// TODO Auto-generated method stub
		return null;
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
	public Page<Article> listAll(int pn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Article> listAll(int pn, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getArticlesByTitle(String title,int pn,int pageSize) {
		return articleDao.getArticlesByTitle(title, pn, pageSize);
	}

}
