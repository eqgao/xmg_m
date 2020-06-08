<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <%@include file="/static/common/common.jsp" %>
    <script type="text/javascript" src="/static/js/permission.js"></script>
</head>

<body>
<!-- 数据表格 -->
<table id="permission_datagrid">
</table>

<!-- 重新加载权限 -->
<div id="permission_datagrid_tb">
    <div>
        <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">加载权限</a>
        <a href="javascript:;" class="easyui-linkbutton"
           data-options="iconCls:'icon-redo',plain:'redo'" id="sql_in" data-cmd='importSql'
        >导入数据库</a>
        <a href="javascript:;" class="easyui-linkbutton" id="sql_bak" data-cmd="exportSql"
           data-options="iconCls:'icon-print',plain:true">备份数据库</a>

        <%--================================================================================================================--%>

        <%--备份对话框--%>
        <div id="sql_bak_dialog">
            <form id="sql_form" action="/sql_bak.do" method="post">
                数据库连接ip地址:<input class="easyui-textbox" id="ip" name="ip" type="text"/><br/>
                数据库连接端口号:<input class="easyui-textbox" id="port" name="port" type="text"/><br/>
                数据库的连接帐号:<input class="easyui-textbox" id="username" name="username" type="text"/><br/>
                数据库的连接密码:<input class="easyui-textbox" id="password" name="password" type="password"/><br/>
                需要备份的数据库:<input class="easyui-textbox" id="database" name="database" type="text"/><br/>
                请输入备份后名称:<input class="easyui-textbox" id="bakName" name="bakName" type="text"/><br/>
                <br/>
                温馨提示:该备份文件默认保存在C盘根目录下,请敬谅,么么哒<br/>
                <br/>
                <a href="javascript:;" class="easyui-linkbutton" id="bak_submit" data-cmd="bakSub"
                   data-options="iconCls:'icon-ok'">确定</a>
            </form>
        </div>
        <%--备份对话框--%>
        <div id="sql_in_dialog">
            <form id="sqlIn_form" action="/sql_in.do" method="post" enctype="multipart/form-data">
                数据库连接ip地址:<input class="easyui-textbox" id="ipIn" name="ip" type="text"/><br/>
                数据库连接端口号:<input class="easyui-textbox" id="portIn" name="port" type="text"/><br/>
                数据库的连接帐号:<input class="easyui-textbox" id="usernameIn" name="username" type="text"/><br/>
                数据库的连接密码:<input class="easyui-textbox" id="passwordIn" name="password" type="password"/><br/>
                需要被导入的数据库:<input class="easyui-textbox" id="databaseIn" name="database" type="text"/><br/>
                请选择需要导入的文件:<input type="file" name="file" id="inName"/><br/>
                <input type="submit" value="提交"/>
            </form>
        </div>
        <%--================================================================================================================--%>

    </div>
</div>

</body>
</html>