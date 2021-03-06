<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入jquery核心类库 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<!-- 导入easyui类库 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/js/easyui/ext/portal.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/default.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.portal.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/easyui/ext/jquery.cookie.js"></script>
<script
	src="${pageContext.request.contextPath }/js/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>
<script type="text/javascript">
	function doAdd() {
		//alert("增加...");
		$('#addStaffWindow').window("open");
	}

	function doView() {
		
		
		//获得选中的行
		var rows = $("#grid").datagrid("getSelections");
		
		if (rows.length == 1) {
			var array=new Array();
			for (var i = 0; i < rows.length; i++) {
				var id=rows[i].id;
				array.push(id);
			}
			
			  $.post("${pageContext.request.contextPath}/staffAction_view.action",
					    {
					     ids:array[0]
					    },
					    function(data){
					    
					     $('#viewStaffWindow').form("load",data);
						$('#viewStaffWindow').window("open");
				 });
		}else{
			$.messager.alert("提示信息", "请选择一条查看", "warning")
		}
		 

	}
	

	function doDelete() {
		//获得选中的行
		var rows = $("#grid").datagrid("getSelections");
		if (rows.length == 0) {
		
			$.messager.alert("提示信息", "请选择需要作废的记录", "warning")
		} else {
			var array=new Array();
			for (var i = 0; i < rows.length; i++) {
				var id=rows[i].id;
				array.push(id);
			}
			var ids=array.join(",");//1,2,3
			window.location.href = '${pageContext.request.contextPath}/staffAction_delete.action?ids='+ids;
		}

	}

	function doRestore() {
		alert("将取派员还原...");
	}

	//扩展校验规则
	$(function() {
		var reg = /^1[3|4|5|7|8|9][0-9]{9}$/;
		$.extend($.fn.validatebox.defaults.rules, {
			phonenumber : {
				validator : function(value, param) {
					return reg.test(value);
				},
				message : '手机号输入有误！'
			}
		});
	});
	//工具栏
	var toolbar = [ {
		id : 'button-view',
		text : '查询',
		iconCls : 'icon-search',
		handler : doView
	}, 
	<shiro:hasPermission name="staff">
	{
		id : 'button-add',
		text : '增加',
		iconCls : 'icon-add',
		handler : doAdd
	},
	</shiro:hasPermission>
	
	<shiro:hasPermission name="staff">
	{
		id : 'button-delete',
		text : '作废',
		iconCls : 'icon-cancel',
		handler : doDelete
	},
	</shiro:hasPermission>
  {
		id : 'button-save',
		text : '还原',
		iconCls : 'icon-save',
		handler : doRestore
	} ];
	// 定义列
	var columns = [ [ {
		field : 'id',
		checkbox : true,
	}, {
		field : 'name',
		title : '姓名',
		width : 120,
		align : 'center'
	}, {
		field : 'telephone',
		title : '手机号',
		width : 120,
		align : 'center'
	}, {
		field : 'haspda',
		title : '是否有PDA',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "1") {
				return "有";
			} else {
				return "无";
			}
		}
	}, {
		field : 'deltag',
		title : '是否作废',
		width : 120,
		align : 'center',
		formatter : function(data, row, index) {
			if (data == "0") {
				return "正常使用"
			} else {
				return "已作废";
			}
		}
	}, {
		field : 'standard',
		title : '取派标准',
		width : 120,
		align : 'center'
	}, {
		field : 'station',
		title : '所谓单位',
		width : 200,
		align : 'center'
	} ] ];

	$(function() {
		// 先将body隐藏，再显示，不会出现页面刷新效果
		$("body").css({
			visibility : "visible"
		});

		// 取派员信息表格
		$('#grid')
				.datagrid(
						{
							iconCls : 'icon-forward',
							fit : true,
							border : false,
							rownumbers : true,
							striped : true,
							pageList : [ 3, 5, 10 ],
							pagination : true,
							toolbar : toolbar,
							url : "${pageContext.request.contextPath}/staffAction_pageQuery.action",
							idField : 'id',
							columns : columns,
							onDblClickRow : doDblClickRow
						});

		// 添加取派员窗口
		$('#addStaffWindow').window({
			title : '添加取派员',
			width : 400,
			modal : true, //遮罩
			shadow : true,//阴影
			closed : true,//关闭状态
			height : 400,
			resizable : false//可以调整大小
		});

		// 添加取派员窗口
		$('#editStaffWindow').window({
			title : '修改取派员',
			width : 400,
			modal : true, //遮罩
			shadow : true,//阴影
			closed : true,//关闭状态
			height : 400,
			resizable : false//可以调整大小
		});
		// 查看窗口
		$('#viewStaffWindow').window({
			title : '修改取派员',
			width : 400,
			modal : true, //遮罩
			shadow : true,//阴影
			closed : true,//关闭状态
			
			resizable : false//可以调整大小
		});
	});

	function doDblClickRow(rowIndex, rowData) {
		$('#editStaffWindow').form("load",rowData);
		$('#editStaffWindow').window("open");
	}
