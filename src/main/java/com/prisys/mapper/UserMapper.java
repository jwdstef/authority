package com.prisys.mapper;

import java.util.List;

import com.prisys.entity.UserFormMap;
import com.prisys.mapper.base.BaseMapper;


public interface UserMapper extends BaseMapper{

	public List<UserFormMap> findUserPage(UserFormMap userFormMap);
	
	
	public List<UserFormMap> seletUser(UserFormMap userFormMap);
}
