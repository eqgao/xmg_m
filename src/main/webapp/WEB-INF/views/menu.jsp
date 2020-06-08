<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/menu.js"></script>
</head>
<body>
<table id="menu_datagrid"></table>

<div id="menu_dialog">
    <form id="menu_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id" />
            <tbody>
            <tr>
                <td>菜单名称:</td>
                <td>
                    <input class="easyui-textbox" style="width:100%;height:25px" type="text" name="text"/>
                </td>
            </tr>
            <tr>
                <td>上级菜单:</td>
                <td>
                    <input type="text" name="parent.id" class="easyui-combobox" style="width:100%;height:25px" id="parent_combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'text',
							        url: '/menu_listParent.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>菜单url:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="url"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="menu_tb">
    <!-- 使用shiro标签控制按钮显示 -->
    <shiro:hasPermission name="classinfo:save">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="classinfo:update">
        <a href="#" class="easyui-linkbutton" id="emp_tb_edit"
           data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-remove',plain:true" id="emp_tb_cs"
       data-cmd="del">删除</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
    <!-- 关键字查询 -->
    关键字
    <input type="text" class="textbox" name="keyword"/>
    </select>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
</div>

<div id="menu_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
</body>
</html>