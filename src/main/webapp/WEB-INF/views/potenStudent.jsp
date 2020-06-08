<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>潜在学员列表</title>
    <%@include file="/static/common/common.jsp" %>

    <script type="text/javascript" src="/static/js/potenStudent.js"></script>
</head>
<body>
<table id="potenStudent_datagrid"></table>
<div id="potenStudent_dialog">
    <form id="potenStudent_form" method="post">
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>姓名:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="name"/>
                </td>
                <td>qq:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="qq"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="tel"/>
                </td>
                <td>咨询师:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" name="employee.id"
                           data-options="required:true,valueField:'id',textField:'realname',url:'/employee_listAll.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
            </tr>
            <tr>
                <td>最后约的时间:</td>
                <td>
                    <input type="text" name="lastTime" class="easyui-datebox"/>
                </td>
                <td>下次约的时间:</td>
                <td>
                    <input type="text" name="nextTime" class="easyui-datebox"/>
                </td>
            </tr>
            <tr>
                <td>来源:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" name="source.id"
                           data-options="required:true,valueField:'id',textField:'name',url:'/dictionaryItem_listSource.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
                <td>意向程度:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" name="level.id"
                           data-options="required:true,valueField:'id',textField:'name',url:'/dictionaryItem_listLevel.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
            </tr>
            <tr>
                <td>意向课程:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" name="course.id"
                           data-options="required:true,valueField:'id',textField:'name',url:'/course_listAll.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
                <td>状态:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" id="status_cbb" name="status.id"
                           data-options="required:true,valueField:'id',textField:'name',url:'/dictionaryItem_listStatus.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="remark"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>

    =======================================================================
    <form id="studentInfo_form" method="post" enctype="multipart/form-data">
        学员信息编辑:
        <input type="hidden" name="id"/>
        <table align="center" style="margin-top: 15px">
            <tbody>
            <tr>
                <td>请上传一张您的高清无码果照:</td>
                <td>
                    <input type="file" name="file" id="info_image"/>
                </td>
            </tr>
            <tr>
                <td>年龄:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="age"/>
                </td>
                <td>性别:</td>
                <td>
                    <select class="easyui-combobox" name="gender" style="width:70px;"
                            data-options="panelHeight:'auto'">
                        <option value=1>男</option>
                        <option value=0>女</option>
                    </select>
                </td>
                <td>毕业院校:<input class="easyui-textbox" type="text" name="school"/>
                </td>
                <td>专业:<input class="easyui-textbox" type="text" name="major"/>
                </td>
            </tr>
            <tr>
                <td>学历:</td>
                <td>
                    <select class="easyui-combobox" name="education" style="width:70px;"
                            data-options="panelHeight:'auto'">
                        <option value=1>博士</option>
                        <option value=2>硕士</option>
                        <option value=3>本科</option>
                        <option value=4>大专</option>
                        <option value=5>中专</option>
                        <option value=6>初中</option>
                    </select>
                </td>
                <td>是否计算机专业:</td>
                <td>
                    <select class="easyui-combobox" name="computerOrNot" style="width:70px;"
                            data-options="panelHeight:'auto'">
                        <option value=1>是</option>
                        <option value=0>否</option>
                    </select>
                </td>
                <td>原职业:<input type="text" name="profession" class="easyui-textbox"/>
                </td>
            <tr>
                <td>原薪资:</td>
                <td>
                    <input type="text" name="salary" class="easyui-textbox"/>
                </td>
                <td>工作地址:</td>
                <td>
                    <input id="address_max" class="easyui-combobox"/>省
                </td>
                <td>
                    <input id="address_pro" class="easyui-combobox"/>市
                </td>
                <td>
                    <input id="address_city" class="easyui-combobox" name="address.id"
                    />区
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<%--=======================================添加信息表=======================================================--%>
<%--==============================================================================================--%>


<%--查看考试情况表单--%>
<div id="exam_dialog">
    <table id="exam_datagrid"></table>
</div>
<%----%>

<div id="potenStudent_tb">
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-remove',plain:true" data-cmd="changeState">流失</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-more',plain:true" data-cmd="follow">跟进</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-ok',plain:true" data-cmd="exam">考试查询</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-tip',plain:true" data-cmd="formal">转正</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-redo',plain:true" data-cmd="transferEmp">移交</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-redo',plain:true" data-cmd="transferClient">移入客户池</a>

    <br/>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>

    <!-- 关键字查询 -->
    <input type="text" name="keyword"/>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
    状态:<input class="easyui-combobox"
              name="statusId" id="search_statusId"
              data-options="valueField: 'id',
        textField: 'name',
        url: '/dictionaryItem_listAllStatus.do',
        panelHeight: 'auto',"/>
    <a href="javascript:;" class="easyui-linkbutton" data-cmd="export" data-options="iconCls:'icon-print',plain:true">导出</a>

</div>

<div id="potenStudent_btns">
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a href="javascript:;" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>


