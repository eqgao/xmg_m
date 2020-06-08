$(function () {
    // 抽取变量
    var checkon_datagrid = $("#checkon_datagrid");


    // 初始化数据表格
    checkon_datagrid.datagrid({
        url: "/checkon_listAll.do",
        fitColumns: true,
        striped: true,
        fit: true,
        pagination: true,

        singleSelect: true,
        toolbar: '#checkon_tb',
        columns: [[{
            field: 'id',
            title: '编号',
            width: 100
        }, {
            field: 'employee',
            title: '姓名',
            width: 100,
            formatter: employeeFormatter

        }, {
            field: 'checkondate',
            title: '考勤日期',
            width: 100

        }, {
            field: 'signintime',
            title: '签到时间(09:00:00)',
            width: 100

        }, {
            field: 'signouttime',
            title: '签退时间(17:30:00)',
            width: 100
        }, {
            field: 'status',
            title: '状态',
            width: 100,
            formatter: statusFormatter
        }]]

    });

    //获取员工的姓名
    function employeeFormatter(value, row, index) {

        return value ? value.realname : "";
    }

    function statusFormatter(value, row, index) {
        if (value == 1) {
            return "正常";
        } else if (value == 2) {
            return "异常";

        }
    }
    var methodObj = {
        // 高级查询
        searchForm: function () {
            // 获取关键字文本框的值
            var keyword = $("[name='keyword']").val();

            checkon_datagrid.datagrid('load', {
                keyword: keyword
            });
        },

        // 查询全部数据
        reload: function () {
            // 清空查询条件的内容
            $("[name='keyword']").val('');

            checkon_datagrid.datagrid('load', {});
        },
    };

    //统一绑定事件
    $("a[data-cmd]").on("click", function () {
        //获取该链接需要执行的方法名字
        var methodName = $(this).data("cmd");
        //掉用方法
        methodObj[methodName]();
    })


});
