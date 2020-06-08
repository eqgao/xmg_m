$(function () {
    // 抽取变量
    var dictionaryItem_datagrid = $("#dictionaryItem_datagrid");
    var dictionaryItem_dialog = $("#dictionaryItem_dialog");
    var dictionaryItem_form = $('#dictionaryItem_form');
    var itemUrl;
    var details_id;

    //加载页面的时候把明细按钮隐藏
    $("#dictionaryItem_tb").hide();

    // 初始化数据表格
    $("#dictionary_datagrid").datagrid({
        url: "/dictionary_listAll.do",
        fitColumns: true,
        striped: true,
        fit: true,
        singleSelect: true,
        columns: [[{
            field: 'sn',
            title: '字典目录编码',
            width: 100
        }, {
            field: 'name',
            title: '字典目录名称',
            width: 100
        }, {
            field: 'intro',
            title: '字典目录介绍',
            width: 100
        },
        ]],
        onClickRow: function (rowIndex, rowData) {
            $("#dictionaryItem_tb").show();
            // 初始化数据表格
            details_id = rowData.id;
            itemUrl = "/dictionaryItem_list.do?details_id=" + details_id;
            $("#dictionary_Item_dialog").dialog("open");
            dictionaryItem_datagrid.datagrid({
                url: itemUrl,
                fitColumns: true,
                striped: true,
                fit: true,
                singleSelect: true,
                toolbar: '#dictionaryItem_tb',
                columns: [[{
                    field: 'sn',
                    title: '字典明细编码',
                    width: 100
                }, {
                    field: 'name',
                    title: '字典明细名称',
                    width: 100
                }, {
                    field: 'intro',
                    title: '字典明细介绍',
                    width: 100
                }, {
                    field: 'details',
                    title: '字典目录',
                    width: 100,
                    formatter: function (value, row, index) {
                        if (value) {
                            return value.name;
                        }
                    }

                },
                ]],
            });
        }
    });

    dictionaryItem_dialog.dialog({
        width: 300,
        height: 300,
        buttons: '#dictionaryItem_btns',
        closed: true
    });


    var methodObj = {
        // 新增
        add: function () {
            // 清空表单
            dictionaryItem_form.form("clear");


            $("#did").combobox("disable");
            $("#did").combobox("setValue", details_id);
            $("#hiId").val(details_id);

            // 打开弹出框
            dictionaryItem_dialog.dialog("open");
            // 设置标题
            dictionaryItem_dialog.dialog("setTitle", "添加字典明细");


        },

        // 编辑
        edit: function () {
            // 判断是否选中数据
            var row = dictionaryItem_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            dictionaryItem_form.form("clear");

            console.log(row);
            //设置字典目录值
            row["details.id"] = row["details"].id;
            row["details.name"] = row["details"].name;
            $("#hiId").val(details_id);
            // 回显数据
            dictionaryItem_form.form("load", row);

            //编辑隐藏字典目录栏,防止修改
            $("#did").combobox("disable");

            // 打开弹出框
            dictionaryItem_dialog.dialog("open");
            // 设置标题
            dictionaryItem_dialog.dialog("setTitle", "编辑字典明细");


        },

        // 取消保存
        cancel: function () {
            // 关闭窗口
            dictionaryItem_dialog.dialog("close");
        },

        // 保存
        save: function () {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/dictionaryItem_update.do";
            } else {
                url = "/dictionaryItem_save.do";
            }

            // 提交表单
            dictionaryItem_form.form('submit', {
                url: url,
                onSubmit: function (param) {
                },
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            dictionaryItem_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            dictionaryItem_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },

        // 删除
        changeState: function () {
            // 判断是否选中数据
            var row = dictionaryItem_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您确定删除该明细吗？', function (r) {
                if (r) {
                    $.post("/dictionaryItem_changeState.do", {
                        id: row.id
                    }, function (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 刷新数据表格(保持在当前页)
                            $("#dictionaryItem_datagrid").datagrid("reload");
                        });
                    })
                }
            });

        },


        // 刷新
        reload: function () {
            /*// 清空查询条件的内容
             $("[name='keyword']").val('');

             dictionaryItem_datagrid.datagrid('load', {});*/
            dictionaryItem_datagrid.datagrid('reload');
        },
    }

    //统一绑定事件
    $("a[data-cmd]").on("click", function () {
        //获取该链接需要执行的方法名字
        var methodName = $(this).data("cmd");
        //掉用方法
        methodObj[methodName]();
    })


})