<%--转正式学员添加表--%>
<div id="student_dialog">
    <form id="student_form" method="post">
        <table align="center" style="margin-top: 15px">
            <tbody>
            <input type="hidden" name="id"/>
            <tr>
                <input type="hidden" name="studentInfo.id"/>
                <td>姓名:</td>
                <td><input type="text" class="easyui-textbox" name="name" id="name"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" class="easyui-textbox" name="tel" id="tel"/></td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td><input type="text" class="easyui-textbox" name="qq" id="qq"/></td>
            </tr>
            <tr>
                <td>营销人员:</td>
                <td><input id="employee_cbb" class="easyui-combobox" name="employee.id"
                           data-options="valueField:'id',textField:'realname',url:'/employee_listAll.do'"/></td>
            </tr>
            <tr>
                <td>教室:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" id="classinfo_cbb" name="classinfo.id"
                           data-options="required:true,valueField:'id',
                           textField:'name',url:'/classinfo_listAll.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
            </tr>
            <tr>
                <td>转入部门:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" id="department_cbb" name="departmentId"
                           data-options="required:true,valueField:'id',
                           textField:'name',url:'/department_list.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
            </tr>
            <tr>
                <td>所属角色:</td>
                <td>
                    <input class="easyui-combobox easyui-validatebox" id="role_cbb" name="roleId"
                           data-options="required:true,valueField:'id',
                           textField:'name',url:'/role_listAll.do',panelHeight:'auto'"/>
                    <%--下拉列表--%>
                </td>
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

<div id="student_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="student_save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="student_cancel">取消</a>
</div>
<%----%>


<%--=========================移交表================================--%>
<div id="transfer_dialog">
    <form id="transfer_form" method="post" enctype="multipart/form-data">
        <table align="center" style="margin-top: 15px">
            <tbody>
            <input type="hidden" name="id"/>
            <tr>
                <td>姓名:</td>
                <td><input type="text" class="easyui-textbox" name="name" id="transfer_name"
                           data-options="disabled:'true'"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" class="easyui-textbox" name="tel" id="transfer_tel"
                           data-options="disabled:'true'"/></td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td><input type="text" class="easyui-textbox" name="qq" id="transfer_qq"
                           data-options="disabled:'true'"/></td>
            </tr>
            <tr>
                <td>原负责人:</td>
                <td><input id="oldEmployee_cbb" class="easyui-combobox" name="employee.id"
                           data-options="valueField:'id',textField:'realname',url:'/employee_listAll.do',
                disabled:'true'"/></td>
            </tr>
            <tr>
                <td>新负责人:</td>
                <td><input id="newEmployee_cbb" data-options="required:true" class="easyui-combobox easyui-validatebox"
                           name="employee.id"
                /></td>
            </tr>
            <tr>
                <td>原因:</td>
                <td><input id="transfer_cause" class="easyui-textbox"
                           data-options="multiline:true" name="remark"
                           style="width: 200px; height: 100px"></td>
            </tr>
            <tr>
                <td>聊天记录:</td>
                <td><input id="transfer_image" type="file"
                           name="file"></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="transfer_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="transfer_save">保存</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="transfer_cancel">取消</a>
</div>
<%--===================================================================--%>

<%--========================移入客户池================================--%>
<div id="client_dialog">
    <form id="client_form" method="post">
        <table align="center" style="margin-top: 15px">
            <tbody>
            <input type="hidden" name="id"/>
            <tr>
                <td>姓名:</td>
                <td><input type="text" class="easyui-textbox" name="name" id="client_name"
                           data-options="disabled:'true'"/>
                </td>
            </tr>
            <tr>
                <td>电话:</td>
                <td><input type="text" class="easyui-textbox" name="tel" id="client_tel"
                           data-options="disabled:'true'"/></td>
            </tr>
            <tr>
                <td>QQ:</td>
                <td><input type="text" class="easyui-textbox" name="qq" id="client_qq"
                           data-options="disabled:'true'"/></td>
            </tr>
            <tr>
                <td>原负责人:</td>
                <td><input id="client_cbb" class="easyui-combobox" name="employee.id"
                           data-options="valueField:'id',textField:'realname',url:'/employee_listAll.do',
                disabled:'true'"/></td>
            </tr>
            <tr>
                <td>意向程度:</td>
                <td>
                    <input id="client_level" class="easyui-combobox easyui-validatebox" name="level.id"
                           data-options="required:true,valueField:'id'
                           ,textField:'name',url:'/dictionaryItem_listLevel.do'
                           ,panelHeight:'auto',
    disabled:'true'"/>
                </td>
            </tr>
            <tr>
                <td>意向课程:</td>
                <td>
                    <input id="client_course" class="easyui-combobox easyui-validatebox" name="course.id"
                           data-options="disabled:'true',required:true,valueField:'id',textField:'name',url:'/course_listAll.do',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input id="client_cause" class="easyui-textbox"
                           data-options="multiline:true" name="remark"
                           style="width: 200px; height: 100px"></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="client_btns">
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-save',plain:true" data-cmd="client_save">确定</a>
    <a href="#" class="easyui-linkbutton"
       data-options="iconCls:'icon-cancel',plain:true" data-cmd="client_cancel">取消</a>
</div>
<%--===================================================================--%>


</body>
</html>