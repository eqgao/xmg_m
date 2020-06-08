<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/static/common/common.jsp"%>

<script type="text/javascript" src="/static/js/test.js"></script>
</head>
<body>
	<table id="test_datagrid"></table>

	<div id="test_dialog">
		<form id="test_form" method="post">
			<input type="hidden" name="id" />
			<table align="center" style="margin-top: 15px">
				<tbody>
					<tr>
						<td>考试编号:</td>
						<td><input type="text" class="easyui-textbox" name="sn" data-options="required:true, " /></td>
					</tr>
					<tr>
						<td>考试科目:</td>
						<td><input type="text" class="easyui-combobox"
							name="course.id"
							data-options="
							        valueField: 'id',    
							        textField: 'name',
							        required:true,     
							        url: '/course_listAll.do',panelHeight:'auto'" /></td>
					</tr>
					<tr>
						<td>学员姓名:</td>
						<td><input type="text" name="potenStudent.id"
							class="easyui-combobox"
							data-options="
							        valueField: 'id',    
							        textField: 'name',
							        required:true,    
							        url: '/potenStudent_listAll.do'" />
						</td>
					</tr>
					<tr>
						<td>考试时间:</td>
						<td><input type="text" name="testTime" class="easyui-datebox" data-options="required:true, "/>
						</td>
					</tr>
					<tr>
						<td>考试结果:</td>
						<td><input type="text" class="easyui-numberbox"
							data-options="
								min:0,
								max:100,
								required:true"
							name="testResult" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div id="test_tb">
		<!-- 使用shiro标签控制按钮显示 -->
		<shiro:hasPermission name="test:save">
			<a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="test:update">
			<a href="#" class="easyui-linkbutton" id="test_tb_edit"
				data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
		</shiro:hasPermission>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>
		<!-- <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true"   
	        data-cmd="importFile">导入</a> -->
		<br /> 关键字: <input type="text" id="keyword" name="keyword"
			class="easyui-textbox" /> 开始时间:<input type="text" id="beginTime"
			name="beginTime" class="easyui-datebox"> 结束时间:<input
			type="text" id="endTime" name="endTime" class="easyui-datebox">
		考试结果<input type="text" id="minResult" name="minResult"
			class="easyui-textbox"> ~<input type="text" id="maxResult"
			name="maxResult" class="easyui-textbox"> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
	</div>

	<div id="test_btns">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
	<div id="test_file" class="easyui-dialog"
		data-options="closed: true,width:300,height:300" title="导出成绩单">
		<form action="/test_import.do" method="post"
			enctype="multipart/form-data">
			导入文件:<input type="file" name="file"><br> <input
				type="submit" value="提交">
		</form>
	</div>

</body>
</html>