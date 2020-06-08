$(function() {
	// 抽取变量
	var studentloss_datagrid = $("#studentloss_datagrid");
	var studentloss_dialog = $("#studentloss_dialog");
	// 初始化数据表格
	studentloss_datagrid.datagrid({
		url : "/studentloss_list.do",
		rownumbers: true,
		fitColumns : true,
		striped : true,
		fit : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#studentloss_tb',
		columns : [ [ {
			field : 'studentname',
			title : '学生姓名',
			width : 100
		}, {
			field : 'qq',
			title : 'QQ',
			width : 100
		}, {
			field : 'tel',
			title : '电话',
			width : 100
		}, {
			field : 'classinfoname',
			title : '流失班级',
			width : 100
		}, {
			field : 'losttime',
			title : '流失时间',
			width : 100
		}, {
			field : 'handler',
			title : '经办人',
			width : 100,
		}, {
			field : 'cause',
			title : '流失原因',
			width : 100,
		} ] ]

	});
	// 统一管理
	var methodObj = {
		// 删除函数
		del : function() {
			// 判断是否选中数据
			var row = studentloss_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			$.messager.confirm('确认对话框', '您想要删除该条数据吗？', function(r) {
				if (r) {
					$.post("/studentloss_delete.do", {
						id : row.id
					}, function(data) {
						$.messager.alert("温馨提示", data.msg, "info", function() {
							// 刷新数据表格(保持在当前页)
							studentloss_datagrid.datagrid("reload");
							// 关闭弹出框
							studentloss_dialog.dialog("close");
						});
					});
				}
			});

		},
		// 刷新函数
		// 查询全部数据
		reload : function() {
			// 刷新清空查询时的字段
			$("#keywordId").textbox("clear");
			// 时间清空
			$("#begin").datebox("clear");
			$("#end").datebox("clear");
			$("#classinfoName").combobox("clear");
			studentloss_datagrid.datagrid('load', {});
		},
		// 查询
		searchForm : function() {
			// 获取关键字文本框的值
			var keyword = $("[name='keyword']").val();
			studentloss_datagrid.datagrid('load', {
				keyword : keyword
			});
		},
		//流失班级查询
		searchFormByclassinfoId:function() {
			// 获取关键字文本框的值
			var classinfoName = $("#classinfoName").val();
			studentloss_datagrid.datagrid('load', {
				classinfoName : classinfoName
			});
		},
		// 高级查询
		searchFormBydate : function() {
			// 获取关键字文本框的值
			var begin = $("#begin").datebox('getValue');
			var end = $("#end").datebox('getValue');

			studentloss_datagrid.datagrid('load', {
				beginDate : begin,
				endDate : end
			});
		},
		// 导出的功能
		exportFile : function() {
			window.location.href = "/studentloss_export.do";
		}
	}

	// 统一绑定事件
	$("a[data-cmd]").on("click", function() {
		// 获取该链接需要执行的方法名字
		var methodName = $(this).data("cmd");
		// 调用方法
		methodObj[methodName]();
	})

})
