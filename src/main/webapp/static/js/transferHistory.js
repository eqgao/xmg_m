$(function () {
    // 抽取变量
    var transferHistory_datagrid = $("#transferHistory_datagrid");
    var transferHistory_dialog = $("#transferHistory_dialog");
    var transferHistory_form = $('#transferHistory_form');

    // 初始化数据表格
    transferHistory_datagrid.datagrid({
        url: "/transferHistory_list.do",
        striped: true,
        fit: true,
        fitColumns: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#transferHistory_tb',
        columns: [[{
            field: 'name',
            title: '姓名',
            width: 100,
            formatter: formatter1,
        }, {
            field: 'qq',
            title: 'qq号',
            width: 100,
            formatter: formatter2
        }, {
            field: 'tel',
            title: '电话',
            width: 100,
            formatter: formatter3
        }, {
            field: 'oldEmployee',
            title: '原负责人',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    return value.realname;
                }
            }

        }, {
            field: 'level',
            title: '意向程度',
            width: 100,
            formatter: formatter4
        }, {
            field: 'transTime',
            title: '移交日期',
            width: 100,
        }, {
            field: 'cause',
            title: '原因',
            width: 100,
        }, {
            field: 'newEmployee',
            title: '新负责人',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    return value.realname;
                }
            }
        },
        ]],
    });

    function formatter1(value, row, index) {
        if (row.potenStudent) {
            return row.potenStudent.name;
        }
    }

    function formatter2(value, row, index) {
        if (row.potenStudent) {
            return row.potenStudent.qq;
        }
    }

    function formatter3(value, row, index) {
        if (row.potenStudent) {
            return row.potenStudent.tel;
        }
    }

    function formatter4(value, row, index) {
        if (row.potenStudent) {
            return row.potenStudent.level.name;
        }
    }

    transferHistory_dialog.dialog({
        width: 400,
        height: 400,
        buttons: '#transferHistory_btns',
        closed: true
    });


    var methodObj = {

        // 查看
        edit: function () {
            // 判断是否选中数据
            var row = transferHistory_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            transferHistory_form.form("clear");


            // 回显数据
            //姓名设置
            console.log(row);
            row["oldEmployee.realname"] = row["oldEmployee"].realname;
            row["newEmployee.realname"] = row["newEmployee"].realname;
            row["potenStudent.name"] = row["potenStudent"].name;

            //设置图片
            $("#image_note").attr("src", row.imgUrl);

            //负责人设置
            transferHistory_form.form("load", row);
            // 打开弹出框
            transferHistory_dialog.dialog("open");
            // 设置标题
            transferHistory_dialog.dialog("setTitle", "查看聊天记录");

        },
        note: function () {
            $("#note_dialog").dialog({
                width: 600,
                height: 600,
                closed: true,
                title: '聊天记录'
            });
            $("#note_dialog").dialog("open");
        },
        // 取消
        cancel: function () {
            // 关闭窗口
            transferHistory_dialog.dialog("close");
        },
        // 刷新
        reload: function () {
            transferHistory_datagrid.datagrid('reload');
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
