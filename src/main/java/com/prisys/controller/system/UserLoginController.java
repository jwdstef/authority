package com.prisys.controller.system;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prisys.controller.index.BaseController;
import com.prisys.entity.UserLoginFormMap;
import com.prisys.mapper.UserLoginMapper;
import com.prisys.plugin.PageView;
import com.prisys.util.Common;

/**
 * 
 * @author zqb 
 * @Email: 
 * @version 1.0v
 */
@Controller
@RequestMapping("/userlogin/")
public class UserLoginController extends BaseController {
	@Inject
	private UserLoginMapper userLoginMapper;

	@RequestMapping("listUI")
	public String listUI(Model model) throws Exception {
		return Common.BACKGROUND_PATH + "/system/userlogin/list";
	}

	@ResponseBody
	@RequestMapping("findByPage")
	public PageView findByPage(String pageNow,
			String pageSize) throws Exception {
		UserLoginFormMap userLoginFormMap = getFormMap(UserLoginFormMap.class);
		userLoginFormMap=toFormMap(userLoginFormMap, pageNow, pageSize);
        pageView.setRecords(userLoginMapper.findByPage(userLoginFormMap));
		return pageView;
	}

}