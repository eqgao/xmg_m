<%@ page language="java" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%
    String ip = request.getRemoteHost();
    request.setAttribute("ip", ip);
%>
<head>
    <title>Dashboard | Klorofil - Free Bootstrap Dashboard Template</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="/static/common/common-assets-start.jsp" %>

</head>

<body>
<div style="width: 100%; height: 100%; background-image: url(/play/timg.gif);background-color: black">
    <!-- WRAPPER -->
    <div id="wrapper" style="height: 100%">
        <!-- NAVBAR -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="brand">
                <a href="index.html"><img src="/static/plugins/assets/img/logo-dark.png" alt="Klorofil Logo"
                                          class="img-responsive logo"></a>
            </div>
            <%--=====================================--%>

            <%--=====================================--%>
            <div class="container-fluid">
                <div id="navbar-menu">
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" class="notification-item" id="sign">签到</a></li>
                        <li class="dropdown" style="padding-top: 10px;">
                            <iframe style="display: inline;" width="420" scrolling="no" height="60" frameborder="0"
                                    allowtransparency="true"
                                    src="http://i.tianqi.com/index.php?c=code&id=12&icon=1&num=5&site=12"></iframe>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"
                               style="padding:14px;"><img
                                    src="/upload/will.png" style="height: 50px;width: 50px;"
                                    class="img-circle" alt="Avatar">
                                <span><shiro:principal property="realname"/></span> <i
                                        class="icon-submenu lnr lnr-chevron-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
                                <li><a href="#"><i class="lnr lnr-envelope"></i> <span>Message</span></a></li>
                                <li><a href="#"><i class="lnr lnr-cog"></i> <span>Settings</span></a></li>
                                <li><a href="/logout"><i class="lnr lnr-exit"></i> <span>Logout</span></a></li>
                            </ul>
                        </li>
                        <!-- <li>
                            <a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
                        </li> -->
                    </ul>
                </div>
            </div>
        </nav>
        <!-- END NAVBAR -->
        <!-- LEFT SIDEBAR -->
        <div id="sidebar-nav" class="sidebar">
            <div class="sidebar-scroll">
                <nav>
                    <ul class="nav">
                        <c:forEach items="${menuList}" var="m" step="1">
                            <li>
                                <a href="#subPages${m.id}" data-toggle="collapse" class="collapsed"><i
                                        class="lnr lnr-file-empty"></i>
                                    <span>${m.text}</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
                                <c:if test="${m.children!=null}">
                                    <div id="subPages${m.id}" class="collapse ">
                                        <ul class="nav">
                                            <c:forEach items="${m.children}" var="child">

                                                <li><a data-url="${child.url}" class="menu_item">${child.text}</a></li>
                                            </c:forEach>
                                        </ul>
                                    </div>

                                </c:if>
                            </li>
                        </c:forEach>

                    </ul>
                </nav>
            </div>
        </div>
        <!-- END LEFT SIDEBAR -->
        <!-- MAIN -->
        <div class="main" style="height: 100%;">
            <!-- MAIN CONTENT -->
            <div id="index_tabs" fit="true" class="easyui-tabs">
            </div>
            <!-- END MAIN CONTENT -->
        </div>
        <!-- END MAIN -->
        <div class="clearfix"></div>
    </div>
    <!-- END WRAPPER -->
    <%@include file="/static/common/common-assets-end.jsp" %>
    <%@include file="/static/common/common.jsp" %>

    <script>
        $(function () {
            $("#index_tabs").tabs({
                bodyCls: "autoHeight",
            });

            $("#index_tabs").tabs("add", {
                title: '首页',

                content: "<iframe frameborder=0 height='100%' width='100%' src='/play/list.jsp'></iframe>"
            })

            $(".menu_item").click(function () {
//            console.debug($(this));
                //判断选项卡是否存在,如果存在,就选中该选项卡,否则,就添加一个选项卡
                if ($("#index_tabs").tabs("exists", $(this).text())) {//存在
                    $("#index_tabs").tabs("select", $(this).text());//选中
                } else {//不存在
                    $("#index_tabs").tabs("add", {
                        title: $(this).text(),
                        closable: true,
//					href:node.attributes.url,
                        content: "<iframe frameborder=0 height='100%' width='100%' src=" + $(this).data("url") + "></iframe>"
                    })
                }
            });
        });
        $("#sign").bind('click', function () {
                    $.post("/index_sign.do", function (data) {
                        if (data) {
                            $.messager.alert('温馨提示', '签到成功！', 'info')
                        }
                    })
                }
        );
    </script>
</div>
</body>

</html>