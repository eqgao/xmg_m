$(function() {
	// 抽取变量
	var potenClient_datagrid = $("#potenClient_datagrid");
	var potenClient_dialog = $("#potenClient_dialog");
	var potenClient_form = $('#potenClient_form');

	// 初始化数据表格
	potenClient_datagrid.datagrid({
		url : "/potenClient_listAll.do",
		fitColumns : true,
		striped : true,
		fit : true,
		pagination : true,
		singleSelect : true,
		toolbar : '#potenClient_tb',
		columns : [ [ {
			field : 'name',
			title : '姓名',
			width : 100,
			formatter :tnameFormatter
		}, {
			field : 'qq',
			title : 'qq号',
			width : 100,
			formatter :qqFormatter
		}, {
			field : 'tel',
			title : '电话',
			width : 100,
			formatter :telFormatter
		}, {
			field : 'employee',
			title : '营销人员',
			width : 100,
			formatter :nameFormatter

		}, {
			field : 'putTime',
			title : '放入时间',
			width : 100
		}, {
			field : 'level',
			title : '意向程度',
			width : 100,
			formatter : levelFormatter
		}, {
			field : 'course',
			title : '意向课程',
			width : 100,
			formatter : courseFormatter
		},  {
			field : 'status',
			title : '状态',
			width : 100,
			formatter : statusFormatter
		},{
			field : 'remark',
			title : '备注',
			width : 100,
		} ] ],
	});

	// qq格式化
	function tnameFormatter(value, row, index) {
		return row.potenStudent ? row.potenStudent.name : "";
	}
	// tel格式化
	function qqFormatter(value, row, index) {
		return row.potenStudent ? row.potenStudent.qq : "";
	}
	function telFormatter(value, row, index) {
		return row.potenStudent ? row.potenStudent.tel : "";
	}
	// name格式化
	function nameFormatter(value, row, index) {
		return row.employee ? row.employee.realname : "";
	}
	// level格式化
	function levelFormatter(value, row, index) {
		return row.potenStudent.level ? row.potenStudent.level.name : "";
	}
	function courseFormatter(value, row, index) {
		return row.potenStudent.course ? row.potenStudent.course.name : "";
	}
	function statusFormatter(value, row, index) {
		return row.potenStudent.status ? row.potenStudent.status.name : "";
	}
	// remark格式化
//	function remarkFormatter(value, row, index) {
//		return row.potenStudent ? row.potenStudent.remark : "";
//	}

	var methodObj = {

		/* =============================================== */
		formal : function() {
			// 判断是否选中数据
			var row = potenClient_datagrid.datagrid("getSelected");
			if (!row) {
				$.messager.alert('温馨提示', '请选中一条数据!', 'info');
				return;
			}
			// 弹出确认框
			$.messager.confirm('确认对话框', '您想要证明自己吗？那就搞定他!!!', function(r) {
				if (r) {
					$.post("/potenClient_change.do", {
						id : row.id
					}, function(data) {
						$.messager.alert("温馨提示:", data.msg, "info", function() {
							potenClient_datagrid.datagrid("reload");
						})
					})
				}
			});

		},
		/* =============================================== */

		// 高级查询
		searchForm : function() {
			// 获取关键字文本框的值
			var id = potenClient_datagrid.datagrid("getSelected");
			var keyword = $("[name='keyword']").val();
			potenClient_datagrid.datagrid('load', {
				keyword : keyword,
				id:id
			});
		},

		// 刷新
		reload : function() {
			// 清空查询条件的内容(第一种方法因为input框有class属性所以清不掉)
			//$("[name='keyword']").val('');
			$("#keyword").textbox("clear");
			potenClient_datagrid.datagrid('load', {});
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
