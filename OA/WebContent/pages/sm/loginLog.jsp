<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>

</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">登 录 日 志</h3>
			<hr color="#eaf2ff" />
			请选择时间:
			<input class="easyui-datebox" data-options="onSelect:onSelect" ></input>
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ss',plain:true" onclick=" doSearch()">搜索</a>
			
			
		</div>
		<hr color="#eaf2ff" />
		
        
		<div>
			<!-- 数据表格  START-->
			<table id="dg" style="width: 800px; height: 365px" rownumbers="true"
				toolbar="#toolbar" pagination="true" idField="loginId"
				fitColumns="true" singleSelect="true">
				<thead>
					<tr>

						<th field="userinfo" width="100" formatter="userinfoFormatter">登录用户</th>
						<th field="loginTime" width="200">登录时间</th>
						<th field="null" width="200">退出时间</th>
						<th field="loginUserIp" width="100">Ip地址</th>
						<th field="ifSuccess" width="100" data-options="formatter:ifSuccessFormat">是否成功</th>
						<th field="loginDesc" width="100">登录备注</th>

					</tr>
				</thead>
			</table>

			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"
					onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-undo" plain="true"
					onclick="javascript:$('#dg').edatagrid('cancelRow')">撤销</a>
			
					
			</div>
			<!-- 数据表格  END-->

		</div>


	</center>
	<script type="text/javascript">
		//加载所有
		$(function() {
			$('#dg').edatagrid({
				url : '<%=path%>/loginlog/showPage',
				destroyUrl : '<%=path%>/loginlog/remove'
			
			});
		});

		function userinfoFormatter(val) {
			if (val) {
				return val.userName;
			} else {
				return "";
			}
		}
		
		//格式化
		function ifSuccessFormat(val, row) {
			console.info(val);
			if (val==1)
				return "是";
			else
				return "否";
		}
		
		
		// 选中日期控件
		var loginTime;
		var nextTime;
		function onSelect(date) {
			loginTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate();
			nextTime=date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
			+ (date.getDate()+1);
			
		}
		// 点击搜索
		function doSearch() {
			$('#dg').datagrid({
				url : "/OA/loginlog/search?loginTime="+loginTime +"&nextTime="+nextTime
			});
		
		}

		// 全部删除
		function deteleAll() {
			$('#dg').datagrid({
				url : "/OA/loginlog/deleteAll"
			});
		

		}
	</script>
</body>
</html>