package com.prisys.mapper;

import java.util.List;

import com.prisys.entity.DeptFormMap;
import com.prisys.mapper.base.BaseMapper;

public interface DeptMapper extends BaseMapper{
	public List<DeptFormMap> findChildlists(DeptFormMap map);

	public List<DeptFormMap> findRes(DeptFormMap map);

	
}
