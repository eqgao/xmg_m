<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp"%>

    <script type="text/javascript" src="/static/js/salary.js"></script>
</head>
<body>
<table id="salary_datagrid"></table>

<div id="salary_dialog">
    <form id="salary_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>员工:</td>
                <td>
                    <input id="salary_combobox" type="text" name="employee.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'realname',
							        url:'/employee_listAll.do',panelHeight:'auto'"/>
                </td>
            </tr>

            <tr>
                <td>卡号:</td>
                <td>
                    <input type="text" name="card" class="easyui-textbox"/>
                </td>
            </tr>
            <tr>
                <td>工资:</td>
                <td>
                    <input type="text" name="salary" class="easyui-textbox"/>
                </td>
            </tr>

            <tr>
                <td>备注:</td>
                <td>
                    <input type="text" name="remark" class="easyui-textbox"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="salary_tb">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a href="#" class="easyui-linkbutton" id="role_tb_edit"
       data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>

    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
    <!-- 关键字查询 -->
    <input type="text" name="keyword"/>
    <a href="#" class="easyui-linkbutton "
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>

</div>

<div id="salary_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


</body>
</html>