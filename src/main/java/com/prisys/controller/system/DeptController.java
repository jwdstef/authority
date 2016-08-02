package com.prisys.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prisys.annotation.SystemLog;
import com.prisys.controller.index.BaseController;
import com.prisys.entity.ButtomFormMap;
import com.prisys.entity.DeptFormMap;
import com.prisys.service.DeptService;
import com.prisys.util.Common;
import com.prisys.util.TreeObject;
import com.prisys.util.TreeUtil;
@Controller
@RequestMapping("/dept/")
public class DeptController extends BaseController{

	@Autowired
	private DeptService deptService;
	
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/dept/list";
	}
	
	@ResponseBody
	@RequestMapping("treelists")
	public DeptFormMap findByPage(Model model) {
		DeptFormMap deptFormMap = getFormMap(DeptFormMap.class);
//		String order = " order by level asc";
//		deptFormMap.put("$orderby", order);
		List<DeptFormMap> mps = deptService.findByNames(deptFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (DeptFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
		deptFormMap = new DeptFormMap();
		deptFormMap.put("treelists", ns);
		return deptFormMap;
	}
	
	@ResponseBody
	@RequestMapping("reslists")
	public List<TreeObject> reslists(Model model) throws Exception {
		DeptFormMap deptFormMap = getFormMap(DeptFormMap.class);
		List<DeptFormMap> mps = deptService.findByWhere(deptFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (DeptFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0, "　");
		return ns;
	}
	
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name,String resKey) {
		DeptFormMap deptFormMap = getFormMap(DeptFormMap.class);
		List<DeptFormMap> r = deptService.findByNames(deptFormMap);
		if (r.size()==0) {
			return true;
		} else {
			return false;
		}
	}
	
	//@RequestMapping("addEntity")
	@RequestMapping(value = "addEntity", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="部门管理-新增部门")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		DeptFormMap deptFormMap = getFormMap(DeptFormMap.class);
		
		Object o = deptFormMap.get("parentId");
		if(null==o){
			deptFormMap.set("parentId", "0");
		}
		
		deptService.addEntity(deptFormMap);
		return "success";
	}
	
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/dept/add";
	}
	
	@ResponseBody
	@RequestMapping("findByButtom")
	public List<ButtomFormMap> findByButtom(){
		return deptService.findByWhere(new ButtomFormMap());
	}
	
	
}
