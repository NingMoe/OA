/**
 * 
 */
// 加载所有日程
$(function() {
	// 加载jiguo
	loadOne("jiguo", "branchInfos");

	$('#dg').datagrid({
		url : "/OA/zhu/scheduleAction!showPage"
	});
});

function loadOne(select, eq) {
	$
			.ajax({
				url : "/OA/zhu/scheduleAction!loadBranch",
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
// 两级连动
function loadInfo() {
	var bumen = $("#bumen");
	var jiguo = $("#jiguo");
	bumen.empty();
	$
			.ajax({
				url : "/OA/zhu/scheduleAction!loadDepart?foreiId="
						+ jiguo.val(),
				dataType : "json",
				type : "post",
				success : function(data) {

					for ( var key in data) {
						if (key == "departinfos") {
							for ( var key2 in data[key]) {
								var item = $("<option></option>").val(
										data[key][key2].departId).html(
										data[key][key2].departName);
								bumen.append(item);
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
// 选中日期控件
var datetime;
function onSelect(date) {
	datetime = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
			+ date.getDate();
}
// 点击搜索
function doSearch(value, name) {
	$('#dg').datagrid({
		url : "/OA/zhu/scheduleAction!showPage"
	});
	alert('value: ' + value + '\ndatetime:' + datetime);
}

// 格式化表格中数据
function managerMeetinginfoFormat(val, row) {
	if (val)
		return val.meetingName;
	else
		return "";
}

// 点击表格中行
var rowData, rowIndex;
$('#dg').datagrid({
	onClickRow : function onClickRow(rowIndex1, rowData1) {
		rowData = rowData1;
		rowIndex = rowIndex1;
	}
});
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
// 添加
function append() {
	if (!validataselectRow()) {
		console.info("1saa");
		return;
	}
	console.info("saa");
}
// 删除
function del() {
	if (!validataselectRow()) {
		console.info("remove1");
		return;
	}
	console.info("remove");
	$.messager.confirm('提示', '你确定要删除吗?', function() {
		// 这里进行删除
		//
		//
		$('#dg').datagrid('deleteRow', rowIndex).datagrid("reload");
	});

}
// 修改
function modify() {
	if (!validataselectRow()) {
		console.info("modify");
		return;
	}
	$('#depart_modify_Dialog').dialog("open");
}
