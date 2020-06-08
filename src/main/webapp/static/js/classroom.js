$(function() {
    // 抽取变量
    var room_datagrid = $("#room_datagrid");
    var room_dialog = $("#room_dialog");
    var room_form = $('#room_form');

    // 初始化数据表格
    room_datagrid.datagrid({
        url : "/classRoom_list.do",
        fitColumns : true,
        rownumbers : "true",
        striped : true,
        fit : true,
        pagination : true,
        singleSelect : true,
        toolbar : '#room_tb',
        columns : [ [ {
            field : 'name',
            title : '教室名称',
            width : 100
        }, {
            field : 'location',
            title : '教室位置',
            width : 100
        }, {
            field : 'num',
            title : '座位数',
            width : 100
        }, {
            field : 'status',
            title : '状态',
            width : 100,
            formatter : stateFormatter
        }] ]

    });

    room_dialog.dialog({
        width : 300,
        height : 250,
        buttons : '#room_btns',
        closed : true
    });

    // 状态格式化
    function stateFormatter(value, row, index) {
        return value ? "<font color='green'>启用</font>" : "<font color='red'>未启用</font>";
    }


    var obj = {
        name : 'will',
        showName : function() {
            alert(1)
        }
    }

    // 使用对象来统一管理方法
    var methodObj = {
        // 新增
        add : function() {
            // 清空表单
            room_form.form("clear");
            // 打开弹出框
            room_dialog.dialog("open");
            // 设置标题
            room_dialog.dialog("setTitle", "新增教室");
            //设置默认的状态
            $("[option=value]").val("false");
        },

        // 编辑
        edit : function() {
            // 判断是否选中数据
            var row = room_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            room_form.form("clear");

            // 回显状态
            row["status"] = row["status"] + "";

            // 回显数据
            room_form.form("load", row);

            // 打开弹出框
            room_dialog.dialog("open");
            // 设置标题
            room_dialog.dialog("setTitle", "教室编辑");

        },

        // 取消保存
        cancel : function() {
            // 关闭窗口
            room_dialog.dialog("close");
        },

        // 保存
        save : function() {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/classroom_update.do";
            } else {
                url = "/classroom_save.do";
            }
            // 提交表单
            room_form.form('submit', {
                url : url,
                success : function(data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function() {
                            // 关闭弹出框
                            room_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            room_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },

        // 删除教室
        del : function() {
            // 判断是否选中数据
            var row = room_datagrid.datagrid("getSelected");
            console.log(row.id);
            if (!row) {
                $.messager.alert('温馨提示', '请选中要删除哪一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要删除该教室吗？', function(r) {
                if (r) {
                    $.post("/classroom_delete.do", {
                        id : row.id
                    }, function(data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function() {
                            // 刷新数据表格(保持在当前页)
                            room_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },

        // 高级查询
        searchForm : function() {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();

            room_datagrid.datagrid('load', {
                keyword : keyword
            });
        },

        // 查询全部数据
        reload : function() {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            room_datagrid.datagrid('load', {});
        },
        //导出文件
        exportFile:function(){
            window.location.href="/classroom_export.do";
        },
        //导入文件
        importFile : function () {
            window.location.href="/classroom_import.do";
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
