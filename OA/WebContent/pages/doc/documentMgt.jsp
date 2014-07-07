<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文档管理</title>
<style type="text/css">
a {
	color: #0072BC;
	text-decoration: none;
}

a:hover {
	color: #004270;
	text-decoration: underline;
}
</style>
</head>
<!-- <script type="text/javascript" src="/OA/easyuiInc.jsp"></script> -->

<%-- <link rel="stylesheet" type="text/css"
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
<script type="text/javascript"
	src="<%=path%>/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
<script type="text/javascript" src="<%=path%>/javascript/util.js"></script>
<script type="text/javascript"
	src="<%=path%>/javascript/validate.extended.js"></script> --%>
	
<jsp:include page="docInfoContainer.jsp"></jsp:include>

<script type="text/javascript"
	src="<%=path%>/uploadify/jquery.uploadify.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/uploadify/uploadify.css">

<script type="text/javascript">

/**
 * 响应按键事件:回车提交,ESC退出
 */
var onPressKeyHandler = function(formId,submitId,closeId){
	$('#'+formId).unbind('keypress');
	onPressKey($('#'+formId),$('#'+submitId),$('#'+closeId));
};

/**
 * 弹出消息框
 */
var message = function(msg){
	$.messager.show({
		title: '消息',
		iconCls:'message',
		msg : msg
	});
};

/**
 * 获取父目录
 */
var getSelParentDir=function(){
	var row = $('#tFile').treegrid('getSelected');
	if(row){
		if(row["filetypeinfo.fileTypeName"]=="文件夹"){
			return row;
		}else{
			var pRow = $('#tFile').treegrid('getParent',row.id);
			return pRow;
		}
	}
};

/**
 * 获得父id
 */
var getPid = function(){
	var row=getSelParentDir();
	return row?row.id:null;
};

/**
 * 获得父路径
 */
var getParentPath = function(){
	var row=getSelParentDir();
	return row?row.folderPath:"/";
};

/**
 * 通过选中节点,获取'兄弟'文件名拼接字符串
 * 为防止文件重名发生
 * @param flag true:拼接文件夹名称,false:拼接文件名称 
 */
var getSibilingsStr=function(flag){
	var siblings = "";
	var arr;
	if(getPid()){
		arr = $('#tFile').treegrid('getChildren',getPid());
	}else{
		arr = $('#tFile').treegrid('getRoots');
	}
	for(i in arr){
		var n = arr[i];
		var res = n["filetypeinfo.fileTypeName"]=="文件夹";
		if(flag && res){
			siblings += n.name+"-";
		}else if(!flag && !res){
			siblings += n.name+n.suffix+"-";
		}
	}
	return siblings;
};

