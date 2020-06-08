<%@ page language="java" contentType="text/html;charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>潜在学员列表</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/potenClient.js"></script>
</head>
<body>
<table id="potenClient_datagrid"></table>
<div id="potenClient_tb">
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-tip',plain:true" data-cmd="formal">搞定他</a>

    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>

    <!-- 关键字查询 -->
    <input type="text" id="keyword" class="easyui-textbox" name="keyword"/>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
</div>
</body>
</html>