<%@ page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>广东工业大学</title>
    <%@include file="/static/common/common.jsp" %>



    <style type="text/css">
        * {
            text-align: center;
        }
    </style>
    <style type="text/css">
        * {
            -moz-user-select: none;
            border: 0px;
        }

        #tips {
            width: 850px;
            padding-top: 8px;
            padding-bottom: 8px;
            background: #999999;
            color: #FFFFFF;
            text-align: center;
            margin: auto;
            font-size: 14px;
        }
    </style>
    <script type="text/javascript">
        var Is_Mouse_Down = false
        var Is_First = 1
        function Create(evt) {
            evt = evt ? evt : window.event
            if (Is_Mouse_Down) {
                if (evt.ctrlKey) {
                    Clean(evt)
                } else {
                    Write(evt)
                }
            }
        }
        function Write(evt) {
            evt = evt ? evt : window.event
            var Obj_Nian = document.createElement("DIV")
            Obj_Nian.style.position = "absolute"
            Obj_Nian.style.left = evt.clientX - 10 + "px"
            Obj_Nian.style.top = evt.clientY - 10 + "px"
            switch (Is_First) {
                case 1:
                    Obj_Nian.innerHTML = "小";
                    break;
                case 2:
                    Obj_Nian.innerHTML = "码";
                    break;
                case 3:
                    Obj_Nian.innerHTML = "哥";
                    break;
                case 4:
                    Obj_Nian.innerHTML = "教";
                    break;
                case 5:
                    Obj_Nian.innerHTML = "育";
                    break;
                case 6:
                    Obj_Nian.innerHTML = "欢";
                    break;
                case 6:
                    Obj_Nian.innerHTML = "迎";
                    break;
                case 6:
                    Obj_Nian.innerHTML = "你";
                    break;
                default:
                    Obj_Nian.innerHTML = "Error";
                    break;
            }
            Is_First += 1
            if (Is_First == 7) {
                Is_First = 1
            }
            Color_Nian = "RGB(" + Math.floor(Math.random() * 100) + "%,"
                    + Math.floor(Math.random() * 100) + "%,"
                    + Math.floor(Math.random() * 100) + "%)"
            Obj_Nian.style.color = Color_Nian
            document.body.appendChild(Obj_Nian)
        }
        function Clean(evt) {
            evt = evt ? evt : window.event
            evt.srcObj = evt.srcElement ? evt.srcElement : evt.target
            if (evt.srcObj.tagName == "DIV"
                    && (evt.srcObj.innerHTML == "你" || evt.srcObj.innerHTML == "看"
                    || evt.srcObj.innerHTML == "我"
                    || evt.srcObj.innerHTML == "会"
                    || evt.srcObj.innerHTML == "写" || evt.srcObj.innerHTML == "字")) {
                evt.srcObj.innerHTML = ""
            }
        }
        document.onmousedown = function () {
            Is_Mouse_Down = true
        }
        document.onmousemove = Create
        document.onmouseup = function () {
            Is_Mouse_Down = false
        }
    </script>
    <script type="text/javascript">
        var fireworks = function () {
            this.size = 20;
            this.rise();
        }
        fireworks.prototype = {
            color: function () {
                var c = ['0', '3', '6', '9', 'c', 'f'];
                var t = [c[Math.floor(Math.random() * 100) % 6], '0', 'f'];
                t.sort(function () {
                    return Math.random() > 0.5 ? -1 : 1;
                });
                return '#' + t.join('');
            },
            aheight: function () {
                var h = document.documentElement.clientHeight - 250;
                return Math.abs(Math.floor(Math.random() * h - 200)) + 201;
            },
            firecracker: function () {
                var b = document.createElement('div');
                var w = document.documentElement.clientWidth;
                b.style.position = 'absolute';
                b.style.color = this.color();
                b.style.bottom = 0;
                b.style.left = Math.floor(Math.random() * w) + 1 + 'px';
                document.body.appendChild(b);
                return b;
            },
            rise: function () {
                var o = this.firecracker();
                var n = this.aheight();
                var c = this.color;
                var e = this.expl;
                var s = this.size;
                var k = n;
                var m = function () {
                    o.style.bottom = parseFloat(o.style.bottom) + k * 0.1 + 'px';
                    k -= k * 0.1;
                    if (k < 2) {
                        clearInterval(clear);
                        e(o, n, s, c);
                    }
                }
                o.innerHTML = '.';
                if (parseInt(o.style.bottom) < n) {
                    var clear = setInterval(m, 20);
                }
            },
            expl: function (o, n, s, c) {
                var R = n / 3, Ri = n / 6, Rii = n / 9;
                var r = 0, ri = 0, rii = 0;
                for (var i = 0; i < s; i++) {
                    var span = document.createElement('span');
                    var p = document.createElement('i');
                    var a = document.createElement('a');
                    span.style.position = 'absolute';
                    span.style.fontSize = n / 10 + 'px';
                    span.style.left = 0;
                    span.style.top = 0;
                    span.innerHTML = '*';
                    p.style.position = 'absolute';
                    p.style.left = 0;
                    p.style.top = 0;
                    p.innerHTML = '*';
                    a.style.position = 'absolute';
                    a.style.left = 0;
                    a.style.top = 0;
                    a.innerHTML = '*';
                    o.appendChild(span);
                    o.appendChild(p);
                    o.appendChild(a);
                }
                function spr() {
                    r += R * 0.1;
                    ri += Ri * 0.06;
                    rii += Rii * 0.06;
                    sp = o.getElementsByTagName('span');
                    p = o.getElementsByTagName('i');
                    a = o.getElementsByTagName('a');
                    for (var i = 0; i < sp.length; i++) {
                        sp[i].style.color = c();
                        p[i].style.color = c();
                        a[i].style.color = c();
                        sp[i].style.left = r * Math.cos(360 / s * i) + 'px';
                        sp[i].style.top = r * Math.sin(360 / s * i) + 'px';
                        sp[i].style.fontSize = parseFloat(sp[i].style.fontSize)
                                * 0.96 + 'px';
                        p[i].style.left = ri * Math.cos(360 / s * i) + 'px';
                        p[i].style.top = ri * Math.sin(360 / s * i) + 'px';
                        p[i].style.fontSize = parseFloat(sp[i].style.fontSize)
                                * 0.96 + 'px';
                        a[i].style.left = rii * Math.cos(360 / s * i) + 'px';
                        a[i].style.top = rii * Math.sin(360 / s * i) + 'px';
                        a[i].style.fontSize = parseFloat(sp[i].style.fontSize)
                                * 0.96 + 'px';
                    }
                    R -= R * 0.1;
                    if (R < 2) {
                        o.innerHTML = '';
                        o.parentNode.removeChild(o);
                        clearInterval(clearI);
                    }
                }

                var clearI = setInterval(spr, 20);
            }
        }
        window.onload = function () {
            function happyNewYear() {
                new fireworks();
            }

            setInterval(happyNewYear, 1000);
        }
    </script>
    <style type="text/css">
    </style>
