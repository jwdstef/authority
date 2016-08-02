package com.prisys.entity;

import com.prisys.annotation.TableSeg;
import com.prisys.util.FormMap;

@TableSeg(tableName = "ly_server_info", id="id")
public class ServerInfoFormMap extends FormMap<String, Object> {
	private static final long serialVersionUID = 1L;
}