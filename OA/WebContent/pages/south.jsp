<%@page import="java.util.Map"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%	String path = request.getContextPath();%>
<div style="display: inline-block;">版权所有<a href="#">www.jboa.com</a></div>
<div style="float: right; padding: 0px 10px;">
	在线人数:<span style="color: red;font-weight: bold;">
	<%
		Object user = session.getAttribute("user");
		if(user!=null){
			Map<String, Object> m = (Map) application.getAttribute("online");
			if(m != null){
				out.print(m.size());			
			}
		}
	%></span>
	<input type="image" src="<%=path%>/jquery-easyui-1.3.3/themes/icons/user.png" />
</div>
