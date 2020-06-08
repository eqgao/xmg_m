$(function() {
    // 抽取变量
    var course_datagrid = $("#course_datagrid");
    var course_dialog = $("#course_dialog");
    var course_form = $('#course_form');

    // 初始化数据表格
    course_datagrid.datagrid({
        url : "/course_list.do",
        fitColumns : true,
        rownumbers : "true",
        striped : true,
        fit : true,
        pagination : true,
        singleSelect : true,
        toolbar : '#course_tb',
        columns : [ [ {
            field : 'classinfo',
            title : '班级名称',
            formatter : classinfoFormatter,
            width : 100
        }, {
            field : 'schooltime',
            title : '上课时间',
            width : 100
        }, {
            field : 'name',
            title : '课程名称',
            width : 100
        }, {
            field : 'teacher',
            title : '班主任',
            formatter : teacherFormatter,
            width : 100
        },{
            field : 'lecturer',
            title : '讲师',
            formatter : lecturerFormatter,
            width : 100
        },{
            field : 'classRoom',
            title : '教室',
            formatter : classRoomFormatter,
            width : 100
        },{
            field : 'remark',
            title : '备注',
            width : 100
        },{
            field : 'status',
            title : '状态',
            width : 100,
            formatter : stateFormatter
        }] ]

    });

    course_dialog.dialog({
        width : 400,
        height : 360,
        buttons : '#course_btns',
        closed : true
    });

    // 状态格式化
    function stateFormatter(value, row, index) {
       if (value==1){
           return "<font color='green'>已上</font>";
       }else {
           return "<font color='red'>未上</font>";
       }
    }

    //班级数据格式化
    function classinfoFormatter(value, row, index) {
        return value?value.name:"";
    }

    //讲师数据格式化
    function lecturerFormatter(value, row, index) {
        return value?value.realname:"";
    }

    //教室的数据格式化
    function classRoomFormatter(value, row, index) {
        return value?value.name:"";
    }


    //班主任数据格式化
    function teacherFormatter(value, row, index) {
        return value?value.realname:"";
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
            course_form.form("clear");
            // 打开弹出框
            course_dialog.dialog("open");
            // 设置标题
            course_dialog.dialog("setTitle", "新增课程");
        },

        // 编辑
        edit : function() {
            // 判断是否选中数据
            var row = course_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }

            // 清空表单
            course_form.form("clear");
            //获取班主任的名字
            var name = row.teacher.realname;
            row['teacher.id'] = row.teacher.id;

            //获取讲师的名字
            var lname = row.lecturer.realname;
            row['lecturer.id'] = row.lecturer.id;

            //获取教室的名字
            var infoname = row.classinfo.name;
            row['classinfo.id'] = row.classinfo.id;


            ////获取教室的名字
            var roomname = row.classRoom.name;
            row['classRoom.id'] = row.classRoom.id;

            // 回显数据
            course_form.form("load", row);

            // 打开弹出框
            course_dialog.dialog("open");
            // 设置标题
            course_dialog.dialog("setTitle", "班级编辑");

        },

        // 取消保存
        cancel : function() {
            // 关闭窗口
            course_dialog.dialog("close");
        },

        // 保存
        save : function() {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/course_update.do";
            } else {
                url = "/course_save.do";
            }
            // 提交表单
            course_form.form('submit', {
                url : url,
                success : function(data) {
                    // 转成json对象
                     data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function() {
                            // 关闭弹出框
                            course_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            course_datagrid.datagrid("reload");
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
            var row = course_datagrid.datagrid("getSelected");
            console.log(row.id);
            if (!row) {
                $.messager.alert('温馨提示', '请选中要删除哪一条数据!', 'info');
                return;
            }
            // 弹出确认框
            $.messager.confirm('确认对话框', '您想要删除该教室吗？', function(r) {
                if (r) {
                    $.post("/course_delete.do", {
                        id : row.id
                    }, function(data) {
                        $.messager.alert('温馨提示', data.msg, 'info', function() {
                            // 刷新数据表格(保持在当前页)
                            course_datagrid.datagrid("reload");
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
            course_datagrid.datagrid('load', {
                keyword : keyword,
                startTime : startTime,
                endTime : endTime
            });
        },

        // 查询全部数据
        reload : function() {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            course_datagrid.datagrid('load', {});
        },
        //导出功能
        exportFile : function () {
            window.location.href = "/course_export.do";
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
