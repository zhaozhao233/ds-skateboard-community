<%--
  Created by IntelliJ IDEA.
  User: 昭昭
  Date: 2019-12-09
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>skateboard登录</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/js/jquery-3.4.1.js"></script>
    <script src="/static/layui/layui.js"></script>
    <script src="/static/bootstrap-3.3.7/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.7/css/bootstrap.css">

</head>
<body>

<%-- 最外面的div--%>
<div style="background-color: #00FF00">
    <%--    上面的div--%>

    <div style="height: 38.2%;background-color: #6c8561;text-align: center;">

<%--        <video autoplay="autoplay" style="margin: auto;width: 60%;height: 100%;" controls loop="loop">--%>
<%--            <source src="/getFileSource?filename=skateboard_video/skate_first.mp4" type="video/mp4">--%>
<%--            <source src="/getFileSource?filename=skateboard_video/skate_second.mp4" type="video/ogg">--%>
<%--            您的浏览器不支持Video标签。--%>
<%--        </video>--%>
    </div>

    <%--    下面的div--%>
    <div style="background-color: red;">

        <%--        右边的div--%>
        <div style="background-color:gainsboro;left: 50%;width: 50%; position:fixed;">

            <form action="${pageContext.request.contextPath}/Login" method="post">
                <div class="modal-body">
                    <%--                    输入账号--%>
                    <div class="input-group">
                        <span class="input-group-addon ">
                            <span class="glyphicon glyphicon-user"></span>
                        </span>
                        <input type="text" name="uname" class="form-control" placeholder="手机号/邮箱">
                    </div>
                    <br>
                    <%--                    输入密码--%>
                    <div class="input-group">
                        <span class="input-group-addon">
                            <span class="glyphicon glyphicon-lock"></span>
                        </span>
                        <input type="password" name="upwd" class="form-control" placeholder="密码">
                    </div>
                    <div id="zhucediv" style="text-align:right; padding:20px 0px 0px 0px; margin-left:5px">
                <span style="float:left;">
                    <input type="checkbox" value="rememberMe">记住我
                </span>
                        <a href="${pageContext.request.contextPath}/user/logon">没有账号？注册一个</a>
                    </div>
                </div>
                <div id="errorTips"
                     style="display: none;color: red;border: 1px solid red; border-radius: 3px;margin: 5px;padding: 1px 8px;text-align: left;">
                    账号密码错误
                </div>
                <div class="modal-footer">
                    <button type="button" onclick="login()" class="btn btn-primary">
                        登录
                    </button>
                </div>
            </form>
        </div>
        <%--        右边的div--%>


        <%--        左边的div--%>
        <div style="background-color: blueviolet;width: 50%;">
            <div style="margin: auto;width: 80%;background-color: purple">
                123123123123123123123123123123
                123123123123123123123123123123
                123123123123123123123123123123
                123123123123123123123123123123
                123123123123123123123123123123
                123123123123123123123123123123
            </div>
        </div>
        <%--        左边的div--%>
        <%--    下面的div--%>
    </div>
    <%--最外面的div--%>
</div>


<script>
    // 失去焦点
    $("input").blur(function () {
        if ($(this).val() == '' || $(this).val() == undefined) {
            $(this).css("border-color", "red");
            $(".Error-prompt").css("border-color", "red");

            // $("#errorTips").css({"display": "inline"})
        }

    });
    // 获取焦点
    $("input").focus(function () {
        // $("#errorTips").hide();
        $(this).css("border-color", "#cccccc");
    });

    // 登录请求
    function login() {
        if ($("input[name=uname]").val() != "" && $("input[name=upwd]").val() != "") {
            $.ajax({
                method:"POST",
                url:"${pageContext.request.contextPath}/user/userLogin",
                data:{
                    "userName":$("input[name=uname]").val().trim(),
                    "userPassword":$("input[name=upwd]").val().trim()
                }
            }).done(function (data) {
                if (data === "true") {
                    console.log("账号密码正确，前往个人主页");
                    window.location.href="/user/homepage";
                } else {
                    console.log("账号密码错误！")
                }
            }).fail(function (data) {
                console.log("账号密码登录异步请求异常")
            })
        }
    }
</script>
</body>
</html>
