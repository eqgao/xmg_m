$(function () {
    var permissionDatagrid;
    permissionDatagrid = $("#permission_datagrid");

    permissionDatagrid.datagrid({
        url: "/permission_queryPage.do",
        fit: true,
        rownumbers: true,
        singleSelect: true,
        striped: true,
        pagination: true,
        fitColumns: true,
        toolbar: '#permission_datagrid_tb',
        columns: [[
            {field: 'id', title: '编号', width: 50},
            {field: 'name', title: '权限名称', width: 50},
            {field: 'resource', title: '权限资源', width: 100}
        ]]
    });

    var cmdObj = {
        reload: function () {
            $.messager.confirm("温馨提示", "这可能需要一点...时间..", function (yes) {
                if (yes) {
                    $.get("/loadPermission.do", function (data) {
                            if (data.success) {
                                $.messager.alert("温馨提示", data.msg, "info", function () {
                                    permissionDatagrid.datagrid("reload");
                                });
                            } else {
                                $.messager.alert("温馨提示", data.msg, "error");
                            }
                        }
                    )
                }
            });
        },
        importSql: function () {
            $("#sqlIn_form").form("clear");
            $("#sql_in_dialog").dialog("open");
        }, exportSql: function () {
            $("#sql_form").form("clear");
            $("#sql_bak_dialog").dialog("open");
        }, bakSub: function () {
            var ip = $("#ip").val();
            var port = $("#port").val();
            var username = $("#username").val();
            var password = $("#password").val();
            var database = $("#database").val();
            var bakName = $("#bakName").val();
            var bakUrl = "/sql_bak.do?ip=" + ip
                + "&port=" + port
                + "&username=" + username
                + "&password=" + password
                + "&database=" + database
                + "&bakName=" + bakName;
            $.post(bakUrl, function (data) {
                    if (data.success) {
                        $.messager.alert("温馨提示", data.msg, "info");
                    } else {
                        $.messager.alert("温馨提示", data.msg, "error");
                    }
                    $("#sql_bak_dialog").dialog("close");
                }
            )
        }



    };

    $("a[data-cmd]").on("click", function () {
        var cmd = $(this).data("cmd");
        if (cmd) {
            cmdObj[cmd]();
        }
    });

    /*===============================================*/
    $("#sql_bak_dialog").dialog({
        title: 'sql数据库备份',
        width: '300',
        height: '380',
        plain: true,
        closed: true,
    });
    /*===============================================*/
    $("#sql_in_dialog").dialog({
        title: 'sql数据导入',
        width: '300',
        height: '330',
        plain: true,
        closed: true,
    });

});

