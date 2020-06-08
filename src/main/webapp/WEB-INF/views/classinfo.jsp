<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/classinfo.js"></script>
</head>
<body>
<table id="info_datagrid"></table>

<div id="info_dialog">
    <form id="info_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id"/>
            <tbody>
            <tr>
                <td>班级名称:</td>
                <td>
                    <input class="easyui-textbox" disabled="true" style="width:100%;height:25px" type="text" name="name"/>
                </td>
            </tr>
            <tr>
                <td>班级编号:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="sn"/>
                </td>
            </tr>
            <tr id="pwdInput">
                <td>开班时间:</td>
                <td>
                    <input type="text" class="easyui-datebox" style="width:100%;height:25px" name="begintime"/>
                </td>
            </tr>
            <tr>
                <td>班主任:</td>
                <td>
                    <input id="info_combobox" type="text" name="employee.id" style="width:100%;height:25px"
                           class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'realname',
							        url: '/employee_listAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>教室:</td>
                <td>
                    <input id="info_classRoom" type="text" style="width:100%;height:25px" name="classRoom.id"
                           class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'name',
							        url: '/classRoom_listAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>总学费:</td>
                <td>
                    <input type="text" class="easyui-textbox" disabled="true" style="width:100%;height:25px" name="totalMoney"/>
                </td>
            </tr>


            <tr>
                <td>备注:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="remark"/>
                </td>
            </tr>
            <tr>
                <td>状态:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="status" panelHeight='auto'
                            style="width:100%;height:25px">
                        <option value="1" selected="selected">基础班</option>
                        <option value="2">大神班</option>
                        <option value="3">超神班</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="info_tb">
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
    <input type="text" class="easyui-textbox" placeholder="班级名称或编号" style="width:150px;height:20px"
           name="keyword"/>
    日期:<input type="text" id="date_startTime" name="startTime" class="easyui-datebox"/>-
    <input type="text" id="date_endTime" name="endTime" class="easyui-datebox"/>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">往死里查</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cut',plain:true" data-cmd="exportFile">导出</a>
</div>

<div id="info_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
</body>
</html>