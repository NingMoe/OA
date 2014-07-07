<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!-- easyui控件 -->
<link id="easyuiTheme" rel="stylesheet"
	href="../jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="ui-cupertino"/>/easyui.css"
	type="text/css"></link>
<link rel="stylesheet" href="../jquery-easyui-1.3.3/themes/icon.css"
	type="text/css"></link>
<script type="text/javascript"
	src="../jquery-easyui-1.3.3/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.3/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"
	charset="utf-8"></script>
<!-- cookie插件 -->
<script type="text/javascript" src="../javascript/jquery.cookie.js"
	charset="utf-8"></script>

<!-- 自己定义的样式和JS扩展 -->
<script type="text/javascript" src="../javascript/util.js "
	charset="utf-8"></script>

<%-- <link rel="stylesheet" type="text/css" id="easyuiTheme"
	href="jquery-easyui-1.3.3/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.3.3/themes/">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<!-- 公有脚本文件  -->
<script type="text/javascript" src="javascript/util.js" charset="utf-8"></script> 
<script type="text/javascript" src="javascript/jquery.cookie.js" charset="utf-8"></script>	 --%>