</head>
<body>
<div style="width: 100%; height: 100%; background-image: url(/play/timg.gif);">
    <h3>
        <div align="center">小码哥火星分校
        </div>
    </h3>

    <table>
        <tr>
            <td>
                <video src="/play/mv1.mp4" controls="controls" autoplay="autoplay">
                </video>
            </td>
            <td>
                <video src="/play/mv2.mp4" controls="controls" autoplay="autoplay">
                </video>
            </td>
        </tr>
        <tr>
            <td>
                <MARQUEE onmouseover=this.stop() style="" onmouseout=this.start()
                         scrollAmount=1 direction=up width=700 height=200 border="0"
                         align="left">
                    <table align="left" border="1" width="50%">
                        <thead>
                        <tr>
                            <span style="color: red"><div style="font-size:23px">喜讯栏</div></span>
                        </tr>
                        <br/>
                        </thead>
                        <tbody>
                        <tr>热烈欢迎李明杰教授莅临我校</tr>
                        <br/>
                        <tr>热烈庆祝我校获得世界级java竞赛第一名</tr>
                        <br/>
                        <tr>我校第50分校成立</tr>
                        <br/>
                        <tr>欢迎世界首富米歇尔之子掩饰身份进入我校学习</tr>
                        <br/>
                        </tr>
                        </tbody>
                        <tfoot></tfoot>
                    </table>
                </marquee>
            </td>
            <td>
                <MARQUEE onmouseover=this.stop() style="" onmouseout=this.start()
                         scrollAmount=1 direction=up width=700 height=200 border="0"
                         align="left">
                    <table align="left" border="1" width="50%">
                        <thead>
                        <tr>
                            <span style="color: red"><div style="font-size:23px">通知</div></span>
                        </tr>
                        <br/>
                        </thead>
                        <tbody>
                        <tr>请王蜜同志上台演示</tr>
                        <br/>
                        <tr>请张慧尤不要再那么二笔了</tr>
                        <br/>
                        <tr>王宝宝挺萌的</tr>
                        <br/>
                        <tr>学校资金刚刚到了50个亿,请全体员工想想怎么腐败</tr>
                        <br/>
                        </tr>
                        </tbody>
                        <tfoot></tfoot>
                    </table>
                </marquee>
            </td>
        </tr>
    </table>

</div>
</body>
</html>