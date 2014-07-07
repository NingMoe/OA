<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.text {
	border: 1px #95B8E7 solid;
}

.hideBlock {
	display: none;
	padding-left: 20px;
}
</style>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">

<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bg.css">
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/util.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/validate.extended.js"></script>
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.etreegrid.js"></script>

<script type="text/javascript"
	src="<%=path%>/javascript/datagrid-filter.js"></script>

<script type="text/javascript">
	var toolBar = '';
	var docTreeURL = '/OA/doc/showTree';
	var tFileURL = '/OA/doc/showAll';
	var title1 = '文档信息';
	var title2 = '工具栏';
	var titleIcon = 'document-manage';

	/**
	 * 刷新
	 */
	var refreshBoth = function() {
		$('#docTree').tree('reload');
		$('#tFile').treegrid('reload');
	};

	var remove = function() {
		var node = selectedRow();
		if (node) {
			$.messager.confirm('注意', '彻底删除\"' + node.name + node.suffix
					+ '\",该操作将不可恢复,确定继续吗?', function(r) {
				if (r) {
					$.get('${pageContext.request.contextPath}/doc/remove', {
						'fileinfo.fileId' : node.id
					}, function(data) {
						if (!data.success) {
							$.messager.show({
								title : '提示',
								msg : data.msg
							});
						}
						refreshBoth();
					});
				}
			});
		}
	};

	function collapseAll() {
		$('#docTree').tree('collapseAll');
	}
	function expandAll() {
		$('#docTree').tree('expandAll');
	}

	$(function() {
		// 实例化树菜单
		$("#docTree").tree(
				{
					lines : true,
					url : docTreeURL,
					parentField : 'pid',
					animate : true,
					onClick : function(node) {
						/* if (node.attributes) {
							openTab(node.text, node.attributes.url);
						} */
					},
					onLoadSuccess : function(node, data) {
						var newData = [ {
							id : "1",
							text : "(--默认最大范围--)"
						} ];
						
						for (i in data) {
							var res = data[i];
							if (res.iconCls == "folder")
								newData.push(res);
						}
						var val = $('#cbxScope').combobox('getValue');
						$('#cbxScope').combobox('loadData', newData);
						try {
							$('#cbxScope').combobox('select',(val=="")?1:val);
						} catch (e) {
							$('#cbxScope').combobox('select',1);
						}
						
					}
				});

		// 实例化树表格
		/* filterBtnIconCls : 'icon-filter', */
		var tg = $('#tFile').treegrid({
			url : tFileURL,
			idField : 'id',
			treeField : 'name',
			parentField : 'pid',
			toolbar : toolBar,
			rownumbers : true,
			fit : true,
			fitColumns : true,
			singleSelect : true,
			frozenColumns : [ [ {
				title : '名称',
				field : 'name',
				width : 150
			} ] ],
			columns : [ [ {
				field : 'filetypeinfo.fileTypeName',
				title : '类型',
				width : 60
			}, {
				field : 'remark',
				title : '备注',
				editor : 'text',
				width : 80
			}, {
				field : 'userinfo.userName',
				title : '所有者',
				width : 40
			}, {
				field : 'createDate',
				title : '创建日期',
				width : 100
			} ] ],
			onLoadSuccess : function(row, data) {
				$('#tFile').treegrid('unselectAll');
				if (data == "") {
					$.messager.show({
						title : '提示',
						iconCls : 'icon-message',
						msg : title1 + '是空的!'
					});
				}
			}
		});
		
	    /* $('#tFile').etreegrid({
	    	url : tFileURL,
			idField : 'id',
			treeField : 'name',
			parentField : 'pid', 
            saveUrl: "/area/save",  
            deleteUrl: "/area/delete"
	    });
		$('#tFile').etreegrid('load'); */
	    
		/*tg.treegrid('enableFilter'); , [ {
			field : 'listprice',
			type : 'numberbox',
			options : {
				precision : 1
			},
			op : [ 'equal', 'notequal', 'less', 'greater' ]
		}, {
			field : 'unitcost',
			type : 'numberbox',
			options : {
				precision : 1
			},
			op : [ 'equal', 'notequal', 'less', 'greater' ]
		}, {
			field : 'status',
			type : 'combobox',
			options : {
				panelHeight : 'auto',
				data : [ {
					value : '',
					text : 'All'
				}, {
					value : 'P',
					text : 'P'
				}, {
					value : 'N',
					text : 'N'
				} ],
				onChange : function(value) {
					if (value == '') {
						dg.datagrid('removeFilterRule', 'status');
					} else {
						dg.datagrid('addFilterRule', {
							field : 'status',
							op : 'equal',
							value : value
						});
					}
					dg.datagrid('doFilter');
				}
			}
		} ] );*/

	});

	var selectedRow = function(title, msg) {
		var node = $('#tFile').treegrid('getSelected');
		if (!node) {
			$.messager.show({
				title : (title ? title : '提示'),
				msg : (msg ? msg : '没有记录被选中!'),
				iconCls : 'icon-message',
				closable : false
			});
		} else {
			return node;
		}
	};

	/* 点击展示搜索选项 */
	function checkShow(element) {
		var e = $(element);
		e.css('display') == 'none' ? e.css('display', 'block') : e.css(
				'display', 'none');
		if(element == '#divType'){
			$('#cbxType').combobox({
				url:'<%=path%>/doc/showAllType',
				valueField: 'fileTypeId',
				panelHight:'auto',
		        textField: 'fileTypeName',
		        editable:false,
		        onLoadSuccess:function(){
		        	$('#cbxType').combobox('select',1);
		        }
			});
		}else if(element == '#divOwner'){
			$('#cbxOwner').combobox({
				url:'<%=path%>/doc/fileOwners',
				panelHeight:150,
				valueField: 'userId',  
		        textField: 'userName',
		        editable:false,
		        onLoadSuccess:function(){
		        	var d = $('#cbxOwner').combobox('getData');
		        	if(d && d[0]){
		        		$('#cbxOwner').combobox('select',d[0].userId);
		        	} 
		        }
			});
		}
	}

	/* 点击控件可用 */
	function checkOne(i) {
		disabled_1();
		if (i == 1) {
			$('#monthAgo').numberspinner('enable');
		} else if (i == 2) {
			$('#dayAgo').numberspinner('enable');
		} else if (i == 3) {
			$('#dateBetween1').datebox('enable');
			$('#dateBetween2').datebox('enable');
		}

	}

	/* 禁用日期部分控件 */
	function disabled_1() {
		$('#monthAgo').numberspinner('disable');
		$('#dayAgo').numberspinner('disable');

		$('#dateBetween1').datebox('disable');
		$('#dateBetween2').datebox('disable');
	}

	/* 清理搜索表单 */
	function clear_sf() {
		$('#searchForm').form('clear');
		$('.hideBlock').css('display', 'none');
		disabled_1();
		$('#cbxScope').combobox('select', 1);
	}
	
	/* 搜索文件 */
	function goSearch(data){
		/* doSearch */
		data = data?data:$('#searchForm').serialize();
		$.messager.progress({interval:100});
		$.ajax({
			'url':'<%=path%>/doc/doSearch',
			'data':data,
			'type':'get',
			'success':function(json){
				$.messager.progress('close');
				if(!json || !json[0]){
					$.messager.show({
						title:'消息',
						iconCls:'icon-message',
						msg:'你的搜索结果为-0 条记录-,故页面不会刷新!',
						closable:true
					});
					return;
				}
				$('#tFile').treegrid('loadData',json);
			},
			'error':function(XMLHttpRequest, textStatus, errorThrown){
				$.messager.progress('close');
				console.info("textStatus:"+textStatus);
				console.info("errorThrown:"+errorThrown);
				$.messager.show({
					title:'出现故障',
					closable:true,
					iconCls:'icon-warning',
					msg:'搜索出现故障,请联系技术支持,QQ号:764077832!'
				});
			}
		});
		
	}
	
	/** 单条件搜索  */
	function smallSearch(value,name){
		if(value==""){
			$.messager.show({title:'提示',msg:'请输入相应内容,再进行搜索!'});
			return ;
		}
		goSearch(name+"="+value);
	}
