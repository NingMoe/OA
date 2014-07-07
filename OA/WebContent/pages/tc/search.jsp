<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ page isELIgnored="false" %>
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
</head>
<body class="easyui-layout">

	<center>
		<h3>
			<b>员 工 考 勤 历 史 记 录 查 询</b>
		</h3>
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
				<td style="padding-right: 50px"></td>
				<td><img alt="搜索"
					src="${pageContext.request.contextPath}/Images/search.png"></td>
				<td>查找范围：</td>
<td><input id="jiguoCheck" type="checkbox" onclick="enable1()" />
				</td>
				<td>按机构</td>
				<td><input id="bumenCheck" type="checkbox" onclick="enable2()" />
				</td>
				<td>按部门</td>
				<td><input type="checkbox" id="id" onclick="enable3()" /></td>
				<td>按员工号</td>
			</tr>

		</table>
		<hr color="#eaf2ff" />
		<table>
			<tr>
				<td>机构:</td>
				<td><select style="width: 150px;" id="jiguo"
					disabled="disabled" onchange="loadInfo()"><option
							value="0">请选择</option>
				</select></td>
				<td>部门:</td>
				<td><select disabled="disabled" style="width: 150px;"
					id="bumen"><option value="0">请选择</option>
				</select></td>
				<td>员工号:</td>
				<td><input type="text" id="number" disabled="disabled">
				</td>
			</tr>
		</table>
		<a href="javascript:void(0)" class="easyui-linkbutton"
			style="width: 120px;" onclick="doSearch()">搜索</a>
		<hr color="#eaf2ff" />
	</center>

	<center>
		<div>
			<!-- 数据表格  START-->
			<table id="dg" style="width: 800px; height: 300px" rownumbers="true"
				toolbar="#toolbar" pagination="true" idField="signId"
				fitColumns="true" singleSelect="true">
				<thead>
					<tr>
						<th field="signTime" width="200">签卡时间</th>
						<th field="signTag" width="100"
							data-options="formatter:signTagFormat">签卡标记</th>
						<th field="signDesc" width="100">签卡备注</th>
						<th field="userinfo" data-options="formatter:departFormat"
							width="100">所属部门/姓名</th>
					</tr>
				</thead>
			</table>
	</center>

	<script type="text/javascript">
		var startTime;
		var entTime;
		var dataObj;

		//点击搜索
		function doSearch() {
			//
			console.info("doSearch");
			var startTime = undefined;
			var entTime = undefined;
			var branchId = undefined;
			var departId = undefined;
			var name = undefined;
			var number = undefined;

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

			number = $("#number").val();
			if (number == "") {
				number = undefined;
			}

			//重新加载datagrid
			$('#dg').edatagrid({
				url : '${pageContext.request.contextPath}/history/showPage',
				queryParams : {
					'manualsignStart.signTime' : startTime,
					'manualsignEnd.signTime' : entTime,
					'branchinfo.branchId' : branchId,
					'departinfo.departId' : departId,
					'userinfo.userId' : number,

				}

			});
		}

		//格式化部门
		function departFormat(val, row) {
			var len = dataObj.length;
			if (val) {
				for (var i = 0; i < len; i++) {
					if (dataObj[i].departId = val.departId) {
						return dataObj[i].departName + "／" + val.userName;
					}
				}

			} else {
				return "";
			}
		}
		//格式化退到
		function signTagFormat(val, row) {
			if (val == 0) {
				return "签到";
			} else {
				return "签退";
			}
		}
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

		//加载所有
		$(function() {
			$('#dg').edatagrid({
				url : '${pageContext.request.contextPath}/history/showPage'

			});
		});

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
							$("#" + select).append(item);
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
			loadTwo("bumen", "departinfos",
					"${pageContext.request.contextPath}/history/scheduleAction_loadDepart?foreiId="
							+ jiguo.val());
		}

		//加载所有部门
		function loadAll() {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/history/userinfoAction_showAllDepart",
						dataType : "json",
						type : "post",
						success : function(data) {
							var len = data.length;
							dataObj = data;
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

		$(function() {

			//加载所有部门
			loadAll();

			//加载机构
			loadOne("jiguo", "branchInfos",
					"${pageContext.request.contextPath}/history/scheduleAction_loadBranch");

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

		function enable1() {
			var jiguo = $("#jiguo");
			if (document.getElementById("jiguoCheck").checked) {
				jiguo.removeAttr("disabled");
			} else {
				jiguo.attr("disabled", "disabled");
			}

		}
		/* 	document.getElementById("depart").onclick = function() {
		 enable2();

		 }; */
		function enable2() {
			var bumen = $("#bumen");
			if (document.getElementById("bumenCheck").checked) {
				bumen.removeAttr("disabled");
			} else {
				bumen.attr("disabled", "disabled");
			}
		}
		/* 	document.getElementById("id").onclick = function() {
		 enable3();

		 }; */
		function enable3() {
			var number = $("#number");
			if (document.getElementById("id").checked) {
				number.removeAttr("disabled");
			} else {
				number.attr("disabled", "disabled");
			}
		}
		function enable4() {
			var name = $("#name");
			if (document.getElementById("username").checked) {
				name.removeAttr("disabled");
			} else {
				name.attr("disabled", "disabled");
			}
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