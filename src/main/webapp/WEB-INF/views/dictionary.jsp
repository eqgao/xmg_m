<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>数据字典</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/dictionary.js"></script>
</head>
<body>

<%--面板布局--%>
<div id="cc" class="easyui-layout" style="width:1166px;height:600px;">
    <div data-options="region:'west',title:'字典目录',split:true"
         style="width:600px;">

        <%--字典目录--%>
        <table id="dictionary_datagrid"></table>

    </div>
    <div data-options="region:'center',title:'字典明细'"
         style="padding:5px;background:#eee;">

        <%--字典明细--%>
        <table id="dictionaryItem_datagrid"></table>

        <div id="dictionaryItem_tb">
            <a href="javascript:;" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
            <a href="javascript:;" class="easyui-linkbutton"
               data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
            <a href="javascript:;" class="easyui-linkbutton"
               data-options="iconCls:'icon-remove',plain:true" data-cmd="changeState"
            >删除</a>
            <a href="javascript:;" class="easyui-linkbutton"
               data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
        </div>
    </div>
</div>


<div id="dictionaryItem_dialog">
    <form id="dictionaryItem_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>编码:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="sn"/>
                </td>
            </tr>
            <tr>
                <td>名称:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="name"/>
                </td>
            </tr>

            <tr>
                <td>字典目录:</td>
                <br/>
                <td>
                    <input id="hiId" type="hidden" name="details.id"/>
                    <input id="did" class="easyui-combobox" name="details.id"
                           data-options="valueField:'id',
                           textField:'name',
                           panelHeight:'auto',
                           url:'/dictionary_listAll.do'"/>
                </td>

            </tr>
            <tr>
                <td>介绍:</td>
                <td>
                    <input class="easyui-textbox" type="textarea" style="width:210px" name="intro"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>


<div id="dictionaryItem_btns">
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">确定</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


</body>
</html>