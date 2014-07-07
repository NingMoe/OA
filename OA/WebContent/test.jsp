<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<title>Document</title>
</head> 
<body>
	<table id="dg" class="easyui-datagrid" title="数据表格复选框选择模型"
		style="width: auto; height: 450px;"
		data-options="rownumbers:true,singleSelect:true,url:'../datagrid/datagrid_data1.json'">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'itemid',width:80">编号</th>
				<th data-options="field:'productid',width:100">产品编号</th>
				<th data-options="field:'listprice',width:80,align:'right'">市场价</th>
				<th data-options="field:'unitcost',width:80,align:'right'">成本价</th>
				<th data-options="field:'attr1',width:250">描述</th>
				<th data-options="field:'status',width:60,align:'center'">状态</th>
			</tr>
		</thead>
	</table>
	<div style="margin: 10px 0;">
		<span>选择模型: </span> <select
			onchange="$('#dg').datagrid({singleSelect:(this.value==0)})">
			<option value="0">单选</option>
			<option value="1">多选</option>
		</select><br /> 选择联动复选框: <input type="checkbox" checked
			onchange="$('#dg').datagrid({selectOnCheck:$(this).is(':checked')})"><br />
		复选框联动选择: <input type="checkbox" checked
			onchange="$('#dg').datagrid({checkOnSelect:$(this).is(':checked')})">
	</div>
	<script type="text/javascript">
	$(document).ready(function(){
		alert('文件名称不能包含以下任何字符之一: \\/:*?\"<>|!');
	});
	</script>
</body>
</html>