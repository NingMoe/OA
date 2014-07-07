<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
<script type="text/javascript" src="<%=path%>/javascript/util.js"></script>
<style type="text/css">
.depart_detail_Dialog {
	font: 12pts/18pts;
	font-weight: bolder;
}

#depart_modify_Form tr th {
	padding-top: 10px;
}

#depart_modify_Form tr td {
	padding-top: 10px;
}
</style>
</head>
<body>
	<center>
		<div class="" style="margin: 0px 0px">
			<div class="demo-tip icon-tip"></div>
			<h3 style="font-size: 20px; color: red;">部门日程管理</h3>
		</div>
		<hr color="#eaf2ff" />
		<div>
			<!-- 机构  START-->
			选择机构: <select id="jiguo" onchange="loadInfo()" style="width: 100px;">
				<option value="0">请选择</option>
			</select> &nbsp;&nbsp;
			<!-- 机构  END-->

			<!-- 部门  START-->
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;选择部门 :<select id="bumen"
				style="width: 100px;">
				<option value="0">请选择</option>
			</select>
			<!-- 部门 END-->

			&nbsp;&nbsp; &nbsp;&nbsp;
			<!-- 日期  START-->
			日程日期:&nbsp;<input id="datebox" class="easyui-datebox"
				data-options="onSelect:onSelect,validType:'date[]'"
				style="width: 100px;"></input>
			<!-- 日期  END-->

			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			<!-- 搜索  START-->
			员工姓名:&nbsp;<input class="easyui-searchbox"
				data-options="prompt:'请输入',searcher:doSearch" style="width: 100px"></input>
			<!-- 搜索  END-->

			<!-- 横线  START-->
			<hr color="#eaf2ff" />
			<!-- 横线  END-->


			<!-- 数据表格  START-->
			<table id="dg" title="部门管理" style="width: 800px; height: 365px"
				toolbar="#tb" pagination="true" idField="id" rownumbers="true"
				fitColumns="true" singleSelect="true"
				onRowContextMenu="onRowContextMenu">
				<thead>
					<tr>
						<th field="scheduleId" width="50"
							editor="{type:'validatebox',options:{validType:'email',required:true}}"
							style="display: none;">ID</th>
						<th field="title" width="50"
							editor="{type:'validatebox',options:{validType:'email',required:true}}">标题</th>

						<th field="address" width="50"
							editor="{type:'validatebox',options:{required:true}}">地址</th>
						<th field="beginTime" width="50"
							editor="{type:'validatebox',options:{required:true}}">开始时间</th>
						<th field="endTime" width="50"
							editor="{type:'validatebox',options:{required:true}}">结束时间</th>
						<th field="schContent" width="50"
							editor="{type:'validatebox',options:{required:true}}">内容</th>
						<th field="meetinginfo"
							data-options="formatter:managerMeetinginfoFormat" width="50"
							editor="{type:'validatebox',options:{required:true}}">会议类型</th>
						<th field="createTime" width="50"
							editor="{type:'validatebox',options:{required:true}}">创建时间</th>
					</tr>
				</thead>
			</table>
			<!-- 数据表格  END-->

			<!-- toolbal START -->
			<div id="tb" style="height: auto">
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-remove',plain:true" onclick="del()">删除</a>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					data-options="iconCls:'icon-edit',plain:true" onclick="modify()">修改</a>
			</div>
			<!-- toolbal END -->


			<!-- 弹出的dialog 用来显卡详细信息START -->
			<div id="depart_detail_Dialog"
				style="width: 480px; text-align: center;background-image:url('<%=path%>/Images/tsai/uploadify_bg.png');"
				class="easyui-dialog"
				data-options="title:'详细',closed:true,modal:true
				">
				<table
					style="background-image:url('<%=path%>/Images/tsai/uploadify_bg.png'); height: 390px">
					<tr>
						<td>开始时间：</td>
						<td id="detail_beginTime">2010-5-10 12:12:12</td>
						<td style="padding-left: 35px;">结束时间：</td>
						<td id="detail_endTime">2010-5-10 12:12:12</td>
					</tr>
					<tr>
						<td style="">会议内容：</td>
						<td colspan="3" style="">&nbsp;</td>
					</tr>
					<tr style="height: 60px">
						<td colspan="4" style="color: red; font-size: 18px"
							id="detail_Content">fdasfadsfsdafsdafsd</td>
					</tr>
					<tr>
						<td>会议地址：</td>
						<td id="detail_address">杭州杭州</td>
						<td style="padding-left: 35px;">会议类型：</td>
						<td id="detail_type">小会</td>
					</tr>
				</table>
			</div>
			<!-- 弹出的dialog 用来显卡详细信息EDN -->


			<!-- 弹出的dialog 用来添加，修改用户 START -->
			<div id="depart_modify_Dialog"
				style="width: 350px; text-align: center; background-image: url('<%=path%>/Images/tsai/uploadify_bg.png');"
				class="easyui-dialog"
				data-options="title:'添加/修改',closed:true,modal:true,buttons:[{
				text:'修改/添加',
				iconCls:'icon-edit',
				handler:function(){
					$('#depart_modify_Form').submit();
				}
			}]">
				<form id="depart_modify_Form" method="post"
					style=" background-image: url('<%=path%>/Images/tsai/uploadify_bg.png');">
					<center>
						<table
							style="width: 300px; text-align: center; background-image: url('<%=path%>/Images/tsai/uploadify_bg.png');">
							<tr>
								<th>机构：<select id="jiguo_dialog"
									onchange="loadInfo_dialog()" style="width: 100px;"
									name="jiguo_dialog">
										<option value="0">请选择</option>
								</select></th>


								<th>部门 :<select id="bumen_dialog" style="width: 100px;"
									name="bumen_dialog">
										<option value="0">请选择</option>
								</select></th>

							</tr>
							<tr style="display: none;">
								<th>ID：</th>
								<td><input id="scheduleId" name="schedule.scheduleId" /></td>
							</tr>
							<tr>
								<th>标题：</th>
								<td><input id="meetingTitle" name="schedule.title"
									class="easyui-validatebox"
									data-options="required:true,missingMessage:'标题必填'" /></td>
							</tr>
							<tr>
								<th>会议地址</th>
								<td><input id="meetingAddress" type="text"
									name="schedule.address" class="easyui-validatebox"
									data-options="required:true,missingMessage:'会议地址必填'" /></td>
							</tr>
							<tr>
								<th>开始时间</th>
								<td><input id="startTime" class="easyui-datebox"
									name="schedule.beginTime" required="required"
									data-options="onSelect:onSelectStartTime,missingMessage:'必填'"></input>
							</tr>
							<tr>
								<th>结束时间</th>
								<td><input id="endTime" class="easyui-datebox"
									name="schedule.endTime" required="required"
									data-options="onSelect:onSelectEndTime,missingMessage:'必填',validType:'larger[$(\'#startTime\')]'"></input>
							</tr>
							<tr>
								<th>会议内容</th>
								<td><textarea id="meetingContent"
										name="schedule.schContent" type="text"
										class="easyui-validatebox"
										style="width: 130px; height: 135px; resize: none;"
										data-options="required:true,missingMessage:'必填'"></textarea></td>
							</tr>
							<tr>
								<th>会议类型</th>
								<td><select id="meetingInfo"
									name="schedule.meetinginfo.meetingId" style="width: 130px;">
								</select></td>
							</tr>
						</table>
					</center>

				</form>
			</div>
			<!-- 弹出的dialog 用来添加，修改用户  END-->
		</div>

		<!-- 右击Menu START -->
		<div id="mm" class="easyui-menu" data-options="onClick:menuHandler"
			style="width: 60px;">
			<div data-options="name:'detailInfo',iconCls:'schedule-manage '">详细</div>
		</div>
		<!-- 右击Menu END  -->
	</center>
	<script type="text/javascript">
		//加载jiguo
		function loadOne(select, eq, url_theme) {
			$.ajax({
				url : url_theme,
				dataType : "json",
				type : "post",
				success : function(data) {

					for ( var key in data) {
						if (key == eq) {
							for ( var key2 in data[key]) {
								var item = $("<option></option>").val(
										data[key][key2].branchId).html(
										data[key][key2].branchName);
								$("#" + select).append(item);
							}

						}
					}

				},
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '加载信息出错',
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
			});

		}
		//加载部门
		function loadTwo(select, eq, url_theme) {
			$.ajax({
				url : url_theme,
				dataType : "json",
				type : "post",
				success : function(data) {

					for ( var key in data) {
						if (key == eq) {
							$("#" + select).append(
									"<option value='0'>请选择</option>");
							for ( var key2 in data[key]) {
								var item = $("<option></option>").val(
										data[key][key2].departId).html(
										data[key][key2].departName);
								$("#" + select).append(item);
							}

						}
					}

				},
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '加载信息出错',
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
			});
		}
		//两级连动
		var bumen = $("#bumen");
		var jiguo = $("#jiguo");
		function loadInfo() {
			bumen.empty();

			loadTwo("bumen", "departinfos",
					"${pageContext.request.contextPath}/departSchedule/loadDepart?foreiId="
							+ jiguo.val());
		}
		function loadInfo_dialog() {
			$("#bumen_dialog").empty();

			loadTwo("bumen_dialog", "departinfos",
					"${pageContext.request.contextPath}/departSchedule/loadDepart?foreiId="
							+ $("#jiguo_dialog").val());
		}

		//选中日期控件
		var datetime;
		function onSelect(date) {
			datetime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate();
		}
		$("#datebox").datebox({
			onChange : function(newDate, oldDate) {
				datetime = newDate;
			}
		});
		//点击搜索
		function doSearch(value, name) {

			$('#dg')
					.datagrid(
							{
								url : "${pageContext.request.contextPath}/departSchedule/precontractAction!showPage?datetime="
										+ datetime
										+ "&name="
										+ value
										+"&bumen"+ bumen.val() + "&jiguo=" + jiguo.val()
							});

			/* 
				 alert('value: ' + value + '\ndatetime:' + datetime + "\nbumen.val:"
				 + bumen.val() + "\njiguo.val:" + jiguo.val()); */
		}

		//格式化表格中数据
		function managerMeetinginfoFormat(val, row) {
			if (val)
				return val.meetingName;
			else
				return "";
		}

		//点击表格中行
		var rowData, rowIndex;
		$('#dg').datagrid({
			onClickRow : function onClickRow(rowIndex1, rowData1) {
				rowData = rowData1;
				rowIndex = rowIndex1;
			}
		});
		//验证是否有选中一行
		function validataselectRow() {
			if (rowIndex == undefined) {

				$.messager.show({
					title : '提示',
					msg : '请选中一行',
					timeout : 2000,
					style : {
						right : '',
						top : document.body.scrollTop
								+ document.documentElement.scrollTop,
						bottom : ''
					},
					showType : 'slide'

				});
				$('#dg').datagrid("reload");
				return false;
			} else {
				return true;
			}
		}

		//得到两个日期控制的
		var startTime, endTime;
		function onSelectStartTime(date) {
			startTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate();
		}
		function onSelectEndTime(date) {
			endTime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate();
		}
		//添加
		function append() {
			//打开对话框
			$('#depart_modify_Form input').val("");
			$('#depart_modify_Dialog').dialog("open");
		}
		//删除
		function del() {
			if (!validataselectRow()) {
				return;
			}
			//提示用户是否要删除
			$.messager
					.confirm(
							'提示',
							'你确定要删除吗?(删除这个会同时删除有关系的数据)',
							function(r) {
								//重新加载数据表格
								if (r) {
									//这里进行删除
									//调用后台通过ID删除的方法
									$
											.ajax({
												url : '${pageContext.request.contextPath}/departSchedule/deleteObj?scheduleId='
														+ rowData.scheduleId,
												dataType : "json",
												type : "post",
												success : function(data) {
													if (data) {
														$.messager
																.show({
																	title : '提示',
																	msg : '删除成功',
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
													$('#dg').datagrid(
															'deleteRow',
															rowIndex);

												},
												error : function() {
													$.messager
															.show({
																title : '错误',
																msg : '加载信息出错',
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
											});
								}

								$('#dg').datagrid("reload");
							});

		}

		//修改
		function modify() {
			if (!validataselectRow()) {
				//没有选中行
				return;
			}
			//打开对话框
			$('#depart_modify_Dialog').dialog("open");

			//打开对话框 并 设置 dialog中相应对象的显示的值
			$("#scheduleId").val(rowData.scheduleId);
			$("#meetingTitle").val(rowData.title);
			$("#meetingAddress").val(rowData.address);
			$('#startTime').datebox('setValue', rowData.beginTime);
			$('#endTime').datebox('setValue', rowData.endTime);
			$("#meetingContent").val(rowData.schContent);
			//设置dialog中meetingInfo select 应该选中那一个
			var value = rowData.meetinginfo.meetingId;
			$("#meetingInfo option[value='" + value + "']").attr("selected",
					"selected");

			//设置dialog中jiguo_dialog select 应该选中那一个

			//设置dialog中bumen_dialog select 应该选中那一个

		}

		//在dialog中加载loadMeeting()
		function loadMeeting(select, eq, url_theme) {
			$("#" + select).empty();
			$.ajax({
				url : url_theme,
				dataType : "json",
				type : "post",
				success : function(data) {

					for ( var key in data) {
						if (key == eq) {
							for ( var key2 in data[key]) {
								var item = $("<option></option>").val(
										data[key][key2].meetingId).html(
										data[key][key2].meetingName);
								$("#" + select).append(item);
							}

						}
					}

				},
				error : function() {
					$.messager.show({
						title : '错误',
						msg : '加载信息出错',
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
			});

		}

		//表格右键
		var rightData;
		function onRowContextMenu(e, rowIndex, rowData) {
			e.preventDefault();
			rightData = rowData;
			$('#mm').menu('show', {
				left : e.pageX,
				top : e.pageY
			});
		}
		//菜单选中
		function menuHandler(item) {
			console.info(rightData.scheduleId);
			if (item.name == "detailInfo") {
				//打开详细页面
				$('#depart_detail_Dialog').dialog('open');
				$("#detail_address").html(rowData.address);
				$('#detail_beginTime').html(rowData.beginTime);
				$('#detail_endTime').html(rowData.endTime);
				$("#detail_Content").html(rowData.schContent);
				$("#detail_type").html(rowData.meetinginfo.meetingName);
			}
		}

		//上来就加载
		$(function() {

			//设置隐藏列
			$('#dg').datagrid('hideColumn', 'scheduleId');
			//设置右击
			$('#dg').datagrid({
				onRowContextMenu : onRowContextMenu
			});

			$('#depart_modify_Dialog').dialog(
					{
						//当修改dialog关闭时，remove selected ntnt
						onClose : function() {
							var value = rowData.meetinginfo.meetingId;
							$("#meetingInfo option[value='" + value + "']")
									.removeAttr("selected");
						}
					});

			//加载机构
			loadOne("jiguo", "branchInfos",
					"${pageContext.request.contextPath}/departSchedule/loadBranch");
			loadOne("jiguo_dialog", "branchInfos",
					"${pageContext.request.contextPath}/departSchedule/loadBranch");
			//加载会议类型
			loadMeeting("meetingInfo", "meetingInfo",
					"${pageContext.request.contextPath}/departSchedule/meetingAction!showAll");

			//数据表格加载
			$('#dg')
					.datagrid(
							{
								url : "${pageContext.request.contextPath}/departSchedule/showPage",
								striped : true
							});
			/* 增加回车提交功能 */
			$('#depart_modify_Form input').bind('keyup', function(event) {
				if (event.keyCode == '13') {
					$('#depart_modify_Form').submit();
				} else if (event.keyCode == '27') {
					$('#depart_modify_Dialog').dialog('close');
				}
			});

			//弹出框 修改/添加
			$('#depart_modify_Form')
					.form(
							{
								url : '${pageContext.request.contextPath}/departSchedule/saveOrUpdate',
								success : function(r) {
									if (r != null) {
										$('#depart_modify_Dialog').dialog(
												'close');
										$('#dg').datagrid("reload");
										$.messager
												.show({
													title : '提示',
													msg : "成功",
													timeout : 2000,
													style : {
														right : '',
														top : document.body.scrollTop
																+ document.documentElement.scrollTop,
														bottom : ''
													},
													showType : 'slide'
												});
									} else {
										$.messager
												.show({
													title : '提示',
													msg : "失败",
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
								}

							});
			//设置focus
			window.setTimeout(function() {
				$("#depart_modify_Form input[name='schedule.title']").focus();
			}, 0);
		});
	</script>
</body>
</html>