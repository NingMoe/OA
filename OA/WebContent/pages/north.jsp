<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%	String path = request.getContextPath();%>
<link id="easyuiTheme" rel="stylesheet" href="<%=path %>/css/icon.css"
	type="text/css"></link>
<script type="text/javascript" src="<%=path %>/javascript/validate.extended.js"></script>
<script type="text/javascript" src="<%=path %>/javascript/util.js"></script>
<script type="text/javascript" charset="utf-8">
	$(function() {//如果用户已登录,刷新页面则不显示登录框
		if ('${sessionScope.user.userId}') {
			$('#dgUserLogin').dialog('close');
		} else {
			$('#mainLayout').layout('collapse', 'west');
			$('#loginFrom').form('clear');
			$('#dgUserLogin').show().dialog({
				title : '&nbsp;用户登录',
				iconCls : 'role-manage',
				modal : true,
				closable : false,
				buttons : [ {
					text : '登录',
					id : 'btnLogin',
					handler : function() {
						submitForm(true);
					}
				} ]
			});
			$('#dgUserLogin').dialog('open');
			$('#dgUserLogin form input').first().focus();
			onPressKey($('#dgUserLogin form'), $('#btnLogin'));
		}
		console.info('init');
	});
	function logoutFun(b) {
		if (b) {
			$.messager.confirm('注意', '确定要退出OA-办公自动化管理系统吗?', function(r) {
				if (r) {
					$.get('<%=path%>/user/logout',
							function() {
								location.reload();
							});
				}
			});

		} else {
			$('#loginFrom').form('clear');
			$('#inUserId').attr('readOnly', false);
			$('#dgUserLogin').show().dialog({
				title : '重新登录',
				iconCls : 'cai-icon-revert',
				modal : true,
				closable : true,
				buttons : [ {
					text : '登录',
					id : 'btnReLogin',
					handler : function() {
						submitForm(true);
					}
				}, {
					text : '取消',
					id : 'btnLoginCancel',
					handler : function() {
						$('#dgUserLogin').dialog('close');
					}
				} ]
			});
			$('#dgUserLogin').dialog('open');
			$('#dgUserLogin form input').first().focus();
			onPressKey($('#dgUserLogin form'), $('#btnReLogin'));
		}
	}
	/**
	 * 图片加载失败
	 */
	function nofind(event) {
		var img = event.target;
		img.src = '/OA/Images/not found.jpg';
	}
	/**
	 *查看当前登录用户
	 */
	function userInfoFun() {
		$('#user_vie_Dialog').dialog('open');
	}
	/**
	 * 用户锁定	
	 */
	function userLocked() {
		$.get('<%=path%>/user/logout');
		$('#inUserId').val('${sessionScope.user.userId}').attr('readOnly',
				'readOnly');
		$('#rePassWord').val('');
		$('#dgUserLogin').show().dialog({
			title : '&nbsp;用户锁定',
			iconCls : 'icon-userLocked',
			modal : true,
			closable : false,
			buttons : [ {
				text : '解锁',
				id : 'btnUnlocked',
				handler : function() {
					submitForm();
				}
			} ]
		});
		$('#dgUserLogin').dialog('open');
		$('#dgUserLogin form input').get(1).focus();
		$('#dgUserLogin form').unbind('keypress');
		onPressKey($('#dgUserLogin form'), $('#btnUnlocked'));
	}
	/**
	 * 异步提交登录信息
	 * @param 是否刷新页面
	 */
	function submitForm(r) {
		$('#loginFrom').form('submit', {
			url : '<%=path%>/user/ajaxLogin',
			method : 'post',
			success : function(data) {
				if (data == 'true') {
					$('#dgUserLogin').dialog('close');
					if (r) {
						location.reload();
					}
				} else {
					$.messager.show({
						title : '提示',
						msg : '登录失败!',
						closable : false
					});

				}
			}
		});
	}

	/**
	 * 修改密码
	 */
	function modifyPwd() {
		$('#pwd0').val('');
		$('#pwd1').val('');
		$('#pwd2').val('');
		$('#dgOperPwd')
				.show()
				.dialog(
						{
							title : '&nbsp;修改密码',
							iconCls : "sys-manage",
							modal : true,
							buttons : [
									{
										text : '修改',
										id : 'pwdSubmit',
										handler : function() {
											$('#fOperPwd')
													.form(
															'submit',
															{
																url : '<%=path%>/user/modifyPwd',
																type : "POST",
																data : $(
																		'#fOperPwd')
																		.serialize(),
																dataType : 'json',
																async : false,//是否异步执行
																success : function(
																		msg) {
																	if (msg == "\"密码修改成功!\"") {
																		$(
																				'#dgOperPwd')
																				.dialog(
																						'close');
																	}
																	$.messager
																			.show({
																				iconCls:'icon-message',
																				title : '消息',
																				msg : msg,
																				closable : false
																			});
																},
																error : function() {
																	$.messager
																			.show({
																				iconCls:'icon-warning',
																				title : '注意',
																				msg : '系统故障,请联系技术员解决!'
																			});
																}
															});
										}
									}, {
										text : '取消',
										id : 'btnRePwdCancel',
										handler : function() {
											$('#dgOperPwd').dialog('close');
										}
									} ]
						});
		$('#dgOperPwd').dialog('open');
		$('#dgOperPwd form input').get(1).focus();
		onPressKey($('#dgOperPwd form'), $('#pwdSubmit'), $('#btnRePwdCancel'));
	}