</script>
</head>
<body class="easyui-layout" style="visibility: hidden;">
	
	<div region="center" border="false">
		<table id="grid"></table>
	</div>
	<div class="easyui-window" title="对收派员进行添加或者修改" id="addStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="save" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
					$(function() {
						//绑定事件
						$("#save").click(function() {
							//校验表单输入项
							var v = $("#addStaffForm").form("validate");
							if (v) {
								//校验通过，提交表单
								$("#addStaffForm").submit();
							}
						});
					});
				</script>

			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="addStaffForm"
				action="${pageContext.request.contextPath }/staffAction_add.action"
				method="post">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- <tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							data-options="'validType':'phonenumber'"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<!-- 修改窗口 -->
	<div class="easyui-window" title="对收派员进行添加或者修改" id="editStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
					$(function() {
						//绑定事件
						$("#edit").click(function() {
							//校验表单输入项
							var v = $("#editStaffForm").form("validate");
							if (v) {
								//校验通过，提交表单
								$("#editStaffForm").submit();
							}
						});
					});
				</script>

			</div>
		</div>

		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="editStaffForm"
				action="${pageContext.request.contextPath }/staffAction_edit.action"
				method="post">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- <tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							required="true" /></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							data-options="'validType':'phonenumber'"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							class="easyui-validatebox" required="true" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							class="easyui-validatebox" required="true" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
<!-- 查看 -->	
	<div class="easyui-window" title="查看" id="viewStaffWindow"
		collapsible="false" minimizable="false" maximizable="false"
		style="top: 20px; left: 200px">
		<div region="north" style="height: 31px; overflow: hidden;"
			split="false" border="false">
			<!-- <div class="datagrid-toolbar">
				<a id="edit" icon="icon-save" href="#" class="easyui-linkbutton"
					plain="true">保存</a>
				<script type="text/javascript">
					$(function() {
						//绑定事件
						$("#edit").click(function() {
							//校验表单输入项
							var v = $("#viewStaffForm").form("validate");
							if (v) {
								//校验通过，提交表单
								$("#editStaffForm").submit();
							}
						});
					});
				</script>

			</div> -->
		</div>

		<div region="center" style="overflow: auto; padding: 5px;"
			border="false">
			<form id="viewStaffForm"
				action="#"
				method="post">
				<input type="hidden" name="id">
				<table class="table-edit" width="80%" align="center">
					<tr class="title">
						<td colspan="2">收派员信息</td>
					</tr>
					<!-- TODO 这里完善收派员添加 table -->
					<!-- <tr>
						<td>取派员编号</td>
						<td><input type="text" name="id" class="easyui-validatebox" required="true"/></td>
					</tr> -->
					<tr>
						<td>姓名</td>
						<td><input type="text" name="name" class="easyui-validatebox"
							/></td>
					</tr>
					<tr>
						<td>手机</td>
						<td><input type="text" name="telephone"
							data-options="'validType':'phonenumber'"
							class="easyui-validatebox" /></td>
					</tr>
					<tr>
						<td>单位</td>
						<td><input type="text" name="station"
							class="easyui-validatebox"  /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="checkbox" name="haspda"
							value="1" /> 是否有PDA</td>
					</tr>
					<tr>
						<td>取派标准</td>
						<td><input type="text" name="standard"
							class="easyui-validatebox"  /></td>
					</tr>
					<tr>
						<td>状态</td>
						<td><input type="text" name="deltag"
							 value="${deltag==1?'已作废':'正常使用'}" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
