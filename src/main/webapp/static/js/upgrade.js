$(function () {
    // 抽取变量
    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    var emp_form = $('#emp_form');

    // 初始化数据表格
    emp_datagrid.datagrid({
        url: "/upgrade_list.do",
        fitColumns: true,
        striped: true,
        fit: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#emp_tb',
        columns: [[{
            field: 'id',
            title: '编号',
            width: 100
        }, {
            field: 'name',
            title: '姓名',
            width: 100,
            formatter: function (value, row, index) {
                if (row.student) {
                    return row.student.name;
                }
            }
        }, {
            field: 'qq',
            title: 'QQ',
            width: 100,
            formatter: function (value, row, index) {
                if (row.student) {
                    return row.student.qq;
                }
            }
        }, {
            field: 'tel',
            title: '电话',
            width: 100,
            formatter: function (value, row, index) {
                if (row.student) {
                    return row.student.tel;
                }
            }
        }, {
            field: 'emp',
            title: '营销人员',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    return value.realname;
                }
            }
        }, {
            field: 'upgradetime',
            title: '升班/留级时间',
            width: 100
        }, {
            field: 'classinfo',
            title: '流入班级',
            width: 100,
            formatter: function (value, row, index) {
                if (value) {
                    return value.name;
                }
            }
        }, {
            field: 'status',
            title: '升级/留级',
            width: 100,
            formatter: function (value, row, index) {
                return value == 1 ? "升级" : "<font color='red'>留级</font>";
            }
        }]],
        onClickRow: function (rowIndex, rowData) {
            // 如果当前行的状态是离职,就禁用编辑和离职按钮
            if (!rowData.state) {
                // 禁用按钮
                $("#emp_tb_cs").linkbutton('disable');
                $("#emp_tb_edit").linkbutton('disable');
            } else {
                // 显示按钮
                $("#emp_tb_cs").linkbutton('enable');
                $("#emp_tb_edit").linkbutton('enable');
            }
        }
    });

    $("#toclass").combobox({
        url: 'upgrade_listClass.do',
        width: 150,
        valueField: 'id',
        textField: 'name',
        panelHeight: "auto"
    });

    $('#ss').searchbox({
        searcher: function (value, name) {
            var param = {};
            param[name] = value;
            emp_datagrid.datagrid('load', param);
        },
        menu: '#mm',
        prompt: '请输入关键字',
        width: 300
    });

    $("#upgrade_name").combobox({
        width: 100,
        panelHeight: 'auto',
        onSelect: function (record) {
            console.debug(record);
            emp_datagrid.datagrid('load', {
                status: record.value
            });

        }
    });


    emp_dialog.dialog({
        width: 600,
        height: 400,

        buttons: '#emp_btns',
        closed: true
    });

    // 部门格式化
    function deptFormatter(value, row, index) {
        return value ? value.name : "";
    }

    // 获取部门的其他数据,但是field不能设置重复,我们就通过row来获取
    /*
     * function deptFormatter1(value,row,index){ return row.dept?row.dept.id:""; }
     */
    // 状态格式化
    function stateFormatter(value, row, index) {
        return value ? "在职" : "<font color='red'>离职</font>";
    }

    // 是否管理员格式化
    function adminFormatter(value, row, index) {
        return value ? "是" : "否";
    }

    var obj = {
        name: 'will',
        showName: function () {
            alert(1)
        }
    }


    // 调用对象的方法的几种方式
    /*	obj.showName();
     obj["showName"]();
     var temp = "showName";
     obj[temp]();*/

    // 使用对象来统一管理方法
    var methodObj = {

        del: function () {
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 提交表单
            emp_form.form('submit', {
                url: "/upgrade_del",
                onSubmit: function (param) {
                },
                success: function (data) {
                    data = eval("(" + data + ")");
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            emp_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            emp_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });

        },

        // 编辑
        edit: function () {
            // 判断是否选中数据
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            emp_form.form("clear");

            // 是否管理员下拉框回显处理,设置为字符串类型
            if (row["student"]) {
                row["student.name"] = row.student.name;
            }
            if (row["student"]) {
                row["student.tel"] = row.student.tel;
            }
            if (row["student"]) {
                row["student.qq"] = row.student.qq;
            }
            if (row["emp"]) {
                row["emp.realname"] = row.emp.realname;
            }

            // 部门下拉框处理(同名匹配原则)
            if (row["classinfo"]) {
                row["classinfo.id"] = row.classinfo.id;
            }

            //获取该员工拥有的角色数据
            /*	$.post("/getRolesIdByEmployeeId.do",{employeeId:row.id},function(data){
             $("#roles_combobox").combobox("setValues",data);
             })
             */
            // 回显数据
            emp_form.form("load", row);

            // 打开弹出框
            emp_dialog.dialog("open");
            // 设置标题
            emp_dialog.dialog("setTitle", "编辑");

        },

        // 取消保存
        cancel: function () {
            // 关闭窗口
            emp_dialog.dialog("close");
        },

        // 保存
        save: function () {
            // 判断是否有id
            var url = "/upgrade_update.do";

            // 提交表单
            emp_form.form('submit', {
                url: url,
                onSubmit: function (param) {
                },
                success: function (data) {
                    data = eval("(" + data + ")");
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            emp_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            emp_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },

        // 设置为离职
        changeState: function () {
            // 判断是否选中数据
            var row = emp_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要设置该员工为离职吗？', function (r) {
                if (r) {
                    $.post("/employee_changeState.do", {
                        id: row.id
                    }, function (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 刷新数据表格(保持在当前页)
                            emp_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },

        // 高级查询
        searchForm: function () {
            // 获取关键字文本框的值
            var startTime = $("[name='startTime']").val();
            var endTime = $("[name='endTime']").val();
            var param = {};
            if (startTime) {
                param['startTime'] = startTime;
            }
            if (endTime) {
                param['endTime'] = endTime;
            }
            emp_datagrid.datagrid('load', param);
        },

        // 查询全部数据
        reload: function () {
            // 清空查询条件的内容
            $('#ss').searchbox("setValue","");

            emp_datagrid.datagrid('load', {});
        },
        //导出文件
        exportFile: function () {
            window.location.href = "/upgrade_export.do";
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
