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
<script type="text/javascript" src="<%=path%>/javascript/util.js"></script>
</head>
<body>
	<center>
		<div style="margin: 0px 0px;">
			<h3 style="font-size: 20px; color: #ea3233">角 色 管 理</h3>
		</div>
		<hr color="#eaf2ff" />
		<div>

			<!-- 数据表格  START-->
			<table id="dg" style="width: 800px; height: 365px" rownumbers="true"
				toolbar="#toolbar" pagination="true" idField="roleId"
				fitColumns="true" singleSelect="true">
				<thead>
					<tr>
						<th field="roleId" width="100"></th>
						<th field="roleName" width="400"
							editor="{type:'validatebox',options:{required:true}}">角色名称</th>
						<th field="roleDesc" width="300"
							editor="{type:'validatebox',options:{required:true}}">角色说明</th>
						<!--分配权限  也就是给不同角色指定不同的用户 然后不同的用户可以执行相应的操作  -->
					</tr>
				</thead>
			</table>
			<!-- 数据表格  END-->

			<!-- 右击Menu START -->
			<div id="mm" class="easyui-menu" data-options="onClick:menuHandler"
				style="width: 120px;">
				<div data-options="name:'editRole',iconCls:'role-manage'">分配权限</div>
			</div>
			<!-- 右击Menu END  -->


			<!-- toolbar START -->
			<div id="toolbar">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-add" plain="true"
					onclick="javascript:$('#dg').edatagrid('addRow')">添加</a> <a
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
			<!-- toolbar END -->

		</div>

		<!-- 弹出的dialog 用来显卡详细信息START -->
		<div id="roleManage_sysFun_Dialog"
			style="width: 400px; height: 400px; text-align: center; background-image: url('/OA/Images/bg.gif');"
			class="easyui-dialog"
			data-options="
			title:'分配权限',
			closed:true,
			modal:true,
			buttons: '#dlg-buttons'
				">
			<div id="p" class="easyui-panel" title=""
				style="padding: 10px; height: 450px; background: #fafafa;"
				data-options="fit:true,border:false">
				<ul id="roleManage_sysFun_Tree"></ul>
			</div>

		</div>
		<div id="dlg-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				style="width: 80px;" onclick="authorize()">授权</a>
		</div>
		<!-- 弹出的dialog 用来显卡详细信息ENDs -->
	</center>
	<script type="text/javascript">
		//加载所有
		$(function() {
			$('#dg').edatagrid({
				url : "${pageContext.request.contextPath}/role/showPage",

				saveUrl : "${pageContext.request.contextPath}/role/add",

				updateUrl : "${pageContext.request.contextPath}/role/update",

				destroyUrl : "${pageContext.request.contextPath}/role/remove",

				onRowContextMenu : onRowContextMenu

			});
			//设置隐藏列
			$('#dg').edatagrid('hideColumn', 'roleId');

		});

		//在一行上右击显示相关menu
		var roleId;
		function onRowContextMenu(e, rowIndex, rowData) {
			e.preventDefault();
			roleId = rowData.roleId;
			/* console.info("e.pageX=" + e.pageX);
			console.info("e.pageY=" + e.pageY);
			console.info("rowIndex=" + rowIndex);
			console.info("rowData.roleName=" + rowData.roleName+" rowData.roleId="+rowData.roleId); */
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}

		//菜单右击
		function menuHandler(item) {
			if (item.name == "editRole") {
				$('#roleManage_sysFun_Dialog').dialog("open");
				$('#roleManage_sysFun_Tree')
						.tree(
								{
									url : '${pageContext.request.contextPath}/role/sysfunAction_getAllTreeNodeByRole?roleId='
											+ roleId,
									parentField : 'pid',
									animate : true,
									checkbox : true
								});
			}
		}

		//点击授权
		function authorize() {
			var treeObj = $('#roleManage_sysFun_Tree');
			var dialog = $('#roleManage_sysFun_Dialog');
			var nodes = treeObj.tree('getChecked');
			var nodesLen = nodes.length;
			var params = new Array(nodesLen);
			for (var i = 0; i < nodesLen; i++) {
				var nodeId = nodes[i].id;
				console.info(nodeId);
				params[i] = nodeId;
				/* var nodesParent = treeObj.tree(
						'getParent', nodes[i].target);
				console.info(nodesParent.id); */
			}
			loadAjaxAuthorizeRole(dialog,
					"${pageContext.request.contextPath}/role/sysfunAction_authorizeRole?params="
							+ params);
		}

		//点击授权 用到的
		function loadAjaxAuthorizeRole(select, url_theme) {

			$.ajax({
				url : url_theme,
				dataType : "json",
				type : "post",
				success : function(data) {
					var info = '信息';
					if (data == 'success') {
						msg(info, '授权成功');
						select.dialog('close');
					} else {
						msg(info, '授权失败');
					}
				},
				error : function() {
					msg('错误', '加载信息出错');
				}
			});

		}

		function msg(title, msg) {
			$.messager.show({
				title : title,
				msg : msg,
				timeout : 2000,
				style : {
					right : '',
					top : document.body.scrollTop
							+ document.documentElement.scrollTop,
					bottom : ''
				},
				showType : 'slide'
			});
		}
	</script>
</body>
</html>