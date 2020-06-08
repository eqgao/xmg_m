$(function () {
    // 抽取变量
    var menu_datagrid = $("#menu_datagrid");
    var menu_dialog = $("#menu_dialog");
    var menu_form = $('#menu_form');

    // 初始化数据表格
    menu_datagrid.datagrid({
        url: "/menu_listAll.do",
        fitColumns: true,
        rownumbers:"true",
        striped: true,
        fit: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#menu_tb',
        columns: [[{
            field: 'text',
            title: '菜单名称    ',
            width: 100
        }, {
            field: 'parent',
            title: '上级菜单',
            width: 100,
            formatter: parentFormatter
        }, {
            field: 'url',
            title: 'url',
            width: 100
        }]]

    });

    menu_dialog.dialog({
        width: 250,
        height: 250,
        buttons: '#menu_btns',
        closed: true
    });


    //父级菜单数据格式化
    function parentFormatter(value, row, index) {
        if (row.parent){
            return row.parent.text;
        }else {
            return "<font color='red'>顶级菜单</font>";
        }
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
            menu_form.form("clear");
            // 打开弹出框
            menu_dialog.dialog("open");
            // 设置标题
            menu_dialog.dialog("setTitle", "新增菜单");
        },

        // 编辑
        edit: function () {
            // 判断是否选中数据
            var row = menu_datagrid.datagrid("getSelected");
            console.log(row)
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 清空表单
            menu_form.form("clear");

            if (row["parent"]) {
                row["parent.id"] = row["parent"].id;
            }
            // 回显数据
            menu_form.form("load", row);

            // 打开弹出框
            menu_dialog.dialog("open");
            // 设置标题
            menu_dialog.dialog("setTitle", "菜单编辑");

        },

        // 取消保存
        cancel: function () {
            // 关闭窗口
            menu_dialog.dialog("close");
        },

        // 保存
        save: function () {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/menu_update.do";
            } else {
                url = "/menu_save.do";
            }
            // 提交表单
            menu_form.form('submit', {
                url: url,
                onSubmit : function(param) {
                    // 获取下拉框的值
                    var parent = $("#parent_combobox").combobox("getValues");
                },
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            menu_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            menu_datagrid.datagrid("reload");
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
            var row = menu_datagrid.datagrid("getSelected");
            console.log(row.id);
            if (!row) {
                $.messager.alert('温馨提示', '请选中要删除哪一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要删除该菜单吗？', function (r) {
                if (r) {
                    $.post("/menu_delete.do", {
                        id: row.id
                    }, function (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 刷新数据表格(保持在当前页)
                            menu_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },

        // 高级查询
        searchForm : function() {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();

            menu_datagrid.datagrid('load', {
                keyword : keyword
            });
        },

        // 查询全部数据
        reload: function () {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            menu_datagrid.datagrid('load', {});
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
