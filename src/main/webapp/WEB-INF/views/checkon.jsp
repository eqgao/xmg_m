<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp"%>

    <script type="text/javascript" src="/static/js/checkon.js"></script>
</head>
<body>
<table id="checkon_datagrid"></table>

<div id="checkon_dialog">
    <form id="checkon_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>员工:</td>
                <td>
                    <input id="checkon_combobox" type="text" name="employee.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'realname',
							        url:'/employee_listAll.do',panelHeight:'auto'"/>
                </td>
            </tr>

            <tr>
                <td>考勤日期:</td>
                <td>
                    <input type="text" name="checkondate" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>签到时间:</td>
                <td>
                    <input type="text" name="signintime" class="easyui-datetimebox"/>
                </td>
            </tr>
            <tr>
                <td>签退时间:</td>
                <td>
                    <input type="text" name="signouttime" class="easyui-datetimebox"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="checkon_tb">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <!-- 关键字查询 -->
    <input type="text" name="keyword"/>
    <a href="#" class="easyui-linkbutton "
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>

</div>


</body>
</html>