$(function() {
	// 抽取变量
	var test_datagrid = $("#test_datagrid");
	var test_dialog = $("#test_dialog");
	var test_form = $('#test_form');

	// 初始化数据表格
	test_datagrid.datagrid({
		url : "/test_list.do",
		fitColumns : true,
		striped : true,
		fit : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#test_tb',
		columns : [ [ {
			field : 'sn',
			title : '考试编号',
			width : 100
		}, {
			field : 'course',
			title : '考试科目',
			width : 100,
			formatter : courseFormatter
		}, {
			field : 'name',
			title : '学员名称',
			width : 100,
			formatter : nameFormatter
		}, {
			field : 'qq',
			title : 'QQ',
			width : 100,
			formatter : qqFormatter
		}, {
			field : 'tel',
			title : '电话',
			width : 100,
			formatter : telFormatter
		}, {
			field : 'testTime',
			title : '考试时间',
			width : 100,
		}, {
			field : 'testResult',
			title : '考试结果',
			width : 100,
			formatter : testResultFormatter
		} ] ]
	});

	test_dialog.dialog({
		width : 300,
		height : 350,
		buttons : '#test_btns',
		closed : true
	});

	// 员工格式化
	 function courseFormatter(value, row, index) {
	 return row.course ? row.course.name : "";
	 }
	// qq格式化
	function qqFormatter(value, row, index) {
		return row.potenStudent ? row.potenStudent.qq : "";
	}
	// tel格式化
	function telFormatter(value, row, index) {
		return row.potenStudent ? row.potenStudent.tel : "";
	}
	// name格式化
	function nameFormatter(value, row, index) {
		return row.potenStudent ? row.potenStudent.name : "";
	}

	 function testResultFormatter(value, row, index) {
	        if (value < 60 ) {
	            return "<font color='red'>"+value+"</font>";
	        } else if (value < 85) {
	            return value;
	        } else {
	            return "<font color='green'>"+value+"</font>";
	        }
	    }

	
	var methodObj = {
		// 新增
		add : function() {
			// 清空表单
			test_form.form("clear");
			// 打开弹出框
			test_dialog.dialog("open");
			// 设置标题
			test_dialog.dialog("setTitle", "添加潜在学员");
		},

		// 编辑
		edit : function() {
			// 判断是否选中数据
			var row = test_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}

			// 清空表单
			test_form.form("clear");

			// 设置咨询师下拉列表
			 if (row.potenStudent) {
			 row["potenStudent.id"] = row["potenStudent"].id;
			 }
			 // 设置学科下拉列表
			 if (row.course) {
				 row["course.id"] = row["course"].id;
			 }

			// 回显数据
			test_form.form("load", row);

			// 打开弹出框
			test_dialog.dialog("open");
			// 设置标题
			test_dialog.dialog("setTitle", "编辑潜在学员");

		},

		// 取消保存
		cancel : function() {
			// 关闭窗口
			test_dialog.dialog("close");
		},

		// 保存
		save : function() {
			// 判断是否有id
			var url;
			if ($("[name='id']").val()) {
				url = "/test_update.do";
			} else {
				url = "/test_save.do";
			}

			// 提交表单
			test_form.form('submit', {
				url : url,
				onSubmit : function(param) {
					// 获取下拉框的值
					// var ids = $("#test_combobox").combobox("getValues");
				},
				success : function(data) {
					// 转成json对象
					data = $.parseJSON(data);
					if (data.success) {
						$.messager.alert('温馨提示', data.msg, 'info', function() {
							// 关闭弹出框
							test_dialog.dialog("close");
							// 刷新数据表格(保持在当前页)
							test_datagrid.datagrid("reload");
						});
					} else {
						$.messager.alert('温馨提示', data.msg, 'info');
					}
				}
			});
		},

		// 查询全部数据
		reload : function() {
			
			// 清空查询条件的内容 $("[name='keyword']").val('');
			$("#keyword").textbox("clear");
			$("#beginTime").textbox("clear");
			$("#endTime").textbox("clear");
			$("#minResult").textbox("clear");
			$("#maxResult").textbox("clear");
			
			test_datagrid.datagrid('load', {});
			 
			//test_datagrid.datagrid('reload');

		},
		searchForm : function() {
			// 获取关键字
			var keyword = $("[name='keyword']").val();
			var beginTime = $("[name='beginTime']").val();
			var endTime = $("[name='endTime']").val();
			var minResult = $("[name='minResult']").val();
			var maxResult = $("[name='maxResult']").val();
			test_datagrid.datagrid("load", {
				keyword : keyword,
				beginTime:beginTime,
				endTime:endTime,
				minResult:minResult,
				maxResult:maxResult
			});
		},
		exportFile:function(){
			window.location.href="/test_export.do";
		},
		importFile:function(){
			$("#test_file").dialog("open");
		}
	}

	// 统一绑定事件
	$("a[data-cmd]").on("click", function() {
		// 获取该链接需要执行的方法名字
		var methodName = $(this).data("cmd");
		// 掉用方法
		methodObj[methodName]();
	})

})
