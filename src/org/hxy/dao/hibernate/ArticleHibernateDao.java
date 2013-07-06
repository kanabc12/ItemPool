package org.hxy.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hxy.dao.IArticleDao;
import org.hxy.model.Article;
import org.hxy.model.ArticleID;

import cn.javass.commons.dao.hibernate.BaseHibernateDao;

public class ArticleHibernateDao extends BaseHibernateDao<Article, Integer>
		implements IArticleDao {

	public Article get(Integer id) {
		// TODO Auto-generated method stub
		Article article = null;
		String hql = "from Article where id=?";
		article = unique(hql, new Object[] { id });
		return article;
	}

	@Override
	public List<Article> getArticlesByTitle(String title, int pn, int pageSize) {
		String hql = "from Article where title  like '%" + title + "%'";
		List<Article> result = list(hql, pn, pageSize, null);
		return result;
	}

	@Override
	public List<Article> getArticles(String title, int pn, int pageSize,
			int proviceId, int subjectId) {
		List<Article> result = null;
		List<Object> paramlist = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer();
		sb.append("from Article where 1=1 ");
		if (!"".equals(title) && title != null) {
			sb.append(" and title like ?");
			paramlist.add("%" + title + "%");
		}
		if (proviceId != 0) {
			sb.append(" and proviceId=?");
			paramlist.add(new Integer(proviceId));
		}
		if (subjectId != 0) {
			sb.append(" and subjectId=?");
			paramlist.add(new Integer(subjectId));
		}
		sb.append(" and attach is not null order by postTime desc ");
		result = list(sb.toString(), pn, pageSize, paramlist.toArray());
		return result;
	}

	@Override
	public int countAllArticle() {
		String hql = "select count(*) from Article where attach is not null";
		Number count = unique(hql, null);
		return count.intValue();
	}

	@Override
	public int count(String title, int proviceId, int subjectId) {
		List<Object> paramlist = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select count(*) from Article where attach is not null");
		if (!"".equals(title) && title != null) {
			sb.append(" and title like ?");
			paramlist.add("%" + title + "%");
		}
		if (proviceId != 0) {
			sb.append(" and proviceId=?");
			paramlist.add(new Integer(proviceId));
		}
		if (subjectId != 0) {
			sb.append(" and subjectId=?");
			paramlist.add(new Integer(subjectId));
		}
		Number count = unique(sb.toString(), paramlist.toArray());
		return count.intValue();
	}

}
