<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>Office Automation</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Author" content="">
<meta name="Keywords" content="">
<meta name="Description" content="">

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<!-- 导入必要的文件 -->
<jsp:include page="inc.jsp"></jsp:include>
<link rel="shortcut icon" href="<%=path%>/Images/icons/rainbow.png" />
<script type="text/javascript">
	function collapseAll() {
		$('#tt').tree('collapseAll');
	}
	function expandAll() {
		$('#tt').tree('expandAll');
	}
	$(function() {
		// 实例化树菜单
		$("#tt").tree({
			url : '<%=path%>/sysfun/getAllTreeNode',
			parentField : 'pid',
			onLoadSuccess : function(node) {
				$.messager.progress('close');
			},
			animate : true,
			lines : true,
			onClick : function(node) {
				if (node.attributes) {
					if (node.attributes.url) {
						openTab(node.text, node.attributes.url);
					}
				}
			}

		});

		// 新增Tab
		function openTab(text, url) {

			if ($("#tabs").tabs('exists', text)) {
				$("#tabs").tabs('select', text);
			} else {
				var content = "<iframe frameborder='0' scrolling='auto' style='width:100%;height:100%' src="
						+ url + "></iframe>";
				$("#tabs").tabs('add', {
					title : text,
					closable : true,
					content : content
				});
			}
		}
	});
</script>
</head>
<body class="easyui-layout" id="mainLayout">
	<!-- 北部区域 -->
	<div data-options="region:'north',border:false"
		style="height: 90px; padding: 10px">

		<jsp:include page="north.jsp"></jsp:include>

	</div>

	<!-- 西部区域 -->
	<div data-options="region:'west',split:false,title:'导航菜单'"
		style="width: 180px;">
		<div id="p" class="easyui-panel" title="" fit="true"
			data-options="border:false">
			<a href="#" class="easyui-linkbutton" onclick="expandAll()"
				style="margin: 10px 2px 5px 10px"><img alt="全部打开" src=""></a>
			<a href="#" class="easyui-linkbutton" onclick="collapseAll()"
				style="margin: 10px 2px 5px 10px"><img alt="全部关闭"></a> <br>

			<ul style="margin-left: 10px;" id="tt" class="easyui-tree"></ul>


		</div>

	</div>

	<!-- 南部区域 -->
	<div region="south" style="height: 25px;" align="center">
		<!-- 版权所有<a href="#">www.jboa.com</a>
		<div style="float: right;padding:0px 10px;">在线人数:<input type="image" src="/OA/jquery-easyui-1.3.3/themes/icons/user.png"/></div> -->
		<jsp:include page="south.jsp"></jsp:include>
	</div>

	<!-- 中部区域 -->
	<div data-options="region:'center',title:''">
		<div class="easyui-tabs" fit="true" border="false" id="tabs">
			<div title="首页"
				style="background-image: url('/OA/Images/office.jpg')"></div>
		</div>
	</div>

</body>
</html>