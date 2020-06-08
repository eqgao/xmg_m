<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="/static/common/common.jsp"%>
<script type="text/javascript" src="/static/js/student.js"></script>
<%@include file="/static/common/common-edit.jsp"%>

</head>
<body>
	<!-- 学生退学原因 -->

	<!-- 富文本 -->
	<div id="remark_div" class="easyui-dialog" title="My Dialog"
		style="width: 400px; height: 200px;"
		data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,fit:true,buttons:'#edit_btns',title:'备注详情'">
		<form id="remark_form"  method="post" 
			name="remark" enctype="multipart/form-data">
			<!--  加载编辑器的容器  -->
			<script id="container" name="remark" type="text/plain">
       		 这里写你的初始化内容 
       	</script>
		</form>
	</div>
	<div id="edit_btns">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true"
			data-cmd="send_edit">保存</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true"
			data-cmd="cancel_edit">取消</a>
	</div>
	<!-- ------------------------ -->
	<table id="student_datagrid"></table>
	<div id="student_dialog">
		<form id="student_form" method="post">
			<input type="hidden" name="id" />
			<table align="center" style="margin-top: 15px">
				<tbody>
					<tr>
						<td>姓名:</td>
						<td><input type="text" class="easyui-textbox" name="name" />
						</td>
						<td>电话:</td>
						<td><input type="text" class="easyui-textbox" name="tel" /></td>
					</tr>
					<tr>
						<td>QQ:</td>
						<td><input type="text" class="easyui-textbox" name="qq" /></td>
						<td>缴费金额:</td>
						<td><input type="text" class="easyui-textbox" name="tuition" />
						</td>
					</tr>
					<tr>
						<td>缴费时间:</td>
						<td><input type="text" class="easyui-datebox" name="paytime"
							class="easyui-datebox" /></td>
						<td>入学时间:</td>
						<td><input type="text" class="easyui-datebox"
							name="entrancetime" /></td>
					</tr>
					<!-- <tr>
						<td>备注:</td>
						<td><input class="easyui-textbox"
							data-options="multiline:true" name="remark"
							style="width: 200px; height: 100px"></td>
					</tr> -->
				</tbody>
			</table>
		</form>
	</div>
	<!-- 把值传递给收款管理 -->
	<div id="studentredeipt_dialog">
		<form id="studentredeipt_form" method="post">
			<table align="center" style="margin-top: 15px">
				<tbody>
					<tr>
						<td>收款金额:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-numberbox" name="receiptamount" /></td>
					</tr>
					<tr>
						<td>单据号:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-textbox" name="bills" /></td>
					</tr>
					<tr>
						<td>收据:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-textbox" name="receipt" /></td>
					</tr>
					<tr>
						<td>备注:</td>
						<td><input class="easyui-textbox"
							data-options="multiline:true" name="remark"
							style="width: 200px; height: 100px"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<!-- 把值传递给就业信息统计 -->
	<div id="studentjob_dialog">
		<form id="studentjob_form" method="post">
			<table align="center" style="margin-top: 15px">
				<tbody>
					<tr>
						<td>姓名:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-textbox" name="name" /></td>
					</tr>
					<tr>
						<td>入职时间:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-datebox" name=time /></td>
					</tr>
					<tr>
						<td>就业公司 :</td>
						<td><input type="text" data-options="required:true"
							class="easyui-textbox" name="company" /></td>
					</tr>
					<tr>
						<td>就业薪资:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-numberbox" name=salary /></td>
					</tr>
					<tr>
						<td>岗位:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-textbox" name="profession" /></td>
					</tr>
					<tr>
						<td>福利待遇:</td>
						<td><input type="text" data-options="required:true"
							class="easyui-textbox" name="welfare" /></td>
					</tr>
					<tr>
						<td>入职城市:</td>
						<td><input id="address_max" data-options="required:true"
							class="easyui-combobox" />省</td>
						<td><input id="address_pro" data-options="required:true"
							class="easyui-combobox" />市</td>
						<td><input id="address_city" data-options="required:true"
							class="easyui-combobox" name="address.id" />区</td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div id="studentjob_btns">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true"
			data-cmd="studentjob_save">保存</a> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true"
			data-cmd="studentjob_cancel">取消</a>
	</div>
	<!-- --------------------------------------------------------------------------------- -->

	<div id="student_tb">
		<!-- 关键字查询 -->
		<input id="keywordId" prompt="请输入关键字" type="text"
			class="easyui-textbox" name="keyword" /> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
		<!-- 班级查询 -->
		<input id="classinfoId" type="text" name="classinfo.id" prompt="请选择班级"
			class="easyui-combobox"
			data-options="
						valueField: 'id',    
						textField: 'name',    
						url: '/classinfo_listAll.do',panelHeight:'auto'" />
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true"
			data-cmd="searchFormByclassinfoId">班级</a> <select id="statusSearch"
			class="easyui-combobox" name="status" panelHeight='auto'
			style="width: 120px;">
			<option value="-1">选择学员状态</option>
			<option value="0">非正式学员</option>
			<option value="1">正式学员</option>
			<option value="2">已经退学</option>
			<option value="4">已经毕业</option>
			<option value="3">已经就业</option>
		</select> <select id="feesstatusSearch" class="easyui-combobox"
			name="feesstatus" panelHeight='auto' style="width: 120px;">
			<option value="-1">选择缴费状态</option>
			<option value="0">未缴清</option>
			<option value="1">已缴清</option>
			<option value="2">待审核</option>
		</select> <input id="begin" prompt="请选择入学时间" type="beginDate"
			class="easyui-datebox" />-- <input id="end" prompt="请选择入学时间"
			type="endDate" class="easyui-datebox" /> <a href="#"
			class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true"
			data-cmd="searchFormBydate">入学时间</a> <a href="#"
			class="easyui-linkbutton" id="emp_tb_edit"
			data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
		<a id="dropout_tb" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cut',plain:true" data-cmd="dropout">退学</a>
		<a id="fees_tb" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-sum',plain:true" data-cmd="fees">缴费</a> <a
			id="graduationion_tb" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cry',plain:true" data-cmd="graduationion">毕业</a>
		<a id="graduation_tb" href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-tip',plain:true" data-cmd="graduation">就业</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>
		<!-- <a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-search',plain:true"
			data-cmd="detailed_information">查看详细信息</a>  -->
		<a href="#"class="easyui-linkbutton"
			data-options="iconCls:'icon-cry',plain:true" data-cmd="remark">查看/编辑备注</a>
		<a href="#"class="easyui-linkbutton"
		   data-options="iconCls:'icon-cry',plain:true" data-cmd="toSystemMenber">转成系统社员</a>
	</div>
	<!-- 提交缴费单据按钮 -->
	<div id="studentredeipt_btns">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save_redeipt">提交单据</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true"
			data-cmd="cancel_redeipt">取消提交</a>
	</div>
	<div id="student_btns">
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
		<a href="#" class="easyui-linkbutton"
			data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
	</div>
</body>
</html>