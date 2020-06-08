<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/expend.js"></script>
    <style type="text/css">
        .body_padding {
            padding: 10px;
        }
    </style>
</head>
<body>
<table id="emp_datagrid"></table>

<div id="emp_dialog">
    <form id="emp_form" method="post">
        <input type="hidden" name="id"/>
        <table style="margin-top: 15px; margin: auto">
            <tbody>
            <tr>
                <td>事项:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="item"/>
                </td>
            </tr>
            <tr>
                <td>单据号:</td>
                <td>
                    <input class="easyui-numberbox" type="text" name="bills"/>
                </td>
                <td>合计 :</td>
                <td>
                    <input class="easyui-numberbox" type="text" name="amount"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="emp_tb">

    <a href="#" class="easyui-linkbutton" id="expend_tb_add"
       data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增支出</a>
    <a href="#" class="easyui-linkbutton" id="expend_tb_edit"
       data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">审核</a>
    <a href="#" class="easyui-linkbutton" id="expend_tb_del"
       data-options="iconCls:'icon-remove',plain:true" data-cmd="del">删除</a>
    <a href="#" class="easyui-linkbutton" id="expend_tb_reload"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <input id="ss" data-cmd="searchForm"/>
    <div id="mm" style="width:120px">
        <div data-options="name:'item'">事项</div>
        <div data-options="name:'bills'">单据号</div>
    </div>
    收款时间：
    <input type="text" class="easyui-datebox" name="startTime"/>~
    <input type="text" class="easyui-datebox" name="endTime"/>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>
    <select id="expend_name" name="updrade" class="easyui-combobox"
            data-options="width:100,panelHeight:'auto',cls:'expend_name'">
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