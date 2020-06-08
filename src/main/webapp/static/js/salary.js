$(function() {
    // 抽取变量
    var salary_datagrid = $("#salary_datagrid");
    var salary_dialog = $("#salary_dialog");
    var salary_form = $('#salary_form');

    // 初始化数据表格
    salary_datagrid.datagrid({
        url : "/salary_listAll.do",
        fitColumns : true,
        striped : true,
        fit : true,
        pagination : true,

        singleSelect : true,
        toolbar : '#salary_tb',
        columns : [ [ {
            field : 'id',
            title : '编号',
            width : 100
        }, {
            field : 'employee',
            title : '姓名',
            width : 100,
            formatter : employeeFormatter

        },{
            field : 'card',
            title : '卡号',
            width : 100
        },{
            field : 'salary',
            title : '工资',
            width : 100,
            formatter : salaryFormatter
        },{
            field : 'status',
            title : '状态',
            width : 100,
            formatter : statusFormatter

        },{
            field : 'remark',
            title : '备注',
            width : 100
        } ] ]

    });
    //工资
    function salaryFormatter(value,row,index) {
        return value ? value : "null";
    }
    //获取员工的姓名
    function employeeFormatter(value, row, index) {

        return value ? value.realname : "";
    }
    function statusFormatter(value, row, index) {
        if(value==true){
            return "已发";
        }else {
            return "未发";
        }
    }
    salary_dialog.dialog({
        width : 400,
        height : 400,
        buttons : '#salary_btns',
        closed : true
    });
    var methodObj = {
        // 新增
        add : function() {
            // 显示密码输入框
            $("#pwdInput").show();
            // 清空表单
            salary_form.form("clear");
            // 打开弹出框
            salary_dialog.dialog("open");
            // 设置标题
            salary_dialog.dialog("setTitle", "新增员工");
        },

        // 编辑
        edit : function() {
            // 判断是否选中数据
            var row = salary_datagrid.datagrid("getSelected");
            if (!row) {
                $.messager.alert('温馨提示', '请选中一条数据!', 'info');
                return;
            }
            // 隐藏密码输入框
            $("#pwdInput").hide();



            // 清空表单
            salary_form.form("clear");
            if(row["employee"]){
                row["employee.id"] = row["employee"].id;

            }

            // 回显数据
            salary_form.form("load", row);

            // 打开弹出框
            salary_dialog.dialog("open");
            // 设置标题
            salary_dialog.dialog("setTitle", "编辑员工");

        },

        // 取消保存
        cancel : function() {
            // 关闭窗口
            salary_dialog.dialog("close");
        },

        // 保存
        save : function() {
            // 判断是否有id
            var url;
            if ($("[name='id']").val()) {
                url = "/salary_update.do";
            } else {
                url = "/salary_save.do";
            }

            // 提交表单
            salary_form.form('submit', {
                url : url,
               /* onSubmit : function(param) {
                    //获取下拉框的值
                    var ids = $("#salary_combobox").combobox("getValues");
                    console.log(ids);
                    //添加角色的参数
                    for(var i=0;i<ids.length;i++){
                        param["salary["+i+"].id"] = ids[i];
                    }
                },*/
                success : function(data) {
                    // 转成json对象
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', data.msg, 'info', function() {
                            // 关闭弹出框
                            salary_dialog.dialog("close");
                            // 刷新数据表格(保持在当前页)
                            salary_datagrid.datagrid("reload");
                        });
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'info');
                    }
                }
            });
        },

        // 高级查询
        searchForm : function() {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();

            salary_datagrid.datagrid('load', {
                keyword : keyword
            });
        },

        // 查询全部数据
        reload : function() {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            salary_datagrid.datagrid('load', {});
        }



    };

    //统一绑定事件
    $("a[data-cmd]").on("click",function(){
        //获取该链接需要执行的方法名字
        var methodName = $(this).data("cmd");
        //掉用方法
        methodObj[methodName]();
    })


});
