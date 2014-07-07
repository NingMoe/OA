<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<link rel="stylesheet" type="text/css"
	href="../../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="../../jquery-easyui-1.3.3/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="../../jquery-easyui-1.3.3/demo/demo.css">
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="../../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var datetime;

	var arr = new Array();

	//加载当前日期和当前用户中的日程
	function loadDateSchedule(select, eq, url_theme) {
		$.ajax({
			url : url_theme,
			dataType : "json",
			type : "post",
			success : function(data) {

				for ( var key in data) {
					if (key == eq) {
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
					timeout : 1000,
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

	//异步得到日期数组//加载dates
	function test() {
		$('#cc')
				.calendar(
						{
							formatter : function formatDay(date) {
								//console.info("formatDay()");
								//日历中的月份和日子
								var month = (date.getMonth() + 1);
								var day = date.getDate();
								//月,日 对月日进行行加0处理如  1-->01
								if (month < 10) {
									month = "0" + month;
								}
								if (day < 10) {
									day = "0" + day;
								}
								var datetime = date.getFullYear() + '-' + month
										+ '-' + day;

								var d = date.getDate();

								var opts = $(this).calendar('options');

								var current = opts.current.getFullYear() + "-"
										+ month + "-" + day;

								var len = arr.length;
								for (var j = 0; j < len; j++) {
									if (arr[j] == datetime) {
										return '<div class="icon-ok md">' + d
												+ '</div>';
									}
								}
								return d;
							},
							onSelect : function(date) {
								//日历中的月份和日子
								var month = (date.getMonth() + 1);
								var day = date.getDate();
								//月,日 对月日进行行加0处理如  1-->01
								if (month < 10) {
									month = "0" + month;
								}
								if (day < 10) {
									day = "0" + day;
								}
								var datetime = date.getFullYear() + '-' + month
										+ '-' + day;
								$('#mySchedule_dg').datagrid('loadData', {
									total : 0,
									rows : []
								});
								$('#mySchedule_dg')
										.datagrid(
												{
													url : "${pageContext.request.contextPath}/mySchedule/precontractAction!loadDateSchedule?datetime="
															+ datetime,

												});

								$('#mySchedule_dialog').dialog({
									title : '日程信息',
									width : 680,
									closed : false,
									cache : false,
									href : '',
									modal : true
								});
							}
						});
	}
	setTimeout("test()", 1000);
</script>
</head>
<body>
	<center>
		<div class="" style="margin: 0px 0px">
			<div class="demo-tip icon-tip"></div>
			<h3 style="font-size: 20px; color: red;">个人日程管理</h3>
		</div>
		<hr color="#eaf2ff" />
		<div id="cc" class="easyui-calendar"
			style="width: 680px; height: 400px;"></div>
	</center>
	<script type="text/javascript">
		//上来就加载
		$(function() {
			$("#mySchedule_Form input").attr("readonly", "readonly");
		});

		$
				.ajax({
					url : "${pageContext.request.contextPath}/mySchedule/loadDates",
					dataType : "json",
					type : "post",
					success : function(data) {
						//加载数据成功，把data中的数据转成dates;
						for ( var key in data) {
							if (key == "dates") {
								arr = data[key];
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

		//格式化表格中数据
		function managerMeetinginfoFormat(val, row) {
			if (val)
				return val.meetingName;
			else
				return "";
		}
	</script>
	<!-- Dialog -->
	<div id="mySchedule_dialog" class="easyui-dialog"
		data-options="closed:true">
		<table id="mySchedule_dg" style="height: 235px" pagination="true"
			class="easyui-datagrid" idField="id" rownumbers="true"
			fitColumns="true" singleSelect="true">
			<thead>
				<tr>
					<th field="scheduleId" width="50">ID</th>
					<th field="title" width="50">标题</th>
					<th field="address" width="50"">地址</th>
					<th field="beginTime" width="60">开始时间</th>
					<th field="endTime" width="60">结束时间</th>
					<th field="schContent" width="50">内容</th>
					<th field="meetinginfo" width="60"
						data-options="formatter:managerMeetinginfoFormat">会议类型</th>
					<th field="createTime" width="60">创建时间</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>