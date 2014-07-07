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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>员 工 考 勤 统 计</title>
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
</head>
<body>

	<center>
		<h2>
			<b>员 工 考 勤 统 计</b>
		</h2>

		<table>
			<tr>
				<td>开始时间:</td>
				<td><input type="text" id="start" style="width: 150px;" /></td>
				<td>结束时间:</td>
				<td><input type="text" id="over" style="width: 150px;">
				</td>
				<td><input type="radio" name="date" id="currentDay"
					onclick="currentDay()">本日 <input type="radio" name="date"
					id="currentWeek" onclick="currentWeek()">本周 <input
					type="radio" name="date" id="currentMonth" onclick="currentMonth()">本月
					<a class="easyui-linkbutton" id="cancel" onclick="cancel()">取消选中</a></td>
			</tr>
		</table>
		<hr color="#eaf2ff" />
		<table>
			<tr>
				<td>所属机构:</td>
				<td><select style="width: 150px; border: 1px solid #dedede;"
					id="jiguo" onchange="loadInfo()"><option value="0">请选择</option>
				</select></td>
				<td>所属部门:</td>
				<td><select style="width: 150px; border: 1px solid #dedede"
					id="bumen"><option value="0">请选择</option>
				</select></td>
				<td><a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="count()">&nbsp;&nbsp;&nbsp;统 计&nbsp;&nbsp;&nbsp;</a></td>
				<td></td>
			</tr>
		</table>
		<hr color="#eaf2ff" />

		<table id="dg" style="width: 800px; height: 350px" rownumbers="true"
			toolbar="#toolbar" pagination="true" idField="workTimeId"
			fitColumns="true" singleSelect="true">
			<thead>
				<tr>
					<th field="userinfo" width="50" formatter="userinfoFormatter">姓名</th>
					<th field="userinfo" width="80">出勤率(%)</th>
					<th field="userinfo" width="80">迟到次数</th>
					<th field="userinfo" width="80">早退次数</th>
					<th field="userinfo" width="80">旷工次数</th>
					<th field="userinfo" width="80">所属部门</th>
					<th field="userinfo" width="80">所属机构</th>
				</tr>
			</thead>
		</table>

	</center>
	<script type="text/javascript">
		//加载所有
		$(function() {
			$('#dg').edatagrid({
				url : '${pageContext.request.contextPath}/workTime/showPage'
			});
		});

		//用户点击统计
		function count() {
			var startTime = undefined;
			var entTime = undefined;
			var branchId = undefined;
			var departId = undefined;

			//进行判断 分别给上述 4个进行赋值 
			startTime = $('#start').datebox('getValue');
			if (startTime == "") {
				startTime = undefined;
			}

			entTime = $('#over').datebox('getValue');
			if (entTime == "") {
				entTime = undefined;
			} else {
				entTime += " 23:59:59";
			}

			branchId = $('#jiguo').val();
			if (branchId == 0) {
				branchId = undefined;
			}

			departId = $('#bumen').val();
			if (departId == 0) {
				departId = undefined;
			}

			console.info("startTime:" + startTime);
			console.info("entTime:" + entTime);
			console.info("branchId:" + branchId);
			console.info("departId:" + departId);
			$('#dg').edatagrid({
				fitColumns : true,
				url : '${pageContext.request.contextPath}/workTime/showPage',
				queryParams : {
					'manualsignStart.signTime' : startTime,
					'manualsignEnd.signTime' : entTime,
					'branchinfo.branchId' : branchId,
					'departinfo.departId' : departId
				},
				columns : [ [ {
					field : 'userinfo',
					title : '姓名',
					width : 50,
					formatter : function(val) {
						if (val) {
							return val.userName;
						} else {
							return "";
						}
					}
				}, {
					field : 'name',
					title : '出勤率（%） ',
					width : 80
				}, {
					field : 'price',
					title : '迟到次数',
					width : 80
				}, {
					field : 'price',
					title : '早退次数',
					width : 80
				}, {
					field : 'price',
					title : '旷工次数 ',
					width : 80
				}, {
					field : 'price',
					title : '所属部门 ',
					width : 80
				} ] ]

			});
		}

		function userinfoFormatter(val) {
			if (val) {
				return val.userName;
			} else {
				return "";
			}
		}
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
		function loadInfo() {
			var bumen = $("#bumen");
			var jiguo = $("#jiguo");
			bumen.empty();

			loadTwo(
					"bumen",
					"departinfos",
					"${pageContext.request.contextPath}/workTime/scheduleAction_loadDepart?foreiId="
							+ jiguo.val());
		}
		$(function() {
			//加载机构
			loadOne("jiguo", "branchInfos",
					"${pageContext.request.contextPath}/workTime/scheduleAction_loadBranch");

			$('#start').datebox({
				validType : [ 'date[]', 'less[$(\'#over\')]' ],
				onChange : function(newDate, oldDate) {
					startTime = newDate;
				}

			});
			$('#over').datebox({
				validType : [ 'date[]', 'larger[$(\'#start\')]' ],
				onChange : function(newDate, oldDate) {
					entTime = newDate;
				}

			});

		});

		//用户点击本日
		function currentDay() {
			var date = new Date();
			var newDate = formatterDay(date, 0, 0);
			$('#start').datebox('setValue', newDate);
			$('#over').datebox('setValue', newDate);
		}
		//点击本周
		function currentWeek() {
			var date = new Date();
			var fewdays = date.getDay();
			if (fewdays == 0) {
				fewdays = 7;
			}

			var newDateS = formatterDay(date, (-fewdays + 1), 0);
			var newDateE = formatterDay(date, (7 - fewdays), 0);

			$('#start').datebox('setValue', newDateS);
			$('#over').datebox('setValue', newDateE);
		}
		//点击本月
		function currentMonth() {
			var date = new Date();
			var newDateS = date.getFullYear() + "-" + (date.getMonth() + 1)
					+ "-" + 1;
			var newDateE = date.getFullYear()
					+ "-"
					+ (date.getMonth() + 1)
					+ "-"
					+ getLastDateOfMonth(date.getFullYear(), date.getMonth())
							.getDate();
			$('#start').datebox('setValue', newDateS);
			$('#over').datebox('setValue', newDateE);
		}
		//点击取消
		function cancel() {
			$('#start').datebox('setValue', "");
			$('#over').datebox('setValue', "");
		}
		/**
		 */
		function getLastDateOfMonth(year, month) {
			return new Date(new Date(year, month + 1, 1).getTime() - 1000 * 60
					* 60 * 24);
		}

		//格式化日期
		function formatterDay(date, incrementDate, incrementMonth) {
			var newDate = date.getFullYear() + "-"
					+ (date.getMonth() + 1 + incrementMonth) + "-"
					+ (date.getDate() + incrementDate);
			return newDate;
		}

		/*  单选框取消选中*/
		$(function() {

			var $date = $("input[name=date]");
			var $cancel = $("#cancel");

			$cancel.click(function(e) {
				$date.removeAttr("checked");
				$date.attr("checked", false);
			});
		});
	</script>
</body>
</html>