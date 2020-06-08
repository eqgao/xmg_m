$(function () {
    $("#sign").bind('click', function () {


            $.post("/index_sign.do", function (data) {
                if (data) {
                    $.messager.alert('温馨提示', '签到成功！', 'info')
                }
            })
        }
    );
});








