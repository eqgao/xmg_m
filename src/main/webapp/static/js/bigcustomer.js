$(function() {
    // 抽取变量
    var bigcustomer_datagrid = $("#bigcustomer_datagrid");
    var bigcustomer_dialog = $("#bigcustomer_dialog");
    var bigcustomer_form = $('#bigcustomer_form');

    // 初始化数据表格
    bigcustomer_datagrid.datagrid({
        url :"/bigcustomer_list.do",
        fitColumns : true,
        striped : true,
        fit : true,
        pagination : true,
        singleSelect : true,
        toolbar : '#bigcustomer_tb',
        columns : [ [ {
            field : 'school',
            title : '学校',
            width : 100
        }, {
            field : 'area',
            title : '区域',
            width : 100
        }, {
            field : 'creattime',
            title : '创建时间',
            width : 100
        }, {
            field : 'tel',
            title : '电话',
            width : 100
        }, {
            field : 'eamil',
            title : '邮箱',
            width : 100
        },{
            field : 'employee',
            title : '跟进人员',
            width : 100,
            formatter : employeeFormatter
        },{
            field : 'linkman',
            title : '联系人',
            width : 100
        },{
            field : 'tailafter',
            title : '跟进次数',
            width : 100
        },{
            field : 'agreeornot',
            title : '签约',
            width : 100,
            formatter : agreeOrnotFormatter
        },{
            field : 'starlevel',
            title : '意向星级',
            width : 100
        }
        ] ]
    });

 bigcustomer_dialog.dialog({
        width : 350,
        height : 420,
        buttons : '#bigcustomer_btns',
        closed : true
    });

    // 状态格式化
    function employeeFormatter(value, row, index) {
        //有值 返回员工的值
        if(value){
             return value.realname;
        }
    }

    function agreeOrnotFormatter(value, row, index) {
        //有值 返回员工的值
        if(value==1){
             return "<font color='red'>未签约</font>";
        }else {
            return "<font color='green'>已签约</font>";
        }
    }

    var obj = {
        name : 'will',
        showName : function() {
            alert(1)
        }
    }

    var methodObj = {
        // 新增
        add: function () {
            // 清空表单
            bigcustomer_form.form("clear");
            // 打开弹出框
            bigcustomer_dialog.dialog("open");
            // 设置标题
            bigcustomer_dialog.dialog("setTitle", "添加客户");
        },

        // 编辑
        edit: function () {
            // 判断是否选中数据
            var row = bigcustomer_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            bigcustomer_form.form("clear");

            //设置咨询师下拉列表
            if (row.employee) {
                row["employee.id"] = row["employee"].id;
            }

            // 回显数据
            bigcustomer_form.form("load", row);

            // 打开弹出框
            bigcustomer_dialog.dialog("open");
            // 设置标题
            bigcustomer_dialog.dialog("setTitle", "客户编辑");

        },

        // 取消保存
        cancel: function () {
            // 关闭窗口
            bigcustomer_dialog.dialog("close");
        },

        // 保存
        save: function () {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/bigcustomer_update.do";
            } else {
                url = "/bigcustomer_save.do";
            }

            // 提交表单
            bigcustomer_form.form('submit', {
                url: url,
                onSubmit: function (param) {
                },
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            bigcustomer_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            bigcustomer_datagrid.datagrid("reload");
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
            var row = bigcustomer_datagrid.datagrid("getSelected");
            console.log(row.id);
            if (!row) {
                $.messager.alert('温馨提示', '请选中要删除哪一条数据!', 'bigcustomer');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要删除该客户吗？', function(r) {
                if (r) {
                    $.post("/bigcustomer_delete.do", {
                        id : row.id
                    }, function(data) {
                        $.messager.alert('温馨提示', data.msg, 'bigcustomer', function() {
                            // 刷新数据表格(保持在当前页)
                            bigcustomer_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },
        // 高级查询
        searchForm : function() {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();
            var startTime = $("#date_startTime").datebox("getValue");
            var endTime = $("#date_endTime").datebox("getValue");
            bigcustomer_datagrid.datagrid('load', {
                keyword : keyword,
                startTime : startTime,
                endTime : endTime
            });
        },

        // 查询全部数据
        reload : function() {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            bigcustomer_datagrid.datagrid('load', {});
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
