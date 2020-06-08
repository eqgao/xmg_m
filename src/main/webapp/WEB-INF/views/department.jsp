<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/department.js"></script>
</head>
<body>
<table id="dept_datagrid"></table>

<div id="dept_dialog">
    <form id="dept_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>部门编码:</td>
                <td>
                    <input type="text" class="easyui-textbox" data-options="required:true" name="sn"/>
                </td>
            </tr>
            <tr>
                <td>部门名称:</td>
                <td>
                    <input type="text" class="easyui-textbox" data-options="required:true" name="name"/>
                </td>
            </tr>
            <tr>
                <td>部门经理:</td>
                <td>
                    <input type="text" name="manager.id" class="easyui-combobox" id="manager_combobox"
                           data-options="
							        valueField: 'id',    
							        textField: 'realname',    
							        url: '/employee_listAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>上级部门:</td>
                <td>
                    <input type="text" name="parent.id" class="easyui-combobox" id="parent_combobox"
                           data-options="
							        valueField: 'id',    
							        textField: 'name',    
							        url: '/department_list.do',panelHeight:'auto'"/>
                </td>
            </tr>
           <!-- <tr id="state">
                <td>状态:</td>
                 <td>
                    <select id="cc" class="easyui-combobox" name="state" panelHeight='auto' style="width:120px;" >
                        <option value="true" >正常</option>
                        <option value="false">已取消</option>
                    </select>
                </td>
            </tr> -->
            </tbody>
        </table>
    </form>
</div>

<div id="dept_tb">
    <!-- 使用shiro标签控制按钮显示 -->
    <shiro:hasPermission name="department:save">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="department:update">
        <a href="#" class="easyui-linkbutton" id="dept_tb_edit"
           data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="department:changeState">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-remove',plain:true" id="dept_tb_cs"
       data-cmd="changeState">取消该部门</a> 
    </shiro:hasPermission>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <!-- 关键字查询 -->
   <!--  <input type="text" class="textbox" name="keyword"/>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a> -->
</div>

<div id="dept_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


</body>
</html>