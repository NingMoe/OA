<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%	String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css"
	href="../../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="../../css/icon.css">
<link rel="stylesheet" type="text/css"
	href="../../jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/validate.extended.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/validate.extended.mine.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/util.js"></script>

<style type="text/css">
#depart_edit_Form tr {
	text-align: right;
}
</style>
</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">部 门 管 理</h3>
		</div>
		<hr color="#eaf2ff" />
		<div>

			<!-- 数据表格  START-->
			<table id="dg" title="  " style="width: 800px; height: 365px"
				toolbar="#toolbar" pagination="true" idField="departId"
				rownumbers="true" fitColumns="true" singleSelect="true">
				<thead>
					<tr>
						<th field="departName" width="80">部门名称</th>
						<th field="branchinfo" width="200" formatter="branchFormatter">机构</th>
						<th field="userinfo" width="100" formatter="userFormatter">负责人</th>
						<th field="connectTelNo" width="100">联系电话</th>
						<th field="connectMobileTelNo" width="100">移动电话</th>
						<th field="faxes" width="100">传真</th>
					</tr>
				</thead>
			</table>

			<div id="toolbar">
				<a id="aAdd" href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="append()">添加部门</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="removeDepart()"
					iconCls="icon-remove" plain="true">删除部门</a> <a id="aUpdate"
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="modify()">修改部门</a>
			</div>
			<!-- 数据表格  END-->

		</div>

		<!-- 弹出编辑对话框  BEGIN-->
		<div id="depart_edit_Dialog" class="easyui-dialog" title="  编  辑"
			data-options="iconCls:'icon-edit',closed:true,modal:true"
			style="width: 300px; height: 250px; padding: 10px">

			<form id="depart_edit_Form" action="<%=path%>/depart/addOrUpdate"
				method="post">
				<table>
					<tr>
						<td>所属机构:</td>
						<td><input id="branchinfo" class="easyui-combobox"
							name="depart.branchinfo.branchId" editable="false"
							data-options="valueField:'branchId',textField:'branchName',url:'<%=path%>/depart/addBeforeGetBranch',panelHeight:'auto',required:true,onChange:function(){
				$('#departName').parent().parent().show();}">
						</td>
					</tr>
					<tr>
						<td>部门名称:<input id="departId" name="depart.departId"
							type="hidden" /></td>
						<td><input id="departName" class="easyui-validatebox"
							type="text" name="depart.departName" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<td>部门负责人:</td>
						<td><input id="userinfo" class="easyui-combotree"
							name="depart.userinfo.userId" lines="true"
							data-options="url:'<%=path%>/depart/showEmployeeTree',required:true,parentField:'pid',onBeforeSelect:function(node){return onBeforeSelect(node)}">
						</td>
					</tr>
					<tr>
						<td>联系电话:</td>
						<td><input id="connectTelNo" class="easyui-validatebox"
							type="text" name="depart.connectTelNo" validType="telephone"></input>
						</td>
					</tr>
					<tr>
						<td>移动电话:</td>
						<td><input id="connectMobileTelNo" class="easyui-validatebox"
							type="text" name="depart.connectMobileTelNo" validType="mobile"></input>
						</td>
					</tr>
					<tr>
						<td>传真:</td>
						<td><input id="faxes" class="easyui-validatebox" type="text"
							name="depart.faxes" validType="faxno"></input></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 弹出编辑对话框  BEGIN-->

	</center>
	<script type="text/javascript">
	
		/* -------------------- datagrid ----------------------- */
		//加载所有部门
		$(function() {
			$('#dg').edatagrid({
				url : '<%=path%>/depart/showPage'
			});
			
			$('#depart_edit_Dialog').dialog({
				buttons:[{
					text:' 提 交 ',
					iconCls:'icon-save',
					handler:function(){
						submitForm();
					}
				},{
					text:' 关闭 ',
					iconCls:'icon-cancel',
					handler:function(){
						$('#depart_edit_Dialog').dialog('close');
					}
				}]
			});
			
		});
		
		//获取机构名称(提取子属性)
		function branchFormatter(val){
			if(val){
				return val.branchName;
			}else{
				return "";
			}
		}
		
		//获取用户名称(提取子属性)
		function userFormatter(val){
			if(val){
				return val.userName;
			}else{
				return "";
			}
		}
		
		//未选中,弹出消息
		function rowUncheckedMessage() {
			$.messager.show({
					title : '提示',
					iconCls:'icon-message',
					msg : '请选中一行!',
					closable:false
				});
		}
		
		/* -------------------- toolbar ----------------------- */
		var oper;//提供一个当前操作的辨认值
		
		//添加部门
		function append() {
			oper = 'append';
			//打开对话框
			$('#depart_edit_Form').form('clear');
			$('#depart_edit_Dialog').dialog("open");
			$('#departName').validatebox({validType:'DEPART_EXISTS'});
			$('#departName').parent().parent().hide(); 
		}

		//修改部门
		function modify() {
			oper = 'modify';
			var rowData=$('#dg').datagrid('getSelected');
			if (rowData!=null) {
				//清空表单数据
				$('#depart_edit_Form').form('clear');
				$("#departId").val(rowData.departId);
				$("#departName").val(rowData.departName);
				
				$("#branchinfo").combobox('select', rowData.branchinfo.branchId);
				
				// 指定员工节点
				var node=$('#userinfo').combotree('tree').tree('find',rowData.userinfo.userId);
				if(node){
					$('#userinfo').combotree('setValue',node.attributes.userId);
				}
				
				$('#connectTelNo').val(rowData.connectTelNo);
				$("#connectMobileTelNo").val(rowData.connectMobileTelNo);
				$("#faxes").val(rowData.faxes);
				//打开编辑对话框
				$('#depart_edit_Dialog').dialog('open');
				$('#departName').validatebox({validType:'DEPART_EXISTS[\''+ rowData.departName+'\']'});
			}else{
				//没有选中行
				rowUncheckedMessage();
				return;
			}

		}
		
		//删除部门
		function removeDepart(){
			var rowData=$('#dg').datagrid('getSelected');
			if (rowData!=null) {
				$.messager.confirm("注意", "是否确定要删除\""+rowData.departName+"\",以及该部门下的所有员工信息?", function(r){
					if(r){
						$.ajax({
							url:'<%=path%>/depart/remove',
							data:'id='+rowData.departId,
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
				return;
			}
		}
		
		/* -------------------- dialog ----------------------- */
		function onBeforeSelect(node){
			if(node.attributes['userId']==undefined){
				/* $.messager.show({
					title:'提示',
					iconCls:'icon-message',
					msg:'你选择了一个非员工的无效选项!',
					closable:false
				}); */
				return false;
			}else if(node.iconCls=="role-manage"){
				if(oper == 'modify'){
					//用户正在执行修改操作
					var row = $('#dg').datagrid('getSelected');
					if(row && node.attributes['userId']==row.userinfo.userId)return true;
				}
				/* $.messager.show({
					title:'注意',
					iconCls:'icon-warning',
					msg:'你选择了一个其他部门的负责人! 仅能选择普通员工或本部门负责人!',
					closable:false
				}); */
				return false;
			}
		}
		
		function submitForm() {
			$('#depart_edit_Form').form('submit',{
				success: function(){
					//关闭编辑对话框
					$('#depart_edit_Dialog').dialog('close');
					$('#dg').datagrid('load');
					$('#userinfo').combotree('reload');
				}
			});
		}
		
		function clearForm() {
			$('#depart_edit_Form').form('clear');
		}
	</script>
</body>
</html>