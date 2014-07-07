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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<script type="text/javascript" src="<%=path%>/javascript/util.js"></script>
</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">消 息 传 递</h3>
			<hr color="#eaf2ff" />
			请选择时间段: <input class="easyui-datebox"
				data-options="onSelect:onSelectoperateTime"></input> <input
				class="easyui-datebox" data-options="onSelect:onSelectnextTime"></input>
			<a href="javascript:void(0)" class="easyui-linkbutton"
				data-options="iconCls:'icon-ss',plain:true" onclick=" doSearch()">搜索</a>
			<input type="radio" name="date">本日 <input type="radio"
				name="date">本周 <input type="radio" name="date">本月 <a
				class="easyui-linkbutton" id="cancel">取消选中</a>
		</div>
		<hr color="#eaf2ff" />

		<div>
			<!-- 数据表格  START-->
			<table id="dg" style="width: 800px; height: 365px" rownumbers="true"
				toolbar="#toolbar" pagination="true" idField="messageId"
				fitColumns="true" singleSelect="true">
				<thead>
					<tr>
						<th field="title" width="100">消息标题</th>
						<th field="messagetype" width="100">消息类型</th>
						<th field="content" width="100">消息内容</th>
						<th field="UserId" width="100">创建者</th>
						<th field="fromUserId" width="100">发送对象</th>
						<th field="beginTime" width="200">开始时间</th>
						<th field="endTime" width="200">结束时间</th>
						<th field="recordTime" width="200">创建时间</th>

					</tr>
				</thead>
			</table>

			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true"
					onclick="javascript:$('#dg').edatagrid('addRow')">发布</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"
					onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-save" plain="true"
					onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a> <a
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
				url : '<%=path%>/message/showPage',
				saveUrl : '<%=path%>/message/add',
				updateUrl : '<%=path%>/message/update',
				destroyUrl : '<%=path%>/message/remove'
			});
		});

		
		
		//得到两个日期控制的
		var operateTime, nextTime;
		function onSelectoperateTime(date) {
			operateTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate();
		}
		function onSelectnextTime(date) {
			nextTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ (date.getDate()+1);
		}
		// 点击搜索
	
	
	</script>
</body>

</html>