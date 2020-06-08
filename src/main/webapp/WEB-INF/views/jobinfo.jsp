<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/jobinfo.js"></script>
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
                <td>电话:</td>
                <td>
                    <input class="easyui-textbox" data-options="disabled:true" type="text" name="student.tel"/>
                </td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td>
                    <input class="easyui-numberbox" data-options="disabled:true" type="text" name="student.qq"/>
                </td>
                <td>升班/留级时间 :</td>
                <td>
                    <input class="easyui-datebox" type="text" name="jobinfotime"/>
                </td>
            </tr>
            <tr>
                <td>升班/留级:</td>
                <td>
                    <select class="easyui-combobox" data-options="width:100,panelHeight:'auto'" name="status">
                        <option value="0">留级</option>
                        <option value="1">升级</option>
                    </select>
                </td>
                <td>流入班级 :</td>
                <td>
                    <input id="toclass" class="easyui-combobox" name="classinfo.id"/>
                </td>
            </tr>
            <tr>
                <td>营销人员 :</td>
                <td>
                    <input class="easyui-textbox" type="text" data-options="disabled:true" name="emp.realname"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="win"></div>

<div id="emp_tb">
    <form id="query_condition">
        入职时间：
        <input type="text" class="easyui-datebox" name="beginTime"/>~
        <input type="text" class="easyui-datebox" name="endTime"/>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
        <select id="groupBy" name="groupType" class="easyui-combobox" panelHeight="auto" >
            <option selected value="si.gender">性别</option>
            <option value='date_format(j.time,"%Y-%m")'>入职日期（月）</option>
            <option value='date_format(j.time,"%Y-%m-%d")'>入职日期（日）</option>
            <option value="si.age">年龄</option>
            <option value="si.computerOrNot">是否电脑专业</option>
            <option value="a.name">地址</option>
            <option value="si.school">院校</option>
            <option value="si.major">专业</option>
        </select>
        <a href="#" class="easyui-linkbutton" id="jobinfo_tb_edit"
           data-options="iconCls:'icon-edit',plain:true" data-cmd="showPic">查看统计图</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>

    </form>
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