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
	href="<%=path%>/css/icon.css">
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<!-- 加上数据过滤器时,页面无法正常加载下一页 -->
<%-- <script type="text/javascript"
	src="<%=path%>/javascript/datagrid-filter.js"></script> --%>
</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">机  构  管  理</h3>
		</div>
		<hr color="#eaf2ff" />
		<div>

		<!-- 数据表格  START-->
		<table id="dg" style="width: 800px; height: 365px" rownumbers="true"
			toolbar="#toolbar" pagination="true" idField="branchId"
			fitColumns="true" singleSelect="true" title="  ">
			<thead>
				<tr>
					<th field="branchName" width="300"
						editor="{type:'validatebox',options:{required:true}}">机构名称</th>
					<th field="branchShortName" width="100"
						editor="{type:'validatebox',options:{required:true}}">机构简称</th>
				</tr>
			</thead>
		</table>

		<div id="toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-add" plain="true"
				onclick="javascript:$('#dg').edatagrid('addRow')" >添加机构</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-remove" plain="true"
				onclick="removeBranch()">删除机构</a><a
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
		//未选中行,弹出消息
		function rowUncheckedMessage() {
			$.messager.show({
					title : '提示',
					iconCls:'icon-message',
					msg : '请选择一个机构!',
					closable:false
				});
		}

		//加载所有机构
		$(function() {
			$('#dg').edatagrid({
				url : '<%=path%>/branch/showPage',
				saveUrl : '<%=path%>/branch/add',
				updateUrl : '<%=path%>/branch/update',
				destroyMsg:{
					norecord:{	// when no record is selected
						title:'警告',
						msg:'没有记录被选中!'
					},
					confirm:{	// when select a row
						title:'注意',
						msg:'即将删除\"'/* +$('#dg').datagrid('getSelected').branchName */
							+'\",以及该机构下的所有部门和所有员工! 是否继续删除?'
					}
				}
			});
			
			/* $('#dg').datagrid('enableFilter'); */
		});
		
		function removeBranch(){
			var rowData=$('#dg').datagrid('getSelected');
			if (rowData!=null) {
				$.messager.confirm("注意", '即将删除\"'+rowData.branchName+'\",以及该机构下的所有部门和所有员工! 是否继续删除?', function(r){
					if(r){
						$.ajax({
							url:'<%=path%>/branch/remove',
							data:'id='+rowData.branchId,
							method:'get',
							success:function(d){
								if(d && d.msg && d.msg!=""){
									if(d.msgtype._name=="info"){//不存在问题,就刷新结束
										$('#dg').datagrid('reload');
									}
									$.messager.show({
										title:d.msgtype._name,
										msg:d.msg,
										closable:true,
										iconCls:"icon-"+d.msgtype._name
									});
								}
							},
							error:function(){
								$.messager.show({
									title:'出错',
									iconCls:"icon-info",
									msg:'访问失败,请稍后再试!'
								});
							}
						});
					}
				});
				
			}else{
				//没有选中行
				rowUncheckedMessage();
			}
		}
	</script>
</body>
</html>