<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/classroom.js"></script>
</head>
<body>
<table id="room_datagrid"></table>

<div id="room_dialog">
    <form id="room_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id" />
            <tbody>
            <tr>
                <td>教室名称:</td>
                <td>
                    <input class="easyui-textbox" style="width:100%;height:25px" type="text" name="name"/>
                </td>
            </tr>
            <tr>
                <td>教室位置:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="location"/>
                </td>
            </tr>
            <tr id="pwdInput">
                <td>座位数:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="num"/>
                </td>
            </tr>
            <tr>
                <td>状态:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="status" panelHeight='auto' style="width:143px;">
                        <option value="true">启用</option>
                        <option value="false">未启用</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

    <div id="room_tb">
        <!-- 使用shiro标签控制按钮显示 -->
        <shiro:hasPermission name="classroom:save">
            <a href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="classroom:update">
            <a href="#" class="easyui-linkbutton" id="emp_tb_edit"
               data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        </shiro:hasPermission>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-remove',plain:true" id="emp_tb_cs"
           data-cmd="del">删除</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
        <!-- 关键字查询 -->
        <input type="text" class="easyui-textbox"  placeholder ="教室名字或教室位置" style="width:150px;height:20px"
               name="keyword"/>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜搜搜</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-cut',plain:true" data-cmd="exportFile">导出</a>

    </div>

    <div id="room_btns">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
    </div>

    <%--<div id="room_file" class="easyui-dialog" data-options="">
        <form action="/classroom_import.do" method="post" enctype="multipart/form-data">
            文件:<input type="file" name="file"><br>
            <input type="submit" value="提交">
        </form>
    </div>--%>


</body>
</html>