<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/course.js"></script>
</head>
<body>
<table id="course_datagrid"></table>

<div id="course_dialog">
    <form id="course_form" method="post">T
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id" />
            <tbody>
            <tr>
                <td>班级名称:</td>
                <td>
                    <input id="course_class" type="text" name="classinfo.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'name',
							        url: '/classinfo_listAll.do',panelHeight:'auto'" />
                </td>
            </tr>
            <tr>
                <td>课程名称:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:85%;height:25px" name="name"/>
                </td>
            </tr>
            <tr>
                <td>班主任:</td>
                <td>
                    <input id="course_teacher" type="text" name="teacher.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'realname',
							        url: '/employee_listAll.do',panelHeight:'auto'" />
                </td>
            </tr>
            <tr>
                <td>讲师:</td>
                <td>
                    <input id="course_lecturer" type="text" name="lecturer.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'realname',
							        url: '/employee_listAll.do',panelHeight:'auto'" />
                </td>
            </tr>
            <tr>
                <td>教室:</td>
                <td>
                    <input id="course_room" type="text" name="classRoom.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'name',
							        url: '/classRoom_listAll.do',panelHeight:'auto'" />
                </td>
            </tr>
            <tr id="pwdInput">
                <td>开班时间:</td>
                <td>
                    <input type="text" class="easyui-datebox" style="width:100%;height:25px" name="schooltime"/>
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
                    <select id="cc" class="easyui-combobox" name="status" panelHeight='auto' style="width:143px;">
                        <option value="0">未上</option>
                        <option value="1">已上</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

    <div id="course_tb">
        <!-- 使用shiro标签控制按钮显示 -->
        <shiro:hasPermission name="course:save">
            <a href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="course:update">
            <a href="#" class="easyui-linkbutton" id="emp_tb_edit"
               data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        </shiro:hasPermission>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-remove',plain:true" id="emp_tb_cs"
           data-cmd="del">删除</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
        <!-- 关键字查询 -->
        <input type="text" class="easyui-textbox"  placeholder ="班级名称或编号" style="width:150px;height:20px"
               name="keyword"/>
        日期:<input type="text" id="date_startTime" name="startTime" class="easyui-datebox"/>-
        <input type="text" id="date_endTime" name="endTime" class="easyui-datebox"/>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">往死里查</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-cut',plain:true" data-cmd="exportFile">导出</a>
    </div>

    <div id="course_btns">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
    </div>


</body>
</html>