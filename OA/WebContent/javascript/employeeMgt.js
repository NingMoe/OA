		
		/* -------------------- datagrid ----------------------- */
		//获取项目名
		var pathName=window.document.location.pathname; 
		
		// 获取带"/"的项目名，如：/Tmall
		var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1); 
		
		$(function() {
			// 加载所有员工
			$('#dg').edatagrid({
				url : projectName+'/user/showPage',
				saveUrl : '',
				updateUrl : '',
				destroyUrl : projectName+'/user/remove',
				destroyMsg:{
					norecord:{	// when no record is selected
						title:'警告',
						msg:'没有记录被选中!'
					},
					confirm:{	// when select a row
						title:'注意',
						msg:'此操作,将会删除员工记录和所有关联到该员工的记录!'
							+'\r\n我们建议你修改用户,将用户状态设置为\'已屏蔽\'状态.'
							+'是否继续删除?'
					}
				}
			});
			
			/*$('#trIcon img').attr('src','/OA/Images/bg.gif');*/
		});
		
		// 以下三个方法旨在提取子属性
		function roleinfosFormatter(val){
			var result="";
			if(val && val.length>0){
				for(i=0;i<val.length;i++){
					result+=val[i].roleName;
					if(i<val.length-1){result+=",";}
				}
			}
			return result;
		}
		function genderFormatter(val){
			if(val==1){
				return '男';
			}else{
				return '女';
			}
		}
		function stateFormatter(val){
			if(val){
				return val.userStateName;
			}else{
				return "";
			}
		}
		
		// 未选中,弹出消息
		function rowUncheckedMessage() {
			$.messager.show({
				title : '警告',
				msg : '没有记录被选中!'
			});
		}
		
		/* -------------------- toolbar ----------------------- */
		// 添加员工信息
		function append() {
			// 打开对话框
			$('#user_edit_Form').form('clear');
			$("#userId").attr({readOnly:false});
			$("#userId").validatebox({validType:['loginName','nameExists']});
			$("#imgIcon").attr('src','/OA/Images/no img.jpg');
			$('#user_edit_Dialog').dialog("open");
		}

		// 修改员工信息
		function modify() {
			var rowData=$('#dg').datagrid('getSelected');
			if (rowData!=null) {
				// 清空表单数据
				$('#user_edit_Form').form('clear');
				// 给表单填充数据
				$("#userId").val(rowData.userId).attr('readOnly',true);
				$("#userId").validatebox({validType:''});
				$("#passWord").val(rowData.passWord);
				if(rowData.avatarFile!=undefined){
					$("#imgIcon").attr('src','/OA/'+rowData.avatarFile.filePath.replace('\\','/'));
					console.info("path:  "+rowData.avatarFile.filePath);
				}else{
					$("#imgIcon").attr('src','/OA/Images/no img.jpg');
				}
				$("#rePassWord").val(rowData.passWord);
				$("#userName").val(rowData.userName);
				$("#gender").combobox('select',rowData.gender);
				$("#departinfo").combobox('select', rowData.departId);
				var length = rowData.roleinfos.length;
				if(length>0){
					for(i=0;i<length;i++){
						$("#roleName").combobox('select',rowData.roleinfos[i].roleId);
					}
				}
				$("#userStateName").combobox('select',rowData.userstate.userStateId);
				
				// 打开编辑对话框
				$('#user_edit_Dialog').dialog('open');
			}else{
				// 没有选中行
				rowUncheckedMessage();
				return;
			}

		}
		
		
		/**
		 * 查看员工详细信息
		 */
		function view(){
			var rowData=$('#dg').datagrid('getSelected');
			if (rowData!=null) {
				if(rowData.avatarFile){
					$('#icon img').attr('src','/OA/'+rowData.avatarFile.filePath.replace('\\','/'));
				}else{
					$('#icon img').attr('src','/OA/Images/no img.jpg');
				}
				$('#fUserId').text(rowData.userId);
				$('#fUserName').text(rowData.userName);
				$('#fPassWord').text(rowData.passWord);
				$.getJSON('/OA/depart/show',{'depart.departId':rowData.departId},function(json){
					$('#fDepart').text(json.depart.departName);
				});
				$('#fGender').text(rowData.gender==1?'男':'女');
				
				$('#fUserRole').text("未分配角色");
				if(rowData.roleinfos && rowData.roleinfos.length>0){
					var roles="";
					for(i=0;i<rowData.roleinfos.length;i++){
						roles+=rowData.roleinfos[i].roleName;
						roles+=i<rowData.roleinfos.length-1?",":"";
					}
					$('#fUserRole').text(roles);
				}
				
				$('#fUserState').text(rowData.userstate.userStateName);
				
				// 打开编辑对话框
				$('#user_view_Dialog').dialog('open');
			}else{
				// 没有选中行
				rowUncheckedMessage();
				return;
			}
		}
		/* -------------------- dialog ----------------------- */
		function submitForm() {
			$.messager.progress();
			$('#user_edit_Form').form('submit',{
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	// 当form不合法的时候隐藏工具条
					}
					return isValid;	// 返回false将停止form提交
				},
				success: function(){
					$.messager.progress('close');	// 当成功提交之后隐藏进度条
					// 关闭编辑对话框
					//$('#user_edit_Dialog').dialog('close');
					//$('#dg').datagrid('load');
					location.reload();
				}
			});
		}
		
		/**
		 * 图片加载失败
		 */
		function nofind(event){
			var img=event.target;
			img.src='/OA/Images/not found.jpg';
		} 
		
		/**
		 * 验证文件信息
		 */
		function validateFile(event){
			
			var files=event.target.files;
			
			//文件类型不合法,错误提醒后退出
			if(files[0].type.indexOf('image')==-1){
				alert('仅允许图片类型,请重新选择!');
				return false;
			}
			
			if(files[0].size>(1024*1024)){
				alert('选择的文件太大!');
				return false;
			}
			
		}
		
		function userEditOpen(){
			console.info('dialog open...');
		}
		
		function clearForm() {
			var uid = $("#userId").val();
			$('#user_edit_Form').form('clear');
			$("#userId").val(uid);
		}
		
