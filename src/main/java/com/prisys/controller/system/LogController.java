package com.prisys.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prisys.controller.index.BaseController;
import com.prisys.entity.LogFormMap;
import com.prisys.mapper.LogMapper;
import com.prisys.plugin.PageView;
import com.prisys.util.Common;

/**
 * 
 * @author zqb 2014-11-19
 * @Email: 
 * @version 1.0v
 */
@Controller
@RequestMapping("/log/")
public class LogController extends BaseController {
	@Inject
	private LogMapper logMapper;

	@RequestMapping("list")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/log/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage( String pageNow,
			String pageSize) throws Exception {
		LogFormMap logFormMap = getFormMap(LogFormMap.class);
		String order = " order by id asc";
		logFormMap.put("$orderby", order);
		logFormMap=toFormMap(logFormMap, pageNow, pageSize);
        pageView.setRecords(logMapper.findByPage(logFormMap));
		return pageView;
	}
}