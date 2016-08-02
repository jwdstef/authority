/**
 * 
 */
$(function() {
	// 异步加载所有部门列表
	$("form").validate({
		submitHandler : function(form) {// 必须写在验证前面，否则无法ajax提交
			ly.ajaxSubmit(form,{//验证新增是否成功
				type : "post",
				dataType : "json",
				success : function(data) {
					
				alert(data);
					if (data == "success") {
						layer.confirm('添加成功!是否关闭窗口?', function(index) {
							parent.grid.loadData();
				        	parent.layer.close(parent.pageii);
				        	return false;
						});
 						$("#form")[0].reset();
					} else {
						layer.alert('添加失败！', 3);
					}
				}
			});
		},
		rules : {
			"deptFormMap.name" : {
				required : true,
				remote : { // 异步验证是否存在
					type : "POST",
					url : rootPath + '/dept/isExist.shtml',
					data : {
						name : function() {
							return $("#name").val();
						}
					}
				}
			}
			},
			
		messages : {
			"deptFormMap.name" : {
				required : "部门名称不能为空",
				remote : "该部门名称已经存在"
			}
		},
		errorPlacement : function(error, element) {// 自定义提示错误位置
			$(".l_err").css('display', 'block');
			// element.css('border','3px solid #FFCCCC');
			$(".l_err").html(error.html());
		},
		success : function(label) {// 验证通过后
			$(".l_err").css('display', 'none');
		}
	});
	var url = rootPath + '/dept/reslists.shtml';
	var data = CommnUtil.ajax(url, null,"json");
	if (data != null) {
		var h = "<option value='0'>------顶级目录------</option>";
		for ( var i = 0; i < data.length; i++) {
			h+="<option value='" + data[i].id + "'>"+ data[i].name + "</option>";
		}
		$("#parentId").html(h);
	} else {
		layer.msg("获取部门信息错误，请联系管理员！");
	}
});
function but(v){
	if(v.value==2){
		 showBut();
	}else{
		$("#divbut").css("display","none");
	}
}
function toBut(b){
	$("#description").val($("#"+b.id).html());
}
function showBut(){
	$("#divbut").css("display","block");
	var url = rootPath + '/dept/findByButtom.shtml';
	var data = CommnUtil.ajax(url, null,"json");
	if (data != null) {
		var bb = $("#but");
		bb.html('');
		for ( var i = 0; i < data.length; i++) {
			bb.append("<span onclick=\"toBut(this)\" id=\"span_"+data[i].id+"\">"+ data[i].buttom+"</span>");
		}
	} else {
		layer.msg("获取按扭列表失败！");
	}
}