$(function() {
	// 抽取变量
	var emp_datagrid = $("#emp_datagrid");
	var emp_dialog = $("#emp_dialog");
	var emp_form = $('#emp_form');

	// 初始化数据表格
	emp_datagrid.datagrid({
		url : "/employee_list.do",
		fitColumns : true,
		striped : true,
		fit : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#emp_tb',
		columns : [ [ {
			field : 'username',
			title : '用户名',
			width : 100
		}, {
			field : 'realname',
			title : '真实姓名',
			width : 100
		}, {
			field : 'tel',
			title : '电话',
			width : 100
		}, {
			field : 'email',
			title : '邮箱',
			width : 100
		}, {
			field : 'inputtime',
			title : '入职时间',
			width : 100
		}, {
			field : 'dept',
			title : '部门',
			width : 100,
			formatter : deptFormatter
		}, {
			field : 'state',
			title : '状态',
			width : 100,
			formatter : stateFormatter
		}, {
			field : 'admin',
			title : '管理员',
			width : 100,
			formatter : adminFormatter
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			// 如果当前行的状态是离职,就禁用编辑和离职按钮
			if (!rowData.state) {
				// 禁用按钮
				$("#emp_tb_cs").linkbutton('disable');
				$("#emp_tb_edit").linkbutton('disable');
			} else {
				// 显示按钮
				$("#emp_tb_cs").linkbutton('enable');
				$("#emp_tb_edit").linkbutton('enable');
			}
		}
	});

	emp_dialog.dialog({
		width : 300,
		height : 450,
		buttons : '#emp_btns',
		closed : true
	});

	// 部门格式化
	function deptFormatter(value, row, index) {
		return value ? value.name : "";
	}

	// 获取部门的其他数据,但是field不能设置重复,我们就通过row来获取
	/*
	 * function deptFormatter1(value,row,index){ return row.dept?row.dept.id:""; }
	 */
	// 状态格式化
	function stateFormatter(value, row, index) {
		return value ? "在职" : "<font color='red'>离职</font>";
	}

	// 是否管理员格式化
	function adminFormatter(value, row, index) {
		return value ? "是" : "否";
	}

	var obj = {
		name : 'will',
		showName : function() {
			alert(1)
		}
	}
	// 调用对象的方法的几种方式
/*	obj.showName();
	obj["showName"]();
	var temp = "showName";
	obj[temp]();*/

	// 使用对象来统一管理方法
	var methodObj = {
		// 新增
		add : function() {
			// 显示密码输入框
			$("#pwdInput").show();
			// 清空表单
			emp_form.form("clear");
			// 打开弹出框
			emp_dialog.dialog("open");
			// 设置标题
			emp_dialog.dialog("setTitle", "新增员工");
		},

		// 编辑
		edit : function() {
			// 判断是否选中数据
			var row = emp_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			// 隐藏密码输入框
			$("#pwdInput").hide();

			// 清空表单
			emp_form.form("clear");

			// 是否管理员下拉框回显处理,设置为字符串类型
			if(row["admin"]){
				row["admin"] = row["admin"] + "";
			}

			// 部门下拉框处理(同名匹配原则)
			if(row["dept"]){
				row["dept.id"] = row["dept"].id;
			}

			//获取该员工拥有的角色数据
		/*	$.post("/getRolesIdByEmployeeId.do",{employeeId:row.id},function(data){
				$("#roles_combobox").combobox("setValues",data);
			})
			*/

			//使用同步的方式获取数据
			 var ids = $.ajax({
				  url: "/getRolesIdByEmployeeId.do?employeeId="+row.id,
				  async: false //同步
				 }).responseText;
			 ids = $.parseJSON(ids);

			 //设置下拉框的值
			 $("#roles_combobox").combobox("setValues",ids);


			// 回显数据
			emp_form.form("load", row);

			// 打开弹出框
			emp_dialog.dialog("open");
			// 设置标题
			emp_dialog.dialog("setTitle", "编辑员工");

		},

		// 取消保存
		cancel : function() {
			// 关闭窗口
			emp_dialog.dialog("close");
		},

		// 保存
		save : function() {
			// 判断是否有id
			var url;
			if ($("[name='id']").val()) {
				url = "/employee_update.do";
			} else {
				url = "/employee_save.do";
			}

			// 提交表单
			emp_form.form('submit', {
				url : url,
				onSubmit : function(param) {
					//获取下拉框的值
					var ids = $("#roles_combobox").combobox("getValues");
					console.log(ids);
					//添加角色的参数
					for(var i=0;i<ids.length;i++){
						param["roles["+i+"].id"] = ids[i];
					}
				},
				success : function(data) {
					// 转成json对象
					data = $.parseJSON(data);
					if (data.success) {
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							// 关闭弹出框
							emp_dialog.dialog("close");
							// 刷新数据表格(保持在当前页)
							emp_datagrid.datagrid("reload");
						});
					} else {
						$.messager.alert('温馨提示', data.msg, 'info');
					}
				}
			});
		},

		// 设置为离职
		changeState : function() {
			// 判断是否选中数据
			var row = emp_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			// 弹出确认框
			$.messager.confirm('确认对话框', '您想要设置该员工为离职吗？', function(r) {
				if (r) {
					$.post("/employee_changeState.do", {
						id : row.id
					}, function(data) {
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							// 刷新数据表格(保持在当前页)
							emp_datagrid.datagrid("reload");
						});
					})
				}
			});

		},

		// 高级查询
		searchForm : function() {
			// 获取关键字文本框的值
			var keyword = $("[name='keyword']").val();

			emp_datagrid.datagrid('load', {
				keyword : keyword
			});
		},

		// 查询全部数据
		reload : function() {
			// 清空查询条件的内容
			$("[name='keyword']").val('');

			emp_datagrid.datagrid('load', {});
		},
		//导出文件
		exportFile:function(){
			window.location.href="/employee_export.do";
		},
		importFile:function(){
			$("#emp_file").dialog("open");
		}
		
	}

	//统一绑定事件
	$("a[data-cmd]").on("click",function(){
		//获取该链接需要执行的方法名字
		var methodName = $(this).data("cmd");
		//掉用方法
		methodObj[methodName]();
	})
	
	
	
})
