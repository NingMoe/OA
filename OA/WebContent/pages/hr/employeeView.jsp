<%@page import="com.oa.entity.Userinfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	Userinfo u = ((Userinfo) session.getAttribute("user"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<title>用户视图</title>
<style type="text/css">
body {
	margin: 5px;
	padding-top: 3px;
	padding-left: 3px;
}

#content {
	
}

#content div {
	background-image: url('/OA/Images/bg.gif');
	float: left;
	margin-top: 5px;
	margin-left: 5px;
}

#content #detail {
	width: 300px;
	padding-left: 20px;
}

#content #detail table {
	line-height: 30px;
	font: 15px;
	border: 1px;
}

#content #detail table tr th {
	text-align: right;
	color: #999;
}

#content #detail table tr td {
	text-align: left;
	border-bottom: 1px solid #999;
	width: 120px;
}
</style>
</head>
<body>
	<div id="content">
		<div id="icon">
			<img width="200" height="250" alt="图像未找到" onerror="nofind(event)"
				src="<%=path+"/" %><%=(u != null) ? ((u.getAvatarFile() != null) ?u
					.getAvatarFile().getFilePath().replace("\\", "/") :  "Images/no img.jpg") :"Images/no img.jpg"%>" />
				<%-- <%=path+"/" %><%=(u != null) ? ((u.getAvatarFile() != null) ?u
				.getAvatarFile().getFilePath().replace("\\", "/") :  "Images/no img.jpg") :"Images/no img.jpg"%> --%>
		</div>
		<div id="detail">
			<table>
				<tr>
					<th>用户号:</th>
					<td><font id="fUserId">${sessionScope.user.userId}</font>
					</td>
				</tr>
				<tr>
					<th>姓名:</th>
					<td><font id="fUserName">${sessionScope.user.userName}</font>
					</td>
				</tr>
				<tr>
					<th>部门:</th>
					<td><font id="fDepart">${sessionScope.departName}</font>
					</td>
				</tr>
				<tr>
					<th>性别:</th>
					<td><font id="fGender"><c:out
								value="${sessionScope.user.gender==1?'男':'女'}" /> </font>
					</td>
				</tr>
				<tr>
					<th>角色:</th>
					<td><c:if test="${not empty sessionScope.user.roleinfos}">
							<font id="fUserRole" color="green"> <c:forEach
									items="${sessionScope.user.roleinfos}" var="role"
									varStatus="status">${role.roleName}<c:if
										test="${ not status.last}">,</c:if>
								</c:forEach> </font>
						</c:if> <c:if test="${empty sessionScope.user.roleinfos}">
							<font id="fUserRole" color="red">未分配任何角色</font>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>用户状态:</th>
					<td><font id="fUserState">${sessionScope.user.userstate.userStateName}</font>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>