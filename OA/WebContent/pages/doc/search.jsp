<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	String path = request.getContextPath();%>
<script type="text/javascript">
/* 点击展示搜索选项 */
function checkShow(element){
	var e = $(element);
	e.css('display')=='none'?e.css('display','block'):e.css('display','none');
}

/* 点击控件可用 */
function checkOne(i){
	$('#monthAgo').numberspinner('disable');
	$('#dayAgo').numberspinner('disable');
	
	$('#dateBetween1').datebox('disable');
	$('#dateBetween2').datebox('disable');
	
	if(i==1){
		$('#monthAgo').numberspinner('enable');
	}else if(i==2){
		$('#dayAgo').numberspinner('enable');
	}else if(i==3){
		$('#dateBetween1').datebox('enable');
		$('#dateBetween2').datebox('enable');
	}
	
}

/* 清理搜索表单 */
function clear_sf(){
	/* $('#searchForm').form('clear'); */
	$('#tabs').tabs('reload');
}
</script>
<div class="Tab1" title="搜索文件" iconCls="icon-search">
	<form action="#" class="easyui-form" id="searchForm">
		<div align="center"
			style="margin: 5px; text-align: left; line-height: 20px;">
			<img alt=""
				src="<%=path%>/jquery-easyui-1.3.3/themes/icons/folder_magnify.png">
			从<a href="#" title="访问远程服务器,获取到最新的数据,需要一定的网络带宽!"
				class="easyui-tooltip">远程主机</a>上搜索文件和文件夹.
			<hr color="#D4D0C8" size="3" />
			要搜索的文件或文件夹名称为:<br> <input type="text"><br /> 备注信息:<br>
			<input type="text"><br /> 搜索范围:<br> <input
				id="cbxScope" class="easyui-combobox"
				data-options="valueField: 'id',textField: 'text',panelHeight:'auto',editable:false"><br>
			<a href="#" class="easyui-linkbutton" iconCls="icon-search"
				style="margin: 10px 2px 5px 10px"><img alt="立即搜索"> </a> <a
				href="#" class="easyui-linkbutton" style="margin: 10px 2px 5px 10px"
				iconCls="icon-undo" onclick="clear_sf()"><img alt="重置"> </a>
			<hr color="#D4D0C8" size="3" />

			<!-- 搜索选项 -->
			<div id="advencedSearch" class="easyui-panel"
				data-options="title:'搜索选项',collapsible:true">
				<input type="checkbox" id="chDate" onclick="checkShow('#divDate')">日期<br>

				<div class="hideBlock" id="divDate">
					<table>
						<tr>
							<td><input type="radio" name="d" onclick="checkOne(1)">
							</td>
							<td>前</td>
							<td><input id="monthAgo" class="easyui-numberspinner"
								data-options="value:1,min:1,max:100,width:60,disabled:true">个月</td>
						</tr>
						<tr>
							<td><input type="radio" name="d" onclick="checkOne(2)">
							</td>
							<td>前</td>
							<td><input id="dayAgo" class="easyui-numberspinner"
								data-options="value:1,min:1,max:100,width:60,disabled:true">天</td>
						</tr>
						<tr>
							<td><input type="radio" name="d" onclick="checkOne(3)">
							</td>
							<td>介于</td>
							<td><input id="dateBetween1" class="easyui-datebox"
								data-options="width:100,disabled:true" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td>和</td>
							<td><input id="dateBetween2" class="easyui-datebox"
								data-options="width:100,disabled:true" />
							</td>
						</tr>
					</table>
				</div>
				<input type="checkbox" id="chType" onclick="checkShow('#divType')">类型<br>
				<div class="hideBlock" id="divType">
					<input id="selFileType" class="easyui-combobox"
						style="width: 120px;" />
				</div>

				<input type="checkbox" id="chOwner" onclick="checkShow('#divOwner')">所有者<br>
				<div class="hideBlock" id="divOwner">
					<input class="easyui-combobox" style="width: 120px;" />
				</div>
			</div>
		</div>

	</form>
</div>
