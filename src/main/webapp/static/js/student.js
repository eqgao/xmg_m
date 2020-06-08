$(function () {
    // 抽取变量
    var student_datagrid = $("#student_datagrid");
    var student_dialog = $("#student_dialog");
    var student_form = $('#student_form');
    // 初始化数据表格
    student_datagrid.datagrid({
        url: "/student_list.do",
        fitColumns: true,
        rownumbers: true,
        striped: true,
        fit: true,
        pagination: true,
        singleSelect: true,
        toolbar: '#student_tb',
        columns: [[{
            field: 'name',
            title: '学生姓名',
            width: 100
        }, {
            field: 'qq',
            title: 'QQ',
            width: 100
        }, {
            field: 'tel',
            title: '电话',
            width: 100
        }, {
            field: 'employee',
            title: '销售人员',
            width: 100,
            formatter: employeeFormatter
        }, {
            field: 'totalMoney',
            title: '总学费',
            width: 100,
            formatter: feestotalFormatter
        }, {
            field: 'tuition',
            title: '缴费金额',
            width: 100
        }, {
            field: 'paytime',
            title: '缴费时间',
            width: 100
        }, {
            field: 'status',
            title: '状态',
            width: 100,
            formatter: statusFormatter
        }, {
            field: 'entrancetime',
            title: '入学时间',
            width: 100,
        }, {
            field: 'feesstatus',
            title: '缴费状态',
            width: 100,
            formatter: feesstatusFormatter
        }, {
            field: 'classinfo',
            title: '所在班级',
            width: 100,
            formatter: Formatter
        }, {
            field: 'course',
            title: '课程',
            width: 100,
            formatter: courseFormatter
        }]],
        onClickRow: function (rowIndex, rowData) {
            // 非正式学员不能毕业也不能就业
            $("#dropout_tb").linkbutton('enable');
            $("#fees_tb").linkbutton('enable');
            $("#graduationion_tb").linkbutton('enable');
            $("#graduation_tb").linkbutton('enable');
            if (rowData.status == 0) {
                $("#graduationion_tb").linkbutton('disable');
                $("#graduation_tb").linkbutton('disable');
                return;
            }
            // 正式员工禁用缴费按钮
            if (rowData.status == 1) {
                $("#fees_tb").linkbutton('disable');
                return;
            }
            // 如果当前行的状态是已退学,则禁用退学,,缴费按钮毕业,就业按钮
            if (rowData.status == 2) {
                // 禁用按钮
                $("#dropout_tb").linkbutton('disable');
                $("#fees_tb").linkbutton('disable');
                $("#graduationion_tb").linkbutton('disable');
                $("#graduation_tb").linkbutton('disable');
                return;
            }
            // 毕业以后禁用按钮
            if (rowData.status == 3) {
                // 禁用所以按钮
                $("#dropout_tb").linkbutton('disable');
                $("#fees_tb").linkbutton('disable');
                $("#graduationion_tb").linkbutton('disable');
                return;
            }
            // 就业以后所有按钮
            if (rowData.status == 4) {
                // 禁用所以按钮
                $("#dropout_tb").linkbutton('disable');
                $("#fees_tb").linkbutton('disable');
                $("#graduationion_tb").linkbutton('disable');
                $("#graduation_tb").linkbutton('disable');
            }
            // 如果当前学费已经缴清了就把缴费按钮禁用
            if (rowData.feesstatus == 1) {
                // 禁用按钮
                $("#fees_tb").linkbutton('disable');
                return;
            }
        }
    });
    student_dialog.dialog({
        width: 500,
        height: 400,
        buttons: '#student_btns',
        closed: true
    })
    // 缴费弹出框
    $("#studentredeipt_dialog").dialog({
        width: 300,
        height: 450,
        buttons: '#studentredeipt_btns',
        closed: true
    })
    // 转班弹出框
    $("#changeClassinfo_dialog").dialog({
        width: 250,
        height: 300,
        buttons: '#changeClassinfo_btns',
        closed: true
    })
    // 就业信息编辑弹出框
    $("#studentjob_dialog").dialog({
        width: 500,
        height: 450,
        buttons: '#studentjob_btns',
        closed: true
    })

    // 员工格式化
    function employeeFormatter(value, row, index) {
        return value ? value.realname : "";
    }

    // 总学费格式化
    function feestotalFormatter(value, row, index) {
        if (row.classinfo) {
            return row.classinfo ? row.classinfo.totalMoney : "";
        }
    }

    // 班级格式化
    function Formatter(value, row, index) {
        if (value) {
            return value ? value.name : "";
        }
    }

    // 课程格式化
    function courseFormatter(value, row, index) {
        return value ? value.name : "";
    }

    // 状态格式化
    function statusFormatter(value, row, index) {
        if (value == 1) {
            return "正式学员";
        } else if (value == 0) {
            return "<font color='red'>非正式学员</font>";
        } else if (value == 2) {
            return "<font color='green'>已退学</font>";
        } else if (value == 3) {
            return "<font color='yellow'>已毕业</font>";
        } else {
            return "<font color='violet'>已就业</font>";
        }
    }

    // 缴费状态格式化
    function feesstatusFormatter(value, row, index) {
        if (value == 1) {
            return "已缴清";
        } else if (value == 0) {
            return "<font color='red'>未缴清</font>";
        } else {
            return "<font color='green'>待审核</font>";
        }
    }

    // 学员状态高级
    $("#statusSearch").combobox({
        onSelect: function (record) {
            student_datagrid.datagrid("load", {
                status: record.value
            });
        }
    });
    // 缴费状态高级查询
    $("#feesstatusSearch").combobox({
        onSelect: function (record) {
            student_datagrid.datagrid("load", {
                feesstatus: record.value
            });
        }
    });

    // 富文本


    // 统一管理
    var methodObj = {
        toSystemMenber: function () {
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            if (row.studentId){
                $.messager.alert('温馨提示', '该社员已经是系统社员!', 'info');
                return;
            }
            $.messager.alert('温馨提示', "你确定把该社员设为系统社员吗", 'info', function () {
                $.ajax({
                    url: "/student_toSystemMenber.do",
                    data: {
                        studentId: row.id
                    },
                    success: function (data) {
                        data = eval("(" + data + ")");
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            $("#remark_div").dialog("close");
                            student_datagrid.datagrid("reload")
                        });
                    }
                });
            });

        },
        cancel_edit: function () {
            $("#remark_div").dialog("close");
        },
        send_edit: function () {
            var row = student_datagrid.datagrid("getSelected");
            $('#remark_form').form('submit', {
                url: "/student_remark.do",
                onSubmit: function (param) {
                    param.id = row.id;
                },
                success: function (data) {
                    data = eval("(" + data + ")");
                    $.messager.alert('温馨提示', data.msg, 'info', function () {
                        $("#remark_div").dialog("close");
                        student_datagrid.datagrid("reload")
                    });
                }
            });
        },

        remark: function () {
            // 判断是否选中数据
            console.debug("----------------");
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 打开弹出框
            var ue = UE.getEditor('container', {
                autoHeightEnabled: true,
                autoFloatEnabled: true,
                initialFrameHeight: 600,
                imagePathFormat: "controller.jsp/{yyyy}{mm}{dd}/{time}{rand:6}",
                imageUrlPrefix: "http://localhost/controller.jsp"
            });
            ue.ready(function (editor) {
                ue.setContent(row.remark);
            });
            // console.debug(row);
            $("#remark_div").dialog("open");
            // 设置标题
        },

        // 编辑函数
        edit: function () {
            // 判断是否选中数据
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 清空表单
            student_form.form("clear");
            // 回显数据
            student_form.form("load", row);
            // 打开弹出框
            student_dialog.dialog("open");
            // 设置标题
            student_dialog.dialog("setTitle", "编辑学生");
        },
        // 取消保存函数
        cancel: function () {
            // 关闭窗口
            student_dialog.dialog("close");
        },
        // 保存函数
        save: function () {
            var url = "/student_update.do";
            // 提交表单
            student_form.form('submit', {
                url: url,
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            student_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            student_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },

        // 刷新函数
        // 查询全部数据
        reload: function () {
            // 刷新清空查询时的字段
            $("#keywordId").textbox("clear");
            $("#begin").datebox("clear");
            $("#end").datebox("clear");
            $("#classinfoId").combobox("clear");
            student_datagrid.datagrid('load', {});
        },

        // ----------------------------------------------------------------------------------------------
        // 毕业函数
        graduation: function () {
            // 判断是否选中
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选择所需要的数据');
                return;
            }
            // 清空表单
            $("#studentjob_form").form("clear");
            // 回显数据
            $("#studentjob_form").form("load", row);
            // 打开弹出框
            $("#studentjob_dialog").dialog("open");
            // 设置标题
            $("#studentjob_dialog").dialog("setTitle", "学生就业信息统计");
        },
        // 毕业编辑保存
        studentjob_save: function () {
            var url = "/student_graduation.do";
            // 提交表单
            $("#studentjob_form").form('submit', {
                url: url,
                // 发送额外参数
                onSubmit: function (param) {
                    var row = student_datagrid.datagrid("getSelected");
                    param.sid = row.id;
                },
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            $("#studentjob_dialog").dialog("close");
                            // 刷新数据表格(保持在当前页)
                            student_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },
        // 毕业编辑取消
        studentjob_cancel: function () {
            // 关闭窗口
            $("#studentjob_dialog").dialog("close");
        },
        // 毕业函数
        graduationion: function () {
            // 判断是否选中
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选择所需要的数据');
                return;
            }
            if (row.feesstatus != 1) {
                alert("该学员学费未缴清或未通过审核,没有达到毕业条件");
            } else {
                $.messager.confirm('确认对话框', '该学员达到毕业条件,可以毕业!', function (r) {
                    if (r) {
                        $.post("/changestatus.do", {
                            id: row.id,
                            cause: r
                        }, function (data) {
                            $.messager.alert("温馨提示", data.msg, "info",
                                function () {
                                    // 刷新数据表格(保持在当前页)
                                    student_datagrid.datagrid("reload");
                                });
                        })
                    }
                })
            }

        },
        // 退学函数
        dropout: function () {
            // 判断是否选中
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选择所需要的数据');
                return;
            }

            // 弹出确认框
            $.messager
                .confirm(
                    '确认对话框',
                    '您确定该学生要退学吗？',
                    function (r) {
                        if (r) {
                            $.messager
                                .prompt(
                                    '提示信息',
                                    '请输入退学原因：',
                                    function (r) {
                                        if (r) {
                                            alert('该学生的退学原因是：'
                                                + r);
                                            $
                                                .post(
                                                    "/studentloss_loss.do",
                                                    {
                                                        id: row.id,
                                                        cause: r
                                                    },
                                                    function (data) {
                                                        $.messager
                                                            .alert(
                                                                "温馨提示",
                                                                data.msg,
                                                                "info",
                                                                function () {
                                                                    // 关闭弹出框
                                                                    student_dialog
                                                                        .dialog("close");
                                                                    // 刷新数据表格(保持在当前页)
                                                                    student_datagrid
                                                                        .datagrid("reload");
                                                                });
                                                    })
                                        }

                                    })
                        }
                    });
        },
        // 提交缴费单据函数
        save_redeipt: function () {
            var url = "/studentfees_insert.do";
            // 提交表单
            $("#studentredeipt_form").form('submit', {
                url: url,
                // 添加额外的参数
                onSubmit: function (param) {
                    // 判断是否选中
                    var row = student_datagrid.datagrid("getSelected");
                    if (!row) {
                        $.messager.alert('温馨提示', '请选择所需要的数据');
                        return;
                    }
                    param.Sid = row.id;
                },
                success: function (data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function () {
                            // 关闭弹出框
                            $("#studentredeipt_dialog").dialog("close");
                            // 刷新数据表格(保持在当前页)
                            student_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },
        // 取消提交函数
        cancel_redeipt: function () {
            $("#studentredeipt_dialog").dialog("close");
        },
        // 已经缴费函数
        fees: function () {
            // 判断是否选中
            var row = student_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选择所需要的数据');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您确定要执行缴费操作吗？', function (r) {
                if (r) {
                    $("#studentredeipt_dialog").dialog("open");
                    $("#studentredeipt_dialog").dialog("setTitle", "缴费编辑");
                    // 清空表单
                    $("#studentredeipt_form").form("clear");
                }
            });
        },
        /*// 查看详细详细
         detailed_information : function() {
         student_dialog.dialog("open");
         student_dialog.find("input").attr("style",
         "border:none;background-color:transparent");
         },*/

        // 关键字查询
        searchForm: function () {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();

            student_datagrid.datagrid('load', {
                keyword: keyword
            });
        },
        // 班级查询
        searchFormByclassinfoId: function () {
            // 获取关键字文本框的值
            var classinfoId = $("#classinfoId").val();
            student_datagrid.datagrid('load', {
                classinfoId: classinfoId
            });
        },
        // 高级查询
        searchFormBydate: function () {
            // 获取关键字文本框的值
            var begin = $("#begin").datebox('getValue');
            var end = $("#end").datebox('getValue');
            student_datagrid.datagrid('load', {
                beginDate: begin,
                endDate: end
            });
        },
        // 导出的功能
        exportFile: function () {
            window.location.href = "/student_export.do";
        }
    }
    // 统一绑定事件
    $("a[data-cmd]").on("click", function () {
        // 获取该链接需要执行的方法名字
        var methodName = $(this).data("cmd");
        // 调用方法
        methodObj[methodName]();
    })
    // 城市编辑
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
