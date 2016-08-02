package com.prisys.mapper;

import java.util.List;

import com.prisys.entity.RoleFormMap;
import com.prisys.mapper.base.BaseMapper;

public interface RoleMapper extends BaseMapper{
	public List<RoleFormMap> seletUserRole(RoleFormMap map);
	
	public void deleteById(RoleFormMap map);
}
