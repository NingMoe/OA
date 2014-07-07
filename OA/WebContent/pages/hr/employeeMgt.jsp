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
<script type="text/javascript"
	src="<%=path%>/javascript/validate.extended.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/employeeMgt.js"></script>
<script type="text/javascript">
	var btns = [ {
		text : '提交',
		handler : function() {
			submitForm();
		}
	}, {
		text : '重置',
		handler : function() {
			clearForm();
		}
	}, {
		text : '关闭',
		handler : function() {
			$('#user_edit_Dialog').dialog('close');
		}
	}];
</script>
<style type="text/css">
th {
	text-align: right;
	font-weight: normal;
}
</style>
</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">员 工 管 理</h3>
		</div>
		<hr color="#eaf2ff" />
		<div>

			<!-- 数据表格  START-->
			<table id="dg" style="width: 800px; height: 365px" toolbar="#toolbar"
				pagination="true" idField="userId" rownumbers="true"
				fitColumns="true" singleSelect="true" title="  ">
				<thead>
					<tr>
						<th field="userId" width="150">员工ID</th>
						<th field="userName" width="150">姓名</th>
						<!-- <th field="passWord" width="200">密码</th> -->
						<th field="roleinfos" width="150" formatter="roleinfosFormatter">角色</th>
						<th field="gender" width="150" formatter="genderFormatter">性别</th>
						<th field="userstate" width="150" formatter="stateFormatter">状态</th>
					</tr>
				</thead>
			</table>

			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true" onclick="append()">添加</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-remove" plain="true"
					onclick="javascript:$('#dg').edatagrid('destroyRow');">删除</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-edit" plain="true" onclick="modify()">修改</a><a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="role-manage" plain="true" onclick="view()">详细信息</a>

			</div>
			<!-- 数据表格  END-->

		</div>

		<!-- 弹出编辑对话框  BEGIN-->
		<div id="user_edit_Dialog" class="easyui-dialog" title="  编  辑"
			data-options="iconCls:'icon-edit_add',closed:true,modal:true,onOpen:userEditOpen(),buttons:btns"
			style="width: 350px; height: 415px; padding: 10px">

			<form id="user_edit_Form" action="<%=path%>/user/addOrUpdate"
				method="post" enctype="multipart/form-data">
				<table style="margin: auto;">
					<tr>
						<th>头像:</th>
						<td><img
							style="width: 130px; height: 100px; border: 1px solid;"
							id="imgIcon" alt="图像未找到" onerror="nofind(event)" dir="ltr" /></td>
					</tr>
					<tr>
						<th>用户登录名:</th>
						<td><input id="userId" class="easyui-validatebox" type="text"
							name="userinfo.userId" data-options="required:true"></input></td>
					</tr>
					<tr>
						<th>密 码:</th>
						<td><input id="passWord" class="easyui-validatebox"
							type="password" data-options="required:true" validType="safepass"></input>
						</td>
					</tr>
					<tr>
						<th>确认密码:</th>
						<td><input id="rePassWord" class="easyui-validatebox"
							type="password" name="userinfo.passWord"
							data-options="required:true" validType="equalTo['#passWord']"></input>
						</td>
					</tr>
					<tr>
						<th>真实姓名:</th>
						<td><input id="userName" class="easyui-validatebox"
							type="text" name="userinfo.userName" data-options="required:true"></input>
						</td>
					</tr>
					<tr>
						<th>所在部门:</th>
						<td><input id="departinfo" class="easyui-combobox"
							editable="false" name="userinfo.departId"
							data-options="valueField:'departId',textField:'departName',panelHeight:'auto',url:'<%=path%>/user/showAllDepart',required:true">
						</td>
					</tr>
					<tr>
						<th>性别:</th>
						<td><select id="gender" name="userinfo.gender"
							class="easyui-combobox"
							data-options="panelHeight:'auto',editable:false,required:true">
								<option value="1">男</option>
								<option value="2">女</option>
						</select></td>
					</tr>
					<tr>
						<th>选择头像</th>
						<td><input id="uploadImg" type="file" name="file"
							onchange="validateFile(event)" style="width: 220px;"
							accept="image/*"></input></td>
					</tr>
					<tr>
						<th>角色:</th>
						<td><input id="roleName" class="easyui-combobox"
							editable="false" name="roleIds"
							data-options="valueField:'roleId',textField:'roleName',panelHeight:'auto',multiple:true,url:'<%=path%>/user/showAllRole',required:true"></input>
						</td>
					</tr>
					<tr>
						<th>当前状态:</th>
						<td><input id="userStateName" class="easyui-combobox"
							editable="false" name="userinfo.userstate.userStateId"
							data-options="valueField:'userStateId',textField:'userStateName',panelHeight:'auto',url:'<%=path%>/user/showAllState',required:true"></input>
						</td>
					</tr>
				</table>
			</form>
			<!-- <div style="text-align: center; padding: 5px" id="divFormBtn">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="submitForm()">提交</a>&nbsp;&nbsp;<a
					href="javascript:void(0)" class="easyui-linkbutton"
					onclick="clearForm()">重置</a>
			</div> -->

		</div>
		<!-- 弹出编辑对话框  BEGIN-->

		<!-- 弹出查看对话框  BEGIN-->
		<div id="user_view_Dialog" class="easyui-dialog" title="  详细信息"
			data-options="iconCls:'role-manage',closed:true,modal:true,onClose:function(){$('user_view_Dialog').dialog('destroy');}"
			style="width: 580px; height: 320px; padding: 10px; background-image: url('/OA/Images/bg.gif')">
			<jsp:include page="employeeView.jsp"></jsp:include>
		</div>
		<!-- 弹出查看对话框  BEGIN-->
	</center>
</body>
</html>