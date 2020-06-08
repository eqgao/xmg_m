<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/static/common/common.jsp" %>

<script type="text/javascript" src="/static/js/role.js"></script>
</head>
<body>
	<table id="role_datagrid"></table>

	<div id="role_dialog">
		<form id="role_form" method="post">
			<input type="hidden" name="id" />
			<table align="center" style="margin-top: 15px">
				<tbody>
					<tr>
						<td>角色编号:<input type="text" name="sn" class="easyui-textbox"/></td>
						<td>角色名称:<input type="text" name="name" class="easyui-textbox"/></td>
					</tr>
					<tr>
						<td>
							<table id="allPermissions"></table>
						</td>
						<td>
							<table id="selfPermissions"></table>
						</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div id="role_tb">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
		<a href="#" class="easyui-linkbutton" id="role_tb_edit"
			data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
		<a href="#" class="easyui-linkbutton"
		   data-options="iconCls:'icon-remove',plain:true" id="emp_tb_cs"
		   data-cmd="del">删除</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
		<!-- 关键字查询 -->
		<input type="text" name="keyword" class="easyui-textbox"/>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
		
	</div>

	<div id="role_btns">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>


</body>
</html>