$(function () {
    // 抽取变量
    var info_datagrid = $("#info_datagrid");
    var info_dialog = $("#info_dialog");
    var info_form = $('#info_form');

    // 初始化数据表格
    info_datagrid.datagrid({
        url: "/classinfo_list.do",
        fitColumns: true,
        rownumbers : "true",
        striped: true,
        fit: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#info_tb',
        columns: [[{
            field: 'name',
            title: '班级名称',
            width: 100
        }, {
            field: 'sn',
            title: '编号',
            width: 100
        }, {
            field: 'begintime',
            title: '开班时间',
            width: 100
        }, {
            field: 'employee',
            title: '班主任',
            width: 100,
            formatter: employeeFormatter
        }, {
            field: 'totalMoney',
            title: '总学费',
            width: 100
        }, {
            field: 'remark',
            title: '备注',
            width: 100
        }, {
            field: 'classRoom',
            title: '教室',
            width: 100,
            formatter: classRoomFormatter
        }, {
            field: 'status',
            title: '状态',
            width: 100,
            formatter: stateFormatter
        }]]

    });

    info_dialog.dialog({
        width: 300,
        height: 350,
        buttons: '#info_btns',
        closed: true
    });

    // 状态格式化
    function stateFormatter(value, row, index) {
        if (value == 1) {
            return "<font color='DarkGray'>基础班</font>";
        } else if (value == 2) {
            return "<font color='green'>大神班</font>";
        } else {
            return "<font color='#ffd700'>超神班</font>";
        }
    }

    //教室数据格式化
    function classRoomFormatter(value, row, index) {
        console.log(value)
        return value ? value.name:"";
    }

    //班主任据格式化
    function employeeFormatter(value, row, index) {
        return value ? value.realname : "";
    }

    var obj = {
        name: 'will',
        showName: function () {
            alert(1)
        }
    }

    // 使用对象来统一管理方法
    var methodObj = {
        // 新增
        add: function () {
            // 清空表单
            info_form.form("clear");
            // 打开弹出框
            info_dialog.dialog("open");
            // 设置标题
            info_dialog.dialog("setTitle", "新增班级");
        },

        // 编辑
        edit: function () {
            // 判断是否选中数据
            var row = info_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            console.log(row);
            // 清空表单
            info_form.form("clear");
            //获取教师的名字
            var realName = row.employee.realname;
            row['employee.id'] = row.employee.id;

            var roomname = row.classRoom.name;
            row['classRoom.id'] = row.classRoom.id;

            // 回显数据
            info_form.form("load", row);

            // 打开弹出框
            info_dialog.dialog("open");
            // 设置标题
            info_dialog.dialog("setTitle", "班级编辑");

        },

        // 取消保存
        cancel: function () {
            // 关闭窗口
            info_dialog.dialog("close");
        },

        // 保存
        save: function () {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/classinfo_update.do";
            } else {
                url = "/classinfo_save.do";
            }
            // 提交表单
            info_form.form('submit', {
                url: url,
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            info_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            info_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },

        // 删除教室
        del: function () {
            // 判断是否选中数据
            var row = info_datagrid.datagrid("getSelected");
            console.log(row.id);
            if (!row) {
                $.messager.alert('温馨提示', '请选中要删除哪一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要删除该教室吗？', function (r) {
                if (r) {
                    $.post("/classinfo_delete.do", {
                        id: row.id
                    }, function (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 刷新数据表格(保持在当前页)
                            info_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },

        // 高级查询
        searchForm: function () {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();
            var startTime = $("#date_startTime").datebox("getValue");
            var endTime = $("#date_endTime").datebox("getValue");
            info_datagrid.datagrid('load', {
                keyword: keyword,
                startTime: startTime,
                endTime: endTime
            });
        },

        // 查询全部数据
        reload: function () {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            info_datagrid.datagrid('load', {});
        },
        //导出文件
        exportFile: function () {
            window.location.href = "/classinfo_export.do";
        }
    }

    //统一绑定事件
    $("a[data-cmd]").on("click", function () {
        //获取该链接需要执行的方法名字
        var methodName = $(this).data("cmd");
        //掉用方法
        methodObj[methodName]();
    })
})
