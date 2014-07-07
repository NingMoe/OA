<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>回收站</title>
<%-- <link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/util.js"></script> --%>
</head>

<jsp:include page="docInfoContainer.jsp"></jsp:include>

<script type="text/javascript">
	
	docTreeURL='/OA/recycle/showRecycleDir';
	tFileURL = '/OA/recycle/showRecycle';
	title1 = '回收站';
	titleIcon = 'recycle-bin';
	
	//工具条数组
	toolBar = [ '-', {
		text : '清空回收站',
		iconCls : 'cai-icon-recycle-clean',
		handler : function() {
			clean();
		}
	},{
		text : '删除',
		iconCls : 'cai-icon-remove',
		handler : function() {
			remove();
		}
	},{
		text : '还原',
		iconCls : 'cai-icon-revert',
		handler : function() {
			revert();
		}
	},{
		text : '全部还原',
		iconCls : 'cai-icon-revert',
		handler : function() {
			revertAll();
		}
	}, {
		text : ' 刷新',
		iconCls : 'cai-icon-reload',
		handler : function() {
			location.reload();
		}
	}, '-' ];
	
	function revert(){
		var node = selectedRow();
		var pNode=node;
		while(1==1){
			var flag = $('#tFile').treegrid('getParent',pNode.id);
			if(flag)pNode = flag;
			else break;
		}
		if(node){
			
			$.get('<%=path%>/recycle/revert',{id:pNode.id},function(data){
				if(data.success){
					location.reload();
				}else{
					$.messager.show({
						title:'提示',
						msg : data.msg
					});
				}
			});	
		}
	}
	
	function revertAll(){
		if($('#tFile').treegrid('getRoot')){
			$.get('<%=path%>/recycle/revertAll',function(data){
				if(data.success){
					location.reload();
				}else{
					$.messager.show({
						title:'提示',
						msg : data.msg
					});
				}
			});	
		}else{
			$.messager.show({
				title:'提示',
				msg : '回收站内没有可还原的数据!'
			});
		}
	}
	
	function clean(){
		if($('#tFile').treegrid('getRoot')){
			<%-- $.get('<%=path%>/recycle/recycle/rtAll',function(data){
				if(data.success){
					location.reload();
				}else{
					$.messager.show({
						title:'提示',
						msg : data.msg
					});
				}
			});	 --%>
		}else{
			$.messager.show({
				title:'提示',
				msg : '回收站已经是空的!'
			});
		}
	}
	
	$(function(){
		$('#tabs').tabs('close', 0);
	});
</script>
</html>