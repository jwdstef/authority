var pageii = null;
var grid = null;
$(function() {
	grid = lyGrid({
		id : 'paging',
		l_column : [ {
			colkey : "id",
			name : "id",
			hide : true
		}, {
			colkey : "name",
			name : "部门名称",
			width : "100px",
			align : 'left'
		}, {
			colkey : "parentId",
			name : "上级部门",

		}, {
			colkey : "deptgrade",
			name : "部门等级"
		}, {
			colkey : "description",
			name : "描述"
		}, {
			colkey : "createtime",
			width : "100px",
			name : "创建时间"
		} ],
		jsonUrl : rootPath + '/dept/treelists.shtml',
		checkbox : true,
		usePage : false,
		records : "treelists",
		treeGrid : {
			type:1,
			tree : true,
			name : 'name',
			id: "id",
			pid: "parentId"
		}
	});
	$("#seach").click("click", function() {// 绑定查询按扭
		var searchParams = $("#searchForm").serializeJson();
		grid.setOptions({
			data : searchParams
		});
	});
	$("#addFun").click("click", function() {
		addFun();
	});
	$("#editFun").click("click", function() {
		editFun();
	});
	$("#delFun").click("click", function() {
		delFun();
	});
	$("#lyGridUp").click("click", function() {// 上移
		var jsonUrl=rootPath + '/background/dept/sortUpdate.shtml';
		grid.lyGridUp(jsonUrl);
	});
	$("#lyGridDown").click("click", function() {// 下移
		var jsonUrl=rootPath + '/background/dept/sortUpdate.shtml';
		grid.lyGridDown(jsonUrl);
	});
});
function editFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox.length > 1 || cbox == "") {
		layer.alert("只能选中一个");
		return;
	}
	pageii = layer.open({
		title : "编辑",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/dept/editUI.shtml?id=' + cbox
	});
}
function addFun() {
	pageii = layer.open({
		title : "新增",
		type : 2,
		area : [ "600px", "80%" ],
		content : rootPath + '/dept/addUI.shtml'
	});
}
function delFun() {
	var cbox = grid.getSelectedCheckbox();
	if (cbox == "") {
		layer.alert("请选择删除项！！");
		return;
	}
	layer.confirm('是否删除？', function(index) {
		var url = rootPath + '/dept/deleteEntity.shtml';
		var s = CommnUtil.ajax(url, {
			ids : cbox.join(",")
		}, "json");
		if (s == "success") {
			layer.msg('删除成功');
			grid.loadData();
		} else {
			layer.msg('删除失败');
		}
	});
}
