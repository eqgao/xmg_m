<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/employee.js"></script>
</head>
<body>
<table id="emp_datagrid"></table>

<div id="emp_dialog">
    <form id="emp_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>用户名:</td>
                <td>
                    <input type="text" class="easyui-textbox" data-options="required:true" name="username"/>
                </td>
            </tr>
            <tr>
                <td>真实姓名:</td>
                <td>
                    <input type="text" class="easyui-textbox" data-options="required:true" name="realname"/>
                </td>
            </tr>
            <tr id="pwdInput">
                <td>密码:</td>
                <td>
                    <input type="password" class="easyui-textbox" data-options="required:true" name="password"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td>
                    <input type="text" class="easyui-textbox" name="tel"/>
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td>
                    <!-- 							<input type="text" name="email" class="easyui-validatebox" data-options="required:true,validType:'email'"/>
-->
                    <input type="text" class="easyui-textbox" data-options="validType:'email'" name="email"/>
                </td>
            </tr>
            <tr>
                <td>入职时间:</td>
                <td>
                    <input type="text" name="inputtime" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>部门:</td>
                <td>
                    <input type="text" name="dept.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',    
							        textField: 'name',    
							        url: '/department_list.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>管理员:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="admin" panelHeight='auto' style="width:120px;">
                        <option value="true">是</option>
                        <option value="false">否</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>角色:</td>
                <td>
                    <input id="roles_combobox" type="text" class="easyui-combobox"
                           data-options="
							        valueField: 'id',    
							        textField: 'name',    
							        url: '/role_listAll.do',panelHeight:'auto',multiple:true"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="emp_tb">
    <!-- 使用shiro标签控制按钮显示 -->
    <shiro:hasPermission name="employee:save">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:update">
        <a href="#" class="easyui-linkbutton" id="emp_tb_edit"
           data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-remove',plain:true" id="emp_tb_cs"
       data-cmd="changeState">离职</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>
    <!-- 关键字查询 -->
    <input type="text" class="textbox" name="keyword"/>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-print',plain:true" data-cmd="exportFile">导出</a>
	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cut',plain:true"   
	        data-cmd="importFile">导入</a>
</div>

<div id="emp_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>

<div id="emp_file" class="easyui-dialog" data-options="closed: true,width:300,height:200," title="导入员工数据">
    <form action="/employee_import.do" method="post" enctype="multipart/form-data" >
        文件:<input type="file" name="file"><br>
        <input type="submit" value="提交">
    </form>
</div>

</body>
</html>