package com.prisys.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prisys.mapper.base.BaseMapper;
import com.prisys.service.BaseService;
import com.prisys.service.jedisclient.JedisClient;

@Service
public class BaseServiceImpl implements BaseService{

	@Autowired
	private BaseMapper baseMapper;
	
	@Autowired
	private JedisClient jedisClient;

	@Override
	public <T> List<T> findByPage(T formMap) {
		return baseMapper.findByPage(formMap);
	}

	@Override
	public <T> List<T> findByWhere(T formMap) {
		return baseMapper.findByWhere(formMap);
	}

	@Override
	public void editEntity(Object formMap) throws Exception {
		baseMapper.editEntity(formMap);
		
	}

	@Override
	public <T> List<T> findByNames(T formMap) {
		return baseMapper.findByNames(formMap);
	}

	@Override
	public <T> List<T> findByAttribute(String key, String value, Class<T> clazz) {
		return baseMapper.findByAttribute(key,value,clazz);
	}

	@Override
	public void deleteByAttribute(String key, String value, Class clazz) throws Exception {
		baseMapper.deleteByAttribute(key, value, clazz);
		
	}

	@Override
	public void addEntity(Object formMap) throws Exception {
		// 1、接收OrderInfo pojo
				// 2、生成orderid
				String key = jedisClient.get("JWD");
				if (StringUtils.isBlank(key)) {
					//设置初始值
					jedisClient.set("JWD", "10");
				}
				//取订单号
				Long orderId = jedisClient.incr("JWD");
				System.out.println("======>>>>>>>>>>"+orderId);
		baseMapper.addEntity(formMap);
	}

	@Override
	public void batchSave(List formMap) throws Exception {
		baseMapper.batchSave(formMap);
		
	}

	@Override
	public void deleteByNames(Object formMap) throws Exception {
		baseMapper.deleteByNames(formMap);
		
	}

	@Override
	public <T> T findbyFrist(String key, String value, Class<T> clazz) {
		return baseMapper.findbyFrist(key,value,clazz);
	}
	
	
	
}
