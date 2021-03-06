<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/receipt.js"></script>
</head>
<body>
<table id="emp_datagrid"></table>

<div id="emp_dialog">
    <form id="emp_form" method="post">
        <input type="hidden" name="id"/>
        <table style="margin-top: 15px; margin: auto">
            <tbody>
            <tr>
                <td>学员姓名 :</td>
                <td>
                    <input class="easyui-textbox" data-options="disabled:true" type="text" name="student.name"/>
                </td>
                <td>学科 :</td>
                <td>
                    <input class="easyui-textbox" data-options="disabled:true" type="text" name="student.name"/>
                </td>
            </tr>
            <tr>
                <td>班级:</td>
                <td>
                    <input class="easyui-numberbox" data-options="disabled:true" type="text" name="student.qq"/>
                </td>
                <td>收款时间 :</td>
                <td>
                    <input class="easyui-datebox" type="text" name="receipttime"/>
                </td>
            </tr>
            <tr>
                <td>收款金额:</td>
                <td>
                    <select class="easyui-combobox" data-options="width:100,panelHeight:'auto'" name="status">
                        <option value="0">留级</option>
                        <option value="1">升级</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>收款人 :</td>
                <td>
                    <input class="easyui-textbox" type="text" data-options="disabled:true" name="emp.realname"/>
                </td>
                <td>单据号 :</td>
                <td>
                    <input class="easyui-textbox" type="text" data-options="disabled:true" name="emp.realname"/>
                </td>
            </tr>
            <tr>
                <td>收据 :</td>
                <td>
                    <input class="easyui-textbox" type="text" data-options="disabled:true" name="emp.realname"/>
                </td>
                <td>销售人员 :</td>
                <td>
                    <input class="easyui-textbox" type="text" data-options="disabled:true" name="emp.realname"/>
                </td>
            </tr>
            <tr>
                <td>备注 :</td>
                <td colspan="3">
                    <input class="easyui-textbox" type="text" data-options="disabled:true" name="emp.realname"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="emp_tb">
    <a href="#" class="easyui-linkbutton" id="receipt_tb_auditor"
       data-options="iconCls:'icon-edit',plain:true" data-cmd="auditor">审核</a>
    <a href="#" class="easyui-linkbutton" id="receipt_tb_reload"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <input id="ss" data-cmd="searchForm"/>
    <div id="mm" style="width:120px">
        <div data-options="name:'stuName'">学生姓名</div>
        <div data-options="name:'auditorName'">收款人</div>
    </div>
    收款时间：
    <input type="text" class="easyui-datebox" name="startTime"/>~
    <input type="text" class="easyui-datebox" name="endTime"/>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>
    <select id="receipt_name" name="status" class="easyui-combobox"
            data-options="width:100,panelHeight:'auto',cls:'receipt_name'">
        <option value="-1">全部</option>
        <option value="1">已审核</option>
        <option value="0">未审核</option>
    </select>
</div>

<div id="emp_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>

<%--<div id="emp_file" class="easyui-dialog" data-options="">
    <form action="/employee_import.do" method="post" enctype="multipart/form-data">
        文件:<input type="file" name="file"><br>
        <input type="submit" value="提交">
    </form>
</div>--%>

</body>
</html>