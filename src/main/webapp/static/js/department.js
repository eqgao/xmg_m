$(function() {
	// 抽取变量
	var dept_datagrid = $("#dept_datagrid");
	var dept_dialog = $("#dept_dialog");
	var dept_form = $('#dept_form');

	// 初始化数据表格
	dept_datagrid.datagrid({
		url : "/department_list.do",
		fitColumns : true,
		striped : true,
		fit : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#dept_tb',
		columns : [ [ {
			field : 'sn',
			title : '部门编码',
			width : 100
		}, {
			field : 'name',
			title : '部门名称',
			width : 100
		}, {
			field : 'manager',
			title : '部门经理',
			width : 100,
			formatter : employeeFormatter
		}, {
			field : 'parent',
			title : '上级部门',
			width : 100,
			formatter : deptFormatter
		}, {
			field : 'state',
			title : '部门状态',
			width : 100,
			formatter : stateFormatter
		} ] ],
		onClickRow : function(rowIndex, rowData) {
			// 如果当前行的部门已经取消,就禁用取消部门按钮
			if (!rowData.state) {
				// 禁用按钮
				$("#dept_tb_cs").linkbutton('disable');
			} else {
				// 显示按钮
				$("#dept_tb_cs").linkbutton('enable');
			}
		}
	});

	dept_dialog.dialog({
		width : 300,
		height : 350,
		buttons : '#dept_btns',
		closed : true
	});

	// 部门格式化
	function deptFormatter(value, row, index) {
		return row.parent ? row.parent.name : "";
	}

	// 获取部门的其他数据,但是field不能设置重复,我们就通过row来获取

	function employeeFormatter(value, row, index) {
		return row.manager ? row.manager.realname : "";
	}

	// 状态格式化
	function stateFormatter(value, row, index) {
		return value ? "正常" : "<font color='red'>已取消</font>";
	}

	// 使用对象来统一管理方法
	var methodObj = {
		// 新增
		add : function() {
			// 清空表单
			dept_form.form("clear");
			// 打开弹出框
			dept_dialog.dialog("open");
			// 设置标题
			dept_dialog.dialog("setTitle", "新增员工");
		},

		// 编辑
		edit : function() {
			// 判断是否选中数据
			var row = dept_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			// 清空表单
			dept_form.form("clear");

			// 是否管理员下拉框回显处理,设置为字符串类型
			// if (!row["state"]) {
			// row["state"] = row["state"] + "";

			// 部门下拉框处理(同名匹配原则)
			if (row["parent"]) {
				row["parent.id"] = row["parent"].id;
			}
			// 经理下拉框处理(同名匹配原则)
			if (row["manager"]) {
				row["manager.id"] = row["manager"].id;
			}

			// 回显数据
			dept_form.form("load", row);

			// 打开弹出框
			dept_dialog.dialog("open");
			// 设置标题
			if (!row["state"]) {
				$.messager.confirm('确认对话框', '您想要重新启用该部门么？', function(r) {
					if (r) {
						dept_dialog.dialog("setTitle", "重新启用部门");
					}else{
						dept_dialog.dialog("close");
					}
				});
			} else {
				dept_dialog.dialog("setTitle", "编辑部门");
			}
		},

		// 取消保存
		cancel : function() {
			// 关闭窗口
			dept_dialog.dialog("close");
		},

		// 保存
		save : function() {
			// 判断是否有id
			var url;
			if ($("[name='id']").val()) {
				url = "/department_update.do";
			} else {
				$("#cc").hide();
				url = "/department_save.do";
			}

			$("#state").hide();
			// 提交表单
			dept_form.form('submit', {
				url : url,
				onSubmit : function(param) {
					// 获取下拉框的值
					var manager = $("#manager_combobox").combobox("getValues");
					var parent = $("#parent_combobox").combobox("getValues");

				},
				success : function(data) {
					// 转成json对象
					data = $.parseJSON(data);
					if (data.success) {
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							// 关闭弹出框
							dept_dialog.dialog("close");
							// 刷新数据表格(保持在当前页)
							dept_datagrid.datagrid("reload");
						});
					} else {
						$.messager.alert('温馨提示', data.msg, 'info');
					}
				}
			});
		},

		// 取消部门
		changeState : function() {
			// 判断是否选中数据
			var row = dept_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			// 弹出确认框
			$.messager.confirm('确认对话框', '您确定要取消该部门么？', function(r) {
				if (r) {
					$.post("/department_changeState.do", {
						id : row.id
					}, function(data) {
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							// 刷新数据表格(保持在当前页)
							dept_datagrid.datagrid("reload");
						});
					})
				}
			});

		},

		// 刷新数据
		reload : function() {
			dept_datagrid.datagrid('load', {});
		},

	}

	// 统一绑定事件
	$("a[data-cmd]").on("click", function() {
		// 获取该链接需要执行的方法名字
		var methodName = $(this).data("cmd");
		// 掉用方法
		methodObj[methodName]();
	})

})
