package org.hxy.dao;

import org.hxy.model.Discipline;

import cn.javass.commons.dao.IBaseDao;

public interface IDisciplineDao extends IBaseDao<Discipline, Integer> {
	/**
	 * 查询所有的试题
	 * @author kanabc12@126.com
	 * @return
	 */
	public int countAllDisciline();
	
	/**
	 * 获取所有试题
	 * @author robbin
	 */
	public int getAllQuestion();
	
	/**
	 * 获取最新试题
	 * @author robbin
	 */
	
	public int getLastQuestion();
}
