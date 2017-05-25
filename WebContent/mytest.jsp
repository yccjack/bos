<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
		<title></title>
		<script src="js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css" />
		<script src="js/easyui/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
		<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css" />
		<script src="js/ztree/jquery.ztree.all-3.5.js" type="text/javascript"></script>
		<script src="js/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
		<!-- 解决window空间bug -->
		<script src="js/easyui/outOfBounds.js" type="text/javascript"></script>
		<script type="text/javascript" src="js/jquery.ocupload-1.1.2.js"></script>
	</head>
	<script type="text/javascript">
	$(function(){
		$("#btn1").upload({
			action:'abc',
			name:'myfile'
		})
	});
	
	
	</script>
	<body>
	<input id="btn1" type="button" value="上传">
		<table id="myiii">
			
		</table>
		<script type="text/javascript">
			$(function(){
				$("#myiii").datagrid({columns:[[
					{'field':'id','title':'编号',checkbox:true},
					{'field':'name','title':'姓名'},
					{'field':'haspda','title':'有无pda'},
					{'field':'standard','title':'范围'},
					{'field':'station','title':'分部'}
				]],
				url:'${pageContext.request.contextPath}/json/test.json',
				toolbar:[
					 {text:'添加',iconCls:'icon-add'},
			         {text:'删除',iconCls:'icon-remove',
			        	 handler:function(){
			        	 	//获得选中的行
			        	 	var rows = $("#myiii").datagrid("getSelections");
			        	 	for(var i=0;i<rows.length;i++){
			        	 		var id = rows[i].id;
			        	 		alert(id);
			        	 	}
			         	  }},
			         {text:'修改',iconCls:'icon-edit'}
			         ],
			         singleSelect:true,
					pagination:true,//分页条
					pageList:[3,5,7]
				});
			});
			
		
		</script>
	</body>
</html>