</script>
<%
	Object user = session.getAttribute("user");
	if(user!=null){
%>
<div style="clear:left;float: right;color: #998877;">您好：${sessionScope.user.userName} ，欢迎使用OA系统！</div>
<% }%>

<div id="logo">
	<img alt="OA System" src="<%=path %>/Images/logo2.png">
</div>

<%-- <div id="sessionInfoDiv" style="position: absolute; right: 5px; top: 10px;">
	<c:if test="${sessionInfo.userId != null}">[<strong>${sessionInfo.loginName}</strong>]，欢迎你！您使用[<strong>${sessionInfo.ip}</strong>]IP登录！</c:if>
</div> --%>
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_pfMenu',iconCls:'icon-rainbow'">更换皮肤</a>
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
	<a href="javascript:void(0);" class="easyui-menubutton"
		data-options="menu:'#layout_north_zxMenu',iconCls:'icon-back'">注销</a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="changeTheme('default');" iconCls="thems-icon-default">default</div>
	<div onclick="changeTheme('gray');" iconCls="thems-icon-gray">gray</div>
	<div onclick="changeTheme('metro');" iconCls="thems-icon-metro">metro</div>
	<div onclick="changeTheme('black');" iconCls="thems-icon-black">black</div>
	<div onclick="changeTheme('bootstrap');" iconCls="thems-icon-bootstrap">bootstrap</div>
	<div class="menu-sep"></div>
	<div onclick="changeTheme('metro-blue');"
		iconCls="thems-icon-metro-blue">metro-blue</div>
	<div onclick="changeTheme('metro-gray');"
		iconCls="thems-icon-metro-gray">metro-gray</div>
	<div onclick="changeTheme('metro-green');"
		iconCls="thems-icon-metro-green">metro-green</div>
	<div onclick="changeTheme('metro-orange');"
		iconCls="thems-icon-metro-orange">metro-orange</div>
	<div onclick="changeTheme('metro-red');" iconCls="thems-icon-metro-red">metro-red</div>
	<div class="menu-sep"></div>
	<div onclick="changeTheme('ui-cupertino');"
		iconCls="thems-icon-ui-cupertino">ui-cupertino</div>
	<div onclick="changeTheme('ui-dark-hive');"
		iconCls="thems-icon-ui-dark-hive">ui-dark-hive</div>
	<div onclick="changeTheme('ui-pepper-grinder');"
		iconCls="thems-icon-ui-pepper-grinder">ui-pepper-grinder</div>
	<div onclick="changeTheme('ui-sunny');" iconCls="thems-icon-ui-sunny">ui-sunny</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div onclick="userInfoFun();" iconCls="role-manage">我的信息</div>
	<div class="menu-sep"></div>
	<div onclick="modifyPwd()" iconCls="sys-manage">修改密码</div>
</div>
<!-- 弹窗BEGIN -->
<div id="user_vie_Dialog" class="easyui-dialog" title="用户信息"
	data-options="iconCls:'role-manage',closed:true,closable:true"
	style="width: 580px; height: 320px; padding: 10px; background-image: url('/OA/Images/bg.gif')">
	<jsp:include page="hr/employeeView.jsp"></jsp:include>
</div>
<div id="dgUserLogin"
	style="padding: 10px; display: none; font-size: 15px;">
	<form id="loginFrom" action="user/ajaxLogin" method="post">
		<table align="center">
			<tr>
				<th align="right">登录名:</th>
				<td><input id="inUserId" class="easyui-validatebox" type="text"
					name="userinfo.userId" data-options="required:true"
					value="${sessionScope.user.userId}"></td>
			</tr>
			<tr>
				<th align="right">密码:</th>
				<td><input id="rePassWord" class="easyui-validatebox"
					type="password" name="userinfo.passWord"
					data-options="required:true"></input></td>
			</tr>
		</table>
	</form>
</div>
<div id="dgOperPwd"
	style="padding: 10px; display: none; font-size: 15px;">
	<form id="fOperPwd">
		<table align="center">
			<tr>
				<th align="right">登录名:</th>
				<td><input class="easyui-validatebox" type="text"
					readonly="readonly" name="userinfo.userId"
					data-options="required:true" value="${sessionScope.user.userId}">
				</td>
			</tr>
			<tr>
				<th align="right">原密码:</th>
				<td><input id="pwd0" class="easyui-validatebox" type="password"
					data-options="required:true" name="userinfo.passWord" /></td>
			</tr>
			<tr>
				<th align="right">新密码:</th>
				<td><input id="pwd1" class="easyui-validatebox" type="password"
					data-options="required:true" validType="safepass" /></td>
			</tr>
			<tr>
				<th align="right">重复新密码:</th>
				<td><input id="pwd2" class="easyui-validatebox" type="password"
					data-options="required:true" name="newPwd"
					validType="equalTo['#pwd1']" /></td>
			</tr>
		</table>
	</form>
</div>
<!-- 弹窗END -->

<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div onclick="userLocked();" iconCls="icon-userLocked">锁定窗口</div>
	<div class="menu-sep"></div>
	<div onclick="logoutFun();" iconCls="cai-icon-revert">重新登录</div>
	<div class="menu-sep"></div>
	<div onclick="logoutFun(true);" iconCls="icon-cancel">退出系统</div>
</div>

