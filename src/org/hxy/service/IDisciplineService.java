package org.hxy.service;

import org.hxy.model.Discipline;

import cn.javass.commons.service.IBaseService;

public interface IDisciplineService extends IBaseService<Discipline,Integer> {
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