/** 为刷新的匿名方法,重新赋值  */
refreshBoth=function(){
	$('#docTree').tree('reload');
	goSearch();
};
	//工具条数组
	toolBar=['-',{
		text : '新建文件夹',
		iconCls : 'folder',
		handler : function(){newFolder();}
	},{
		text : '写备注',
		iconCls : 'icon-edit',
		handler : function(){edit();}
	},'-',{
		text : '保存', 
		iconCls : 'icon-save',
		handler : function(){save();}
	},{
		text : '撤销',
		iconCls : 'icon-undo', 
		handler : function(){cancel();}
	},'-',{
		text : '删除',
		iconCls : 'icon-remove-recycle',
		handler : function(){putRecycle();}
	},{
		text : '彻底删除',
		iconCls : 'cai-icon-remove',
		handler : function(){remove();}
	},'-',{
		text : '上传',
		iconCls : 'icon-upload', 
		handler : function(){upload();}
	},{
		text : '下载', 
		iconCls : 'icon-download',
		handler : function(){download();}
	},'-',{
		text : '刷新',
		iconCls : 'icon-reload ',
		handler : function(){
			goSearch();
			$('#docTree').tree('reload');
		}
	},'-'];

	function putRecycle(){
		var node = selectedRow();
		if(node){
			$.messager.confirm('确认删除', '确定要把\"'+node.name+'\"放入回收站吗?', function(r){
				if (r){
					$.get('<%=path%>/doc/putRecycle',{id:node.id},function(data){
						if(data.success){
							location.reload();
						}else{
							$.messager.show({
								title:'提示',
								iconCls:'icon-message',
								msg : data.msg,
								closable:false
							});
						}
					});
				}
			});
		}
	}
	
	function newFolder(){
		$('#dgMkdir').show().dialog({
			title : '新建文件夹',
			iconCls : 'folder',
			buttons : [{
				text:'创  建'	,
				id:'btn_mk_create',
				handler:function(){
					$('#fMkdir').form('submit',{
						url: '<%=path%>/doc/mkdir',
						success: function(){
							refreshBoth();
						}
					});
				}
			},{
				text:'关  闭',
				id:'btn_mk_close',
				handler:function(){
					$('#dgMkdir').dialog('close');
				}
			}]
		});
		
		$('#dgMkdir').dialog('open');
		$('#fMkdir').form('clear');
		$('#parentPath').val(getParentPath());
		$('#hiPid').val(getPid());
		
		$("#folderName").validatebox({
			validType:['FILENAME','FILENAME_EQUAL[\''+getSibilingsStr(true)+'\']']
		});
		
		onPressKeyHandler('fMkdir','btn_mk_create','btn_mk_close');
	}
	
	var editingId;
	function edit(){
		if (editingId != undefined){
			$('#tFile').treegrid('select', editingId);
			return;
		}
		var row = $('#tFile').treegrid('getSelected');
		if (row){
			editingId = row.id;
			$('#tFile').treegrid('beginEdit', editingId);
		}
	}
	
	function save(){
		if (editingId != undefined){
			var t = $('#tFile');
			var remark = t.treegrid('getEditor',{id:editingId,field:'remark'}).target.val();
			var fileId = editingId;
			
			t.treegrid('endEdit', editingId);
			editingId = undefined;
			
			$.post('<%=path%>/doc/update', {
				'fileinfo.fileId' : fileId,
				'fileinfo.remark' : remark
			});
		}
	}
	function cancel() {
		if (editingId != undefined) {
			$('#tFile').treegrid('cancelEdit', editingId);
			editingId = undefined;
		}
	}

	function upload() {
		var row = $('#tFile').treegrid('getSelected');
		var posFileId=0;//默认存放文件至文档根目录
		if(row){
			$("#path").css('color','green').text(row.folderPath);
			if(row["filetypeinfo.fileTypeName"]=="文件夹"){
				posFileId = row.id;//证明此节点是一个父节点(也就是文件夹)
			}else{
				var p = $('#tFile').treegrid('getParent',row.id);
				if(p){
					posFileId = p.id;
					$("#path").css('color','green').text(p.folderPath);
				} 
				else{
					posFileId=0;
					$("#path").css('color','orange').text("(没有为文档指定具体位置)");
				}
			}
			
		}else {$("#path").css('color','orange').text("(没有为文档指定具体位置)");}

		$("#file_upload")
				.uploadify(
						{
							'swf' : '<%=path%>/uploadify/uploadify.swf',
							'uploader' : '<%=path%>/doc/upload.action?jsessionid=${pageContext.session.id}', //请求到Action的地址
							'method': 'GET', 
							'fileObjName' : 'file',
							'queueID' : 'fileQueue',
							'auto' : false,
							'multi' : true,
							'uploadLimit' : 6,//每次最大上传文件数
							'buttonText' : '选 择 文 件',
							'progressData' : 'speed',//speed和percentage两种
							'fileTypeExts' : '*.jpg;*.gif;*.jpeg;*.png;*.bmp;*.txt;*.pdf;*.doc;*.ppt;*.xls;*.docx;*.pptx;*.xlsx;*.vsd;*.rar',//允许的格式
							'fileSizeLimit' : 50*1024*1024,
							'onComplete' : function(event, queueID, fileObj,
									response, data) {
							},
							'onUploadStart' : function(file) {
					            $("#file_upload").uploadify("settings", "formData", {'posFileId':posFileId,'fileTypeSuffix':file.type});
					        },
					        'onSelect' : function(file) {
					        	/* 同级文件名数组 */
					    		var fNameArr = getSibilingsStr(false).split('-');
					        	for(i in fNameArr){
					        		var n = fNameArr[i];
					        		if(n == file.name){
					        			var t = '警告';
					        			var msg = '文件\"'+n+'\"已存在于该目录下,继续上传会将其覆盖,取消则忽略该文件,要上传吗?';
										$.messager.confirm(t,msg,function(r){
											if(!r) $("#file_upload").uploadify("cancel",file.id);
										});					        			
					        		}
					        	}
					        } ,
					        'onQueueComplete' : function(queueData) {
					            /* alert(queueData.uploadsSuccessful + ' files were successfully uploaded.'); */
					            /* $('#file_upload').uploadify('cancel', '*'); */
					            goSearch();
					            $('#docTree').tree('reload');
					        } 
						});

		$('#divUpload').dialog({
			title : ' 上传',
			iconCls : 'icon-upload',
			width : 400,
			closed : true,
			cache : false,
			collapsible : true,
			right:'',
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:'',
			showType:'slide',
			buttons: [{
				text:'清空队列',
				handler:function(){$('#file_upload').uploadify('cancel', '*');}
			},{
				text:'全部停止',
				handler:function(){$('#file_upload').uploadify('stop', '*');}
			},{
				text:'全部上传',
				id:'up_all',
				handler:function(){$('#file_upload').uploadify('upload', '*');}
			},{
				text:'关闭窗口',
				id:'up_close',
				handler:function(){$('#divUpload').dialog('close');}
			}]
		});
		$('#divUpload').dialog('open');
	}

	function download() {
		var row = selectedRow('警告','请选择一个文件!');
		if(!row){return;}
		if(row["filetypeinfo.fileTypeName"]!="文件夹"){
			<%-- $.ajax({
				url:'<%=path%>/doc/download',
				type: "POST",
				dataType: "json",
				data:'fileinfo.fileId='+row.id,
				success:function(data){
					if(data.msg)
					message(data.msg);
				},
				error:function(){
					message('程序运行出错!');
				}		
			}); --%>
			location.href="<%=path%>/doc/download?fileinfo.fileId="+row.id;
		}else{
			$.messager.show({
				title:'提示',
				iconCls:'icon-message',
				msg:'请选择一个文件!',
			});
		}
		
	}
