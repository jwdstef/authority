package com.prisys.mapper;

import java.util.List;

import com.prisys.entity.BbtUserMap;
import com.prisys.mapper.base.BaseMapper;

public interface BbtUserMapper extends BaseMapper{

	public List<BbtUserMap> seletUser(BbtUserMap userFormMap);
	
}
