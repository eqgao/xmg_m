$(function() {
	// 抽取变量
	var role_datagrid = $("#role_datagrid");
	var role_dialog = $("#role_dialog");
	var role_form = $('#role_form');
	var selfPermissions = $("#selfPermissions");
	var allPermissions = $("#allPermissions");

	// 初始化数据表格
	role_datagrid.datagrid({
		url : "/role_list.do",
		fitColumns : true,
		striped : true,
		fit : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#role_tb',
		columns : [ [ {
			field : 'sn',
			title : '角色编号',
			width : 100
		}, {
			field : 'name',
			title : '角色名称',
			width : 100
		} ] ]
	});

	role_dialog.dialog({
		width : 600,
		height : 500,
		buttons : '#role_btns',
		closed : true
	});

	allPermissions.datagrid({
		width : 240,
		height : 350,
		title : "所有权限",
		url : '/permission_listAll.do',
		fitColumns : true,
		columns : [ [ {
			field : 'name',
			title : '权限名称',

			width : 100,
			align : 'center'
		} ] ],
		onDblClickRow : function(rowIndex, rowData) {
			//取消所有的选中后
			selfPermissions.datagrid("unselectAll");

			var rows = selfPermissions.datagrid("getRows");
			//获取已有权限的所有数据,获取每一行进行对比,看id是否一致
			for (var i = 0; i < rows.length; i++) {
				//如果已经存在已有权限中,就直接选中该数据
				if (rowData.id == rows[i].id) {
					//选中该行数据
					selfPermissions.datagrid("selectRow", i);
					return;
				}
			}
			//否则就添加当前行到已有权限中
			selfPermissions.datagrid("appendRow", rowData);
		}
	});
	selfPermissions.datagrid({
		width : 240,
		height : 350,
		fitColumns : true,
		title : "已有权限",
		columns : [ [ {
			field : 'name',
			title : '权限名称',
			width : 100,
			align : 'center'
		} ] ],
		onDblClickRow : function(rowIndex, rowData) {
			//删除改行数据
			selfPermissions.datagrid("deleteRow", rowIndex);
		}
	});

	// 使用对象来统一管理方法
	var methodObj = {
		// 新增
		add : function() {
			//清空已有权限的数据,加载本地数据
			selfPermissions.datagrid("loadData",{rows:[]});
			// 清空表单
			role_form.form("clear");
			// 打开弹出框
			role_dialog.dialog("open");
			// 设置标题
			role_dialog.dialog("setTitle", "新增员工");
		},

		// 编辑
		edit : function() {
			// 判断是否选中数据
			var row = role_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}

			// 清空表单
			role_form.form("clear");
			
			//获取datagrid的属性对象
			var options = selfPermissions.datagrid("options");
			console.log(options);
			
			//设置已有权限的url
			options.url = '/getPermissionsByRoleId.do?rid='+row.id;
			
			//重新加载数据
			selfPermissions.datagrid("reload");

			// 回显数据
			role_form.form("load", row);

			// 打开弹出框
			role_dialog.dialog("open");
			// 设置标题
			role_dialog.dialog("setTitle", "编辑角色");

		},

		// 取消保存
		cancel : function() {
			// 关闭窗口
			role_dialog.dialog("close");
		},

		// 保存
		save : function() {
			// 判断是否有id
			var url;
			if ($("[name='id']").val()) {
				url = "/role_update.do";
			} else {
				url = "/role_save.do";
			}

			// 提交表单
			role_form.form('submit', {
				url : url,
				onSubmit : function(param) {
					//获取已有权限的所有数据
					var rows = selfPermissions.datagrid("getRows");
					for (var i = 0; i < rows.length; i++) {
						//添加权限的参数
						param["permissions["+i+"].id"] = rows[i].id;
					}
					
				},

				success : function(data) {
					// 转成json对象
					data = $.parseJSON(data);
					if (data.success) {
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							// 关闭弹出框
							role_dialog.dialog("close");
							// 刷新数据表格(保持在当前页)
							role_datagrid.datagrid("reload");
						});
					} else {
						$.messager.alert('温馨提示', data.msg, 'info');
					}
				}
			});
		},

        // 删除
        del : function() {
            // 判断是否选中数据
            var row = role_datagrid.datagrid("getSelected");
            console.log(row.id);
            if (!row) {
                $.messager.alert('温馨提示', '请选中要删除哪一条数据!', 'role');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要删除该角色吗？', function(r) {
                if (r) {
                    $.post("/role_delete.do", {
                        id : row.id
                    }, function(data) {
                        $.messager.alert('温馨提示', data.msg, 'role', function() {
                            // 刷新数据表格(保持在当前页)
                            role_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },

		// 高级查询
		searchForm : function() {
			// 获取关键字文本框的值
			var keyword = $("[name='keyword']").val();

			role_datagrid.datagrid('load', {
				keyword : keyword
			});
		},

		// 查询全部数据
		reload : function() {
			// 清空查询条件的内容
			$("[name='keyword']").val('');

			role_datagrid.datagrid('load', {});
		}
	}

	//统一绑定事件
	$("a[data-cmd]").on("click", function() {
		//获取该链接需要执行的方法名字
		var methodName = $(this).data("cmd");
		//掉用方法
		methodObj[methodName]();
	})

})