</script>

<body>
	<div id="divUpload"
		style="background-image: url('<%=path%>/Images/tsai/uploadify_bg.png');padding:5px;">
		<div style="font-size: 14px; margin-bottom: 5px;margin-top: 5px;">
			目的地:<font id="path"></font>
		</div>
		<a href="#" title="最大支持6个文件的批量上传哟." class="easyui-tooltip" style="float:right"><img src="<%=path %>/jquery-easyui-1.3.3/themes/icons/tip.png"></a>
		<div>
			<input type="file" name="file_upload" id="file_upload"
				style="border: 1px" />
		</div>
		<div id="fileQueue"></div>
		<div id="dgMkdir"
			style="padding: 10px; display: none; font-size: 15px;background-image: url('<%=path%>/Images/tsai/uploadify_bg.png');height:225px;">
			<form id="fMkdir" method="post">
				<table align="center">
					<tr>
						<th align="right">上级目录:</th>
						<td><input id="parentPath" readonly="readonly" /><input
							type="hidden" id="hiPid" name="posFileId" /></td>
					</tr>
					<tr>
						<th align="right">文件夹名称:</th>
						<td><input id="folderName" class="easyui-validatebox"
							type="text" name="fileinfo.fileName" data-options="required:true">
						</td>
					</tr>
					<tr>
						<th align="right">备注:</th>
						<td><textarea id="taRemark" name="fileinfo.remark" cols="15"
								rows="3"></textarea></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>