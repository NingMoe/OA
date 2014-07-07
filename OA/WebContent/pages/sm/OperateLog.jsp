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
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>

</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">操 作 日 志</h3>
			<hr color="#eaf2ff" />
			请选择时间段: <input class="easyui-datebox"
				data-options="onSelect:onSelectoperateTime"></input> <input
				class="easyui-datebox" data-options="onSelect:onSelectnextTime"></input>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-ss',plain:true" onclick=" doSearch()">搜索</a>


		</div>
		<hr color="#eaf2ff" />
		<div>
			<!-- 数据表格  START-->
			<table id="dg" style="width: 800px; height: 365px" rownumbers="true"
				toolbar="#toolbar" pagination="true" idField="operateId"
				fitColumns="true" singleSelect="true">
				<thead>
					<tr>
						<th field="userinfo" width="100" formatter="userinfoFormatter">操作用户</th>
						<th field="operateName" width="200">状态</th>
						<th field="operateDesc" width="100">操作描述</th>
						<th field="operateTime" width="100">操作时间</th>

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
				url : '${pageContext.request.contextPath}/operatelog/showPage',
				destroyUrl : '${pageContext.request.contextPath}/operatelog/remove'
			});
		});

		function userinfoFormatter(val) {
			if (val) {
				return val.userName;
			} else {
				return "";
			}
		}

		//得到两个日期控制的
		var operateTime, nextTime;
		function onSelectoperateTime(date) {
			operateTime = date.getFullYear() + '-' + (date.getMonth() + 1)
					+ '-' + date.getDate();
		}
		function onSelectnextTime(date) {
			nextTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ (date.getDate() + 1);
		}
		// 点击搜索
		function doSearch() {
			$('#dg').datagrid(
					{
						url : "${pageContext.request.contextPath}/operatelog/Search?operateTime="
								+ operateTime + "&nextTime=" + nextTime
					});

		}
	</script>
</body>
</html>