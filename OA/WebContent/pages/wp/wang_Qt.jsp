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
<script type="text/javascript">
    $(function(){
    	$('#dd').datebox({
    		onSelect: function(date){
    		 
    		}
    	});

    	$('#tt').datebox({
    		onSelect: function(date){
    		 
    		}
    	});
    });
</script>
 <!-- <script type="text/javascript">
 
$(function(){
	$('#grid').datagrid({
		title:'',
		url:'',
		striped:true,
		rownumbers:true,
		singleSelect:true,
		idField:'',
		sortName:'createdatetime',
		sortOrder:'desc',
		pageSize:10,
		pageList:[10,20,30,40,50,60,70,80,90,100],
		frozenColumns:[[{
			width:'100',
			title:'发信人',
			field:'',
			sortable:true
		},{
			width:'100',
			title:'消息标题',
			field:'',
			sortable:true
		},{
			width:'150',
			title:'消息内容',
			field:'',
			sortable:true
		},{
			width:'150',
			title:'创建时间',
			field:'',
			sortable:true
		},{
			width:'150',
			title:'修改时间',
			field:'',
			sortable:true
		},{
			title : '操作',
			field : '',
			width : '90',
			formatter : function(value, row) {
				var str = '';
				
					str += sy.formatString('<img class="icon-note" title="查看" onclick=" "/>', row.id);
				
				
					str += sy.formatString('<img class="iconImg ext-icon-note_edit" title="编辑" onclick=" "/>', row.id);
				
				
					str += sy.formatString('<img class="iconImg ext-icon-user" title="用户角色" onclick="  ");"/>', row.id);
				
				
					str += sy.formatString('<img class="iconImg ext-icon-group" title="用户机构" onclick=" " );"/>', row.id);
										
				
					str += sy.formatString('<img class="iconImg ext-icon-note_delete" title="删除" onclick=" "/>', row.id);
				
				return str;

		}}]]
	});
});
</script>  -->
<script type="text/javascript">
    $(function(){
//datagrid初始化  

  $('#list_data').datagrid({   
 
      collapsible:false,//是否可折叠的  

      fitColumns:true,
      striped: true,  
 
      url:' ',  

      //sortName: 'code',  

      //sortOrder: 'desc',  
 
      singleSelect:false,//是否单选  

      pagination:true,//分页控件  

      rownumbers:true,//行号  
     
      frozenColumns:[[  

                      {field:'ck',checkbox:true}  

                  ]]
  });  

  //设置分页控件  

  var p = $('#list_data').datagrid('getPager');  

  $(p).pagination({  

      pageSize: 10,//每页显示的记录条数，默认为10  

      pageList: [10,20,30,40,50,60,70,80,90],//可以设置每页记录条数的列表  

      beforePageText: '第',//页数文本框前显示的汉字  

      afterPageText: '页    共 {pages} 页',  

      displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  

      /*onBeforeRefresh:function(){ 

          $(this).pagination('loading'); 

          alert('before refresh'); 

          $(this).pagination('loaded'); 

      }*/ 

  }); 
    });
</script>
</head>
 

<body class="easyui-layout" data-options="fit:true,border:false">
 
	<div id="toolbar"  style="background-color: #ccc;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td>消息标题</td>
								<td><input name=" " style="width: 80px;" /></td>
								<td>发消息用户</td>
								<td><input name=" " style="width: 80px;" /></td>
								<td>性别</td>
								<td><select  name=" " class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value="">请选择</option>
										<option value="1">男</option>
										<option value="0">女</option></select></td>
								<td>选择时间</td>
								<td><input name=" " id="dd"   style="width: 120px;" />-<input name=" " id="tt"  style="width: 120px;" /></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-ss',plain:true" onclick="grid.datagrid('load',sy.serializeObject($('#searchForm')));">搜索</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-aa',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});">重置内容</a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
			    <td>
			        <table>
			            <tr>
							
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit_add',plain:true" onclick="addFun();">发送信息</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true" onclick="">导入信息</a></td>
							<td><div class="datagrid-btn-separator"></div></td>
							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="">导出信息</a></td>
						</tr>
			          </table>
			     </td>
			</tr>
			</table>
   <div data-options="region:'center',fit:true,border:false" style="height: 370px;">

<table id="list_data" cellspacing="0" cellpadding="0" fit="true" >  
 
    <thead fit="true">  

        <tr>  

            <th field="fldAppDept" width="100">信息发布人</th>  

            <th field="fldAppNode" width="100">信息标题</th>  

            <th field="fldAppName" width="100">信息内容</th>  

            <th field="fldAppMgr" width="100">创建时间</th>  

            <th field="fldAppNote" width="100">注释</th>  

            <th field="fldAppType" width="100">类型</th>  

            <th field="fldTelphone" width="100">电话</th>  

            <th field="fldAppImg" width="100">职务</th>  

            <th field="fldAppMonitor" width="100">操作</th>  

            <th field="fldAppLevel" width="100"></th>  

        </tr>  

    </thead>  
 
</table> 
</div> 
</body>
</html>
 