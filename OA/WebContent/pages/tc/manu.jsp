<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/javascript/util.js"></script>
<script type="text/javascript">
	$(function() {
		var datetime;
		$("#date").datebox({
			onChange : function(newDate, oldDate) {
				datetime = newDate;
			}
		});

	});
	function openMsg(titleShwo, msgShow) {
		$.messager.show({
			title : titleShwo,
			msg : msgShow,
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
	var urlSub_sign_in = '${pageContext.request.contextPath}/manual/add_input';
	var urlSub_sign_out = "${pageContext.request.contextPath}/manual/add_output";
	var manuForm_add_input = function() {
		//$("#manu_form").attr("action", urlSub_sign_in);
		signDesc = $("#signDesc").val();
		console.info(signDesc);
		if (signDesc == undefined) {
			signDesc = "";
		}
		$.ajax({
			url : urlSub_sign_in,
			data : "manualsign.signDesc=" + signDesc,
			success : function(data) {
				var date=new Date();
				date_formatter=date.getFullYear()+"-"+date.getMonth()+"-"+date. getDate()+" "+date.getHours()+":" +date.getMinutes()+":"+date.getSeconds();
				if (data.r == true) {
					openMsg("签到提示：", "<span style='color:red;'>签到成功！！！</span><br />时间：" + date_formatter + "");
				} else {
					openMsg("签到提示：", "签到失败！请稍后才试......");
				}
			}
		});
	};

	var manuForm_add_output = function() {
		//$("#manu_form").attr("action", urlSub_sign_out);
		signDesc = $("#signDesc").val();
		console.info(signDesc);
		if (signDesc == undefined) {
			signDesc = "";
		}
		$.ajax({
			url : urlSub_sign_out,
			data : "manualsign.signDesc=" + signDesc,
			success : function(data) {
				var date=new Date();
				date_formatter=date.getFullYear()+"-"+date.getMonth()+"-"+date. getDate()+" "+date.getHours()+":" +date.getMinutes()+":"+date.getSeconds();
				if (data.r == true) {
					openMsg("签退提示：", "<span style='color:red;'>签退成功！！！</span><br />时间：" + date_formatter + "");
				} else {
					openMsg("签退提示：", "签退失败！请稍后才试......");
				}
			}
		});
	};
</script>
<style type="text/css">
#div1,#div2 {
	width: 680px;
	border: solid 1px #c3c3c3;
}

input {
	border: solid 0.5px #009999;
}

textarea {
	border: solid 1px #c3c3c3;
}

#div2 {
	display: block;
}
</style>
</head>
<body>
	<center>
		<h3>
			<b>员工签到、签退</b>
		</h3>
	</center>
	<center>
		<div id="div1">
			<button name="btn1" onclick="manuForm_add_input()">签到</button>
			<button name="btn2" onclick="manuForm_add_output()">签退</button>
			<br>

			<div>
				<div>签卡备注:</div>
				<textarea rows="5" cols="50" id="signDesc"></textarea>
			</div>
		</div>
	</center>
</body>
</html>