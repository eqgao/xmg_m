$(function () {
    // 抽取变量
    var potenStudent_datagrid = $("#potenStudent_datagrid");
    var potenStudent_dialog = $("#potenStudent_dialog");
    var potenStudent_form = $('#potenStudent_form');

    // 初始化数据表格
    potenStudent_datagrid.datagrid({
        url: "/potenStudent_list.do",
        fitColumns: true,
        striped: true,
        fit: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#potenStudent_tb',
        columns: [[
            {
                field: 'image',
                title: '头像',
                width: 100,
                formatter: function (value, row, index) {
                    if (row.studentInfo) {
                        if (row.studentInfo.image) {
                            return "<image src='" + row.studentInfo.image + "'  widht='50'  height='50'/>"
                        }
                    }
                }

            }, {
                field: 'name',
                title: '姓名',
                width: 100
            }, {
                field: 'qq',
                title: 'qq号',
                width: 100
            }, {
                field: 'tel',
                title: '电话',
                width: 100
            }, {
                field: 'employee',
                title: '咨询师',
                width: 100,
                formatter: function (value, row, index) {
                    if (value) {
                        return value.realname;
                    }
                }

            }, {
                field: 'lastTime',
                title: '最后约的时间',
                width: 100
            }, {
                field: 'nextTime',
                title: '下次约的时间',
                width: 100,
            }, {
                field: 'source',
                title: '来源',
                width: 100,
                formatter: formatter1,
            }, {
                field: 'level',
                title: '意向程度',
                width: 100,
                formatter: formatter2,
            }, {
                field: 'course',
                title: '意向学科',
                width: 100,
                formatter: function (value, row, index) {
                    if (value) {
                        return value.name;
                    }
                }
            }, {
                field: 'remark',
                title: '备注',
                width: 100,
            }, {
                field: 'status',
                title: '状态',
                width: 100,
                formatter: formatter3,
            },
        ]],
    });

    function formatter1(value, row, index) {
        if (value) {
            return value.name;
        }
    }

    function formatter2(value, row, index) {
        if (value.name == '厌恶') {
            return "<div style='color: red'>" + value.name + "</div>";
        } else if (value.name == '我要来,我要做李明杰') {
            return "<div style='color: blue'>" + value.name + "</span>";
        } else {
            return value.name;
        }

    }

    function formatter3(value, row, index) {
        if (value) {
            if (value.name == '转正' || value.name == '已入客户池') {
                return "<div style='color: blue'>" + value.name + "</span>";
            } else if (value.name == '流失') {
                return "<div style='color: red'>" + value.name + "</span>";
            } else {
                return value.name;

            }
        }
    }

    //潜在学员对话框
    potenStudent_dialog.dialog({
        width: 1000,
        height: 500,
        buttons: '#potenStudent_btns',
        closed: true
    });


    //考试表单
    $("#exam_dialog").dialog({
        width: 400,
        height: 300,
        closed: true,
    });

    var addressId;

    var methodObj = {
        // 新增
        add: function () {
            // 清空表单
            potenStudent_form.form("clear");
            $("#studentInfo_form").form("clear");
            // 打开弹出框
            potenStudent_dialog.dialog("open");
            // 设置标题
            potenStudent_dialog.dialog("setTitle", "添加潜在学员");
        },

        // 编辑
        edit: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            potenStudent_form.form("clear");
            $("#studentInfo_form").form("clear");

            //设置咨询师下拉列表
            if (row.employee) {
                row["employee.id"] = row["employee"].id;
            }

            if (row.source) {
                row["source.id"] = row["source"].id;
            }
            if (row.level) {
                row["level.id"] = row["level"].id;
            }
            if (row.status) {
                row["status.id"] = row["status"].id;
            }
            if (row.course) {
                row["course.id"] = row["course"].id;
            }

            // 回显数据
            potenStudent_form.form("load", row);


            $("#studentInfo_form").form("load", row.studentInfo);

            // 打开弹出框
            potenStudent_dialog.dialog("open");
            // 设置标题
            potenStudent_dialog.dialog("setTitle", "编辑潜在学员");

        },

        // 取消保存
        cancel: function () {
            // 关闭窗口
            potenStudent_dialog.dialog("close");
        },

        // 保存
        save: function () {
            // 判断是否有id
            var url;
            var infoUrl;
            if ($("[name='id']").val()) {
                url = "/potenStudent_update.do";
                infoUrl = "/studentInfo_update.do";
            } else {
                url = "/potenStudent_save.do";
                infoUrl = "/studentInfo_save.do";
            }


            // 提交信息表单
            $("#studentInfo_form").form('submit', {
                url: infoUrl,
                onSubmit: function (param) {
                },
                success: function (info) {
                    info = $.parseJSON(info)
                    // 提交表单
                    potenStudent_form.form('submit', {
                        url: url,
                        onSubmit: function (param) {
                            param.infoId = info.id;
                        },
                        success: function (data) {
                            // 转成json对象
                            data = $.parseJSON(data);
                            if (data.success) {
                                $.messager.alert('温馨提示', data.msg, 'info', function () {
                                    // 关闭弹出框
                                    potenStudent_dialog.dialog("close");
                                    // 刷新数据表格(保持在当前页)
                                    potenStudent_datagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert('温馨提示', data.msg, 'info');
                            }
                        }
                    });
                }
            });


        },

        // 设置为流失
        changeState: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要设置该学员为流失吗？', function (r) {
                if (r) {
                    $.post("/potenStudent_changeState.do", {
                        id: row.id
                    }, function (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 刷新数据表格(保持在当前页)
                            potenStudent_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },

        /*===============================================*/
        follow: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '结果会有不确定性,可能失去该学员,也可能获取该学员好感,您确定跟进吗？', function (r) {
                if (r) {
                    $.post("/potenStudent_follow.do", {
                        id: row.id
                    }, function (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 刷新数据表格(保持在当前页)
                            potenStudent_datagrid.datagrid("reload");
                        });
                    })
                }
            });

        },
        exam: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            var testUrl = "/test_selectTestByName.do?id=" + row.id
            $("#exam_datagrid").datagrid({
                url: testUrl,
                fitColumns: true,
                striped: true,
                fit: true,
                singleSelect: true,
                columns: [[{
                    field: 'sn',
                    title: '考试编号',
                    width: 100
                }, {
                    field: 'course',
                    title: '考试科目',
                    width: 100,
                    formatter: function (value, row, index) {
                        if (value) {
                            return value.name;
                        }
                    }
                }, {
                    field: 'testTime',
                    title: '考试时间',
                    width: 100
                }, {
                    field: 'testResult',
                    title: '考试结果',
                    width: 100,
                },
                ]],
            });
            $("#exam_dialog").dialog("setTitle", row.name + " >成绩单");
            $("#exam_dialog").dialog("open");
        },

        /*===============================================*/

        // 高级查询
        searchForm: function () {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();
            var statusId = $("#search_statusId").combobox("getValue");
            potenStudent_datagrid.datagrid('load', {
                keyword: keyword,
                statusId: statusId
            });
        },

        // 刷新
        reload: function () {
            // 清空查询条件的内容
            $("[name='keyword']").val('');
            $("#search_statusId").combobox("clear");
            potenStudent_datagrid.datagrid('load', {});
        },


        /*=======================================================*/
        // 取消保存函数
        student_cancel: function () {
            // 关闭窗口
            $("#student_dialog").dialog("close");
        },
        // 保存函数
        student_save: function () {
            var url = "/student_save.do";
            // 提交表单
            //保存的时候启用
            $("#name").textbox("enable");
            $("#tel").textbox("enable");
            $("#qq").textbox("enable");
            $("#employee_cbb").combobox("enable");
            $("#student_form").form('submit', {
                url: url,
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            $("#student_dialog").dialog("close");
                            potenStudent_datagrid.datagrid("reload");
                        });
                    }
                }
            });
        },
        formal: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要该学员转正吗？', function (r) {
                if (r) {
                    if (row.level.id != 9) {
                        $.messager.alert('I am sorry', '该学员意向程度不是满格,请攻略他', 'info');
                        return;
                    }
                    $.post("/test_selectByResult.do", {
                        id: row.id
                    }, function (data) {
                        var ii = -1;
                        $.each(data, function (i, n) {

                            if (row.studentInfo) {
                                row["studentInfo.id"] = row["studentInfo"].id;
                            }

                            $("#student_form").form("load", row);
                            $("#employee_cbb").combobox("setValue", row.employee.id);
                            $("#name").textbox("disable");
                            $("#tel").textbox("disable");
                            $("#qq").textbox("disable");
                            $("#employee_cbb").combobox("disable");
                            $("#classinfo_cbb").combobox("clear");


                            $("#student_dialog").dialog("open");
                            ii = i;
                        })
                        if (ii == -1) {
                            $.messager.alert('I am sorry', '该社员还没有进行考试,首先要考试合格', 'info');
                        }
                    })
                }
            });

        },
        /*======================移交学员=========================*/
        transferEmp: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要该学员移交出去吗？', function (r) {
                if (r) {
                    //数据回显
                    if (row.employee) {
                        $("#oldEmployee_cbb").combobox("setValue", row.employee.id);
                    }

                    var url = '/employee_listRemoveOwn.do?id=' + row.employee.id;
                    $('#newEmployee_cbb').combobox({
                        url: url,
                        valueField: 'id',
                        textField: 'realname',
                        panelHeight: 'auto'

                    });

                    $("#transfer_form").form("load", row);
                    $("#transfer_cause").textbox("clear");
                    $("#transfer_dialog").dialog("open");
                }
            });

        },
        // 取消保存函数
        transfer_cancel: function () {
            // 关闭窗口
            $("#transfer_dialog").dialog("close");
        },
        // 保存函数
        transfer_save: function () {
            var url = "/transfer_save.do";
            // 提交表单
            $("#transfer_form").form('submit', {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            $("#transfer_dialog").dialog("close");
                            potenStudent_datagrid.datagrid("reload");
                        });
                    }
                }
            });
        },
        /*=========================移入客户池==============================*/
        transferClient: function () {
            // 判断是否选中数据
            var row = potenStudent_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要把该学员丢进客户池吗？', function (r) {
                if (r) {
                    //回显数据
                    $("#client_form").form("load", row);
                    $("#client_cbb").combobox("setValue", row.employee.id);
                    $("#client_level").combobox("setValue", row.level.id);
                    $("#client_course").combobox("setValue", row.course.id);

                    //备注清除
                    $("#client_cause").textbox("clear");

                    //打开
                    $("#client_dialog").dialog("open");
                }
            });
        },
        // 取消保存函数
        client_cancel: function () {
            // 关闭窗口
            $("#client_dialog").dialog("close");
        },
        // 保存函数
        client_save: function () {

            //禁用的表单启用
            $("#client_name").textbox("enable")
            $("#client_tel").textbox("enable")
            $("#client_qq").textbox("enable")
            $("#client_cbb").combobox("enable")
            $("#client_level").combobox("enable")
            $("#client_course").combobox("enable")
            $("#client_cause").textbox("enable")


            var url = "/potenClient_insert.do";
            // 提交表单
            $("#client_form").form('submit', {
                url: url,
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            $("#client_dialog").dialog("close");
                            potenStudent_datagrid.datagrid("reload");
                        });
                    }
                }
            });
        },

        //导出
        export: function () {
            window.location.href = "/potenStudent_export.do";
        },
    }
    /*=======================================================*/

    //统一绑定事件
    $
    ("a[data-cmd]").on("click", function () {
        //获取该链接需要执行的方法名字
        var methodName = $(this).data("cmd");
        //掉用方法
        methodObj[methodName]();
    })


    /*================================*/
    $("#student_dialog").dialog({
        width: 300,
        height: 450,
        buttons: '#student_btns',
        closed: true,
        title: '潜在学员转正表',
    })
    /*=================================*/
    /*================================*/
    $("#transfer_dialog").dialog({
        width: 300,
        height: 420,
        buttons: '#transfer_btns',
        closed: true,
        title: '学员移交表',
    })
    /*=================================*/
    /*================================*/
    $("#client_dialog").dialog({
        width: 300,
        height: 420,
        buttons: '#client_btns',
        closed: true,
        title: '入客户池表',
    })
    /*=================================*/

    /*=====================下拉列表状态值改变查询===================================*/
    $("#search_statusId").combobox({
        onChange: function () {
            methodObj.searchForm();
        }
    })
    /*========================================================*/

    /*===========================================================*/

    var selectUrl;
    $("#address_max").combobox({
        valueField: 'id',
        textField: 'name',
        url: '/address_selectByCode.do',
        onSelect: function (rec) {
            console.log(rec);
            selectUrl = '/address_selectByCode.do?parentCode=' + rec.code;
            $('#address_pro').combobox('reload', selectUrl);
        }
    });
    $("#address_pro").combobox({
        valueField: 'id',
        textField: 'name',
        onSelect: function (rec) {
            selectUrl = '/address_selectByCode.do?parentCode=' + rec.code;
            $('#address_city').combobox('reload', selectUrl);
        }
    });

    $("#address_city").combobox({
        valueField: 'id',
        textField: 'name',
    })

})
