<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>移交列表</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/transferHistory.js"></script>
</head>
<body>
<table id="transferHistory_datagrid"></table>

<div id="transferHistory_dialog">
    <form id="transferHistory_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>姓名:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="potenStudent.name" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td>原负责人:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="oldEmployee.realname" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td>新负责人:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="newEmployee.realname" readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <td>聊天记录:</td>
                <br/>
                <td>
                    <a href="javascript:;" class="easyui-linkbutton"
                       data-options="iconCls:'icon-tip',plain:true" data-cmd="note">点我</a>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<%--图片对话框--%>
<div id="note_dialog"><input id="image_note" type="image"/></div>

<div id="transferHistory_tb">
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="edit">查看</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
</div>

<div id="transferHistory_btns">
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">确定</a>
</div>


</body>
</html>