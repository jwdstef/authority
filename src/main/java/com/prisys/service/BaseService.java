package com.prisys.service;

import java.util.List;

public interface BaseService  {
	
	public <T> List<T> findByPage(T formMap);

	public <T> List<T> findByWhere(T formMap);

	public void editEntity(Object formMap) throws Exception;

	public <T> List<T> findByNames(T formMap);

	public <T> List<T> findByAttribute(String key, String value, Class<T> clazz);

	public void deleteByAttribute(String key, String value, Class clazz) throws Exception;

	public void addEntity(Object formMap) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public void batchSave(List formMap) throws Exception;

	public void deleteByNames(Object formMap) throws Exception;
	
	public <T> T findbyFrist(String key,String value,Class<T> clazz);
}
