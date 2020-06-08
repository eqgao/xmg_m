<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/static/common/common.jsp" %>

<script type="text/javascript" src="/static/js/studentloss.js"></script>
</head>
<body>
	<table id="studentloss_datagrid"></table>
	<div id="studentloss_dialog"></div>
	<div id="studentloss_tb">
		<!-- 关键字查询 -->
		<input id="keywordId" prompt="请输入关键字" type="text" class="easyui-textbox" name="keyword"/>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
	 <!-- 班级查询 -->
		<input id="classinfoName" type="text" name="classinfo.id" prompt="请选择班级" class="easyui-combobox"data-options="
						valueField: 'name',    
						textField: 'name',    
						url: '/classinfo_listAll.do',panelHeight:'auto'"/>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchFormByclassinfoId">班级查询</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-remove',plain:true" data-cmd="del">删除</a>
		<input id="begin" prompt="请选择流失时间" type="beginDate" class="easyui-datebox"/>--
		<input id="end" prompt="请选择流失时间" type="endDate" class="easyui-datebox"/>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchFormBydate">流失时间查询</a>
		<a href="#" class="easyui-linkbutton"
       		data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>
	</div>
</body>
</html>