</script>
</head>
<body class="easyui-layout" id="docMgtLayout">

	<div
		data-options="region:'east',split:false,title:title2,iconCls:titleIcon"
		style="width: 200px;">
		<div id="p" class="easyui-panel" title="" fit="true"
			data-options="border:false">

			<div class="easyui-tabs" fit="true" border="false" id="tabs">

				<div class="Tab1" title="搜索文件" iconCls="icon-search">
					<form action="#" id="searchForm">
						<div align="center"
							style="margin: 5px; text-align: left; line-height: 20px;">
							<%-- <img alt=""
								src="<%=path%>/jquery-easyui-1.3.3/themes/icons/folder_magnify.png"> --%>
							从<a href="#" title="访问远程服务器,获取到最新的数据,需要一定的网络带宽!"
								class="easyui-tooltip">远程主机</a>上搜索文件和文件夹.
							<hr color="#D4D0C8" size="3" />
							要搜索的文件或文件夹名称为:<br> <input type="text" name="c.fileName" class="easyui-searchbox" searcher="smallSearch"><br /> 备注信息:<br>
							<input type="text" name="c.remark" class="easyui-searchbox" searcher="smallSearch"><br /> 搜索范围:<br> <input
								id="cbxScope" class="easyui-combobox" name="c.scope"
								data-options="valueField: 'id',textField: 'text',panelHeight:'auto',editable:false"><br>
							<a href="#" class="easyui-linkbutton" iconCls="icon-search"
								style="margin: 10px 2px 5px 10px" onclick="goSearch()"><img alt="综合搜索"> </a> <a
								href="#" class="easyui-linkbutton"
								style="margin: 10px 2px 5px 10px" iconCls="icon-undo"
								onclick="clear_sf()"><img alt="重置"> </a>
							<hr color="#D4D0C8" size="3" />

							<!-- 搜索选项 -->
							<div id="advencedSearch" class="easyui-panel"
								data-options="title:'搜索选项',collapsible:true,iconCls:'document-search'">
								<input type="checkbox" id="chDate"
									onclick="checkShow('#divDate')">日期<br>

								<div class="hideBlock" id="divDate">
									<table cellspacing="0">
										<tr>
											<td><input type="radio" name="d" onclick="checkOne(1)">
											</td>
											<td>前</td>
											<td><input id="monthAgo" name="c.monAgo" class="easyui-numberspinner"
												data-options="min:1,max:100,width:60,disabled:true">个月</td>
										</tr>
										<tr>
											<td><input type="radio" name="d" onclick="checkOne(2)">
											</td>
											<td>前</td>
											<td><input id="dayAgo" name="c.dayAgo" class="easyui-numberspinner"
												data-options="min:1,max:100,width:60,disabled:true">天</td>
										</tr>
										<tr>
											<td><input type="radio" name="d" onclick="checkOne(3)">
											</td>
											<td>介于</td>
											<td><input id="dateBetween1" name="c.beginDate" class="easyui-datebox"
												data-options="width:100,disabled:true,editable:false" />
											</td>
										</tr>
										<tr>
											<td></td>
											<td>和</td>
											<td><input id="dateBetween2" name="c.endDate" class="easyui-datebox"
												data-options="width:100,disabled:true,editable:false" />
											</td>
										</tr>
									</table>
								</div>
								<input type="checkbox" id="chType"
									onclick="checkShow('#divType')">类型<br>
								<div class="hideBlock" id="divType" >
									<input id="cbxType" style="width: 120px;" name="c.typeId"/>
								</div>

								<input type="checkbox" id="chOwner"
									onclick="checkShow('#divOwner')">所有者<br>
								<div class="hideBlock" id="divOwner" style="margin-bottom:3px;">
									<input id="cbxOwner" style="width: 120px;" name="c.userId"/>
								</div>
							</div>
						</div>

					</form>
				</div>

				<div class="Tab2" title="目录">
					<!-- 树形列表 -->
					<a href="#" class="easyui-linkbutton" onclick="expandAll()"
						style="margin: 10px 2px 5px 10px"><img alt="全部打开" src="">
					</a> <a href="#" class="easyui-linkbutton" onclick="collapseAll()"
						style="margin: 10px 2px 5px 10px"><img alt="全部关闭"> </a> <br>
					<ul style="margin-left: 10px;" id="docTree" class="easyui-tree"></ul>
				</div>

			</div>

		</div>
	</div>

	<div data-options="region:'center',title:title1,iconCls:titleIcon"
		style="padding: 5px; background: #eee;">

		<div id="ptg" class="easyui-panel" fit="true" style="border: 0px;">
			<table id="tFile" style="height: 450px"></table>
		</div>

	</div>

</body>
</html>