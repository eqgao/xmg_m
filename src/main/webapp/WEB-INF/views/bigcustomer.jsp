<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/bigcustomer.js"></script>
</head>
<body>
<table id="bigcustomer_datagrid"></table>

<div id="bigcustomer_dialog">
    <form id="bigcustomer_form" method="post">
        <table align="center" style="margin-top: 15px">
            <input type="hidden" name="id" />
            <tbody>
            <tr>
                <td>学校:</td>
                <td>
                    <input class="easyui-textbox" style="width:100%;height:25px" type="text" name="school"/>
                </td>
            </tr>
            <tr>
                <td>区域:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="area"/>
                </td>
            </tr>
            <tr id="pwdInput">
                <td>创建时间:</td>
                <td>
                    <input type="text" class="easyui-datebox" style="width:100%;height:25px" name="creattime"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="tel"/>
                </td>
            </tr>
            <tr>
                <td>邮箱:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="eamil"/>
                </td>
            </tr>
            <tr>
                <td>跟进人员:</td>
                <td>
                    <input id="bigcustomer_combobox" type="text" name="employee.id" class="easyui-combobox"
                           data-options="
							        valueField: 'id',
							        textField: 'realname',
							        url:'/employee_listAll.do',panelHeight:'auto'" />
                </td>
            </tr>

            <tr>
                <td>联系人:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="linkman"/>
                </td>
            </tr>
            <tr>
                <td>跟进次数:</td>
                <td>
                    <input type="text" class="easyui-textbox" style="width:100%;height:25px" name="tailafter"/>
                </td>
            </tr>
            <tr>
                <td>签约:</td>
                <td>
                    <select  class="easyui-combobox" name="agreeornot" panelHeight='auto' style="width:143px;">
                        <option value="1">未签约</option>
                        <option value="2">已签约</option>

                    </select>
                </td>
            </tr>
            <tr>
                <td>意向星级:</td>
                <td>
                    <select id="cc" class="easyui-combobox" name="starlevel" panelHeight='auto' style="width:143px;">
                        <option value="1星">1星</option>
                        <option value="2星">2星</option>
                        <option value="3星">3星</option>
                        <option value="4星">4星</option>
                        <option value="5星">5星</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

    <div id="bigcustomer_tb">
        <!-- 使用shiro标签控制按钮显示 -->
        <shiro:hasPermission name="classbigcustomer:save">
            <a href="#" class="easyui-linkbutton"
               data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="classbigcustomer:update">
            <a href="#" class="easyui-linkbutton" id="emp_tb_edit"
               data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        </shiro:hasPermission>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-remove',plain:true" id="emp_tb_cs"
           data-cmd="del">删除</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">查询全部</a>


        <!-- 关键字查询 -->
        关键字: <input type="text" class="easyui-textbox"  placeholder ="" style="width:150px;height:20px"
               name="keyword"/>
        日期:<input type="text" id="date_startTime" name="startTime" class="easyui-datebox"/>-
        <input type="text" id="date_endTime" name="endTime" class="easyui-datebox"/>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">搜索</a>


    </div>

    <div id="bigcustomer_btns">
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
        <a href="#" class="easyui-linkbutton"
           data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
    </div>

</body>
</html>