<%--
  Created by IntelliJ IDEA.
  User: 昭昭
  Date: 2019-12-05
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DS滑板社区注册</title>

<%--    <script src="/static/js/jquery-3.4.1.js"></script>--%>
<%--    <link href="/static/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">--%>

<%--    cnd--%>
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <style>
        ul li {
            padding: 0px 5px;
            width: 150px;
            text-align: center;
        }

        body {
            background-color: rgba(0, 100, 200, 0.5);
        }
    </style>
</head>
<body>
<div class="panel panel-default" style="width:700px; margin:auto; margin-top: 7%">
    <div class="panel-body">
        <ul class="nav nav-tabs">
            <li id="myphone" class="active">
                <a onclick="Phone()"><h4>手机注册</h4></a></li>
            <li id="myemail">
                <a onclick="Email()"><h4>邮箱注册</h4></a></li>
        </ul>
        <div id="shoujizhuce" style="margin-left: 15%;margin-top: 5%">
            <!-- 登录表单 -->
            <%--            form 表单以前的规定标配有一个提交按钮，所以把下面的第一个button按钮识别成提交按钮，导致了表单的提交，就导致了页面的刷新，导致了异步请求还每到 .done 方法就刷新页面了--%>
            <%--            <form style="margin-left: 15%;margin-top: 5%">--%>
            <div class="form-group">
                <label for="user" name="phone" stype="display:inline;">手机号码：</label>
                <input type="text" class="form-control" id="user" placeholder="请输入您的手机号码"
                       style="display:inline;width:230px;" autocomplete="off"/><span id="user-span"
                                                                                     style="color: red; font-size: 10px"></span>
            </div>
            <div class="form-group">
                <label for="password" style="display:inline;">设置密码：</label>
                <input type="password" class="form-control" id="password" placeholder="(以字母开头，6-18个字符组成)"
                       style="display:inline;width:230px;" autocomplete="off"/><span id="password-span"
                                                                                     style="color: red; font-size: 10px"></span>
            </div>
            <div class="form-group">
                <label for="password2" style="display:inline;">确认密码：</label>
                <input type="password" class="form-control" id="password2" placeholder="再次输入密码"
                       style="display:inline;width:230px;" autocomplete="off"/><span id="password2-span"
                                                                                     style="color: red; font-size: 10px"></span>
            </div>
            <div class="form-group">
                <label for="code" style="display:inline;">验证码：</label>
                <input type="text" class="form-control" id="code" placeholder="输入短信验证码"
                       style="display:inline;width:125px;margin-left: 13px;" autocomplete="off"/>
                <button class="btn btn-default" id="getCode" style="margin-left: 5px">获取验证码</button>
            </div>
            <button type="submit" class="btn btn-primary" id="validate" style="width:300px">
                注册
            </button>
            <%--            </form>--%>
            <br>
            <br>
            <a href="${pageContext.request.contextPath}/user/login" style="margin-left: 180px">以有账号？点击登录</a>
            <br>
            <br>
        </div>
        <div id="youxianzhuce" style="display: none">
            <h1>邮箱功能还未解锁</h1>
        </div>
    </div>
</div>



<script>
    function Phone() {
        $("#shoujizhuce").css("display", "block");
        $("#myphone").addClass("active");
        $("#youxianzhuce").css("display", "none");
        $("#myemail").removeClass("active");
    }

    function Email() {
        $("#shoujizhuce").css("display", "none");
        $("#myphone").removeClass("active");
        $("#youxianzhuce").css("display", "block");
        $("#myemail").addClass("active");
    }

    // 输入框失去焦点
    $("input").blur(function () {
        if ($(this).val() == '' || $(this).val() == undefined) {
            $(this).css("border-color", "red");
            $(".Error-prompt").css("border-color", "red");
        }
    });
    // 输入框获取焦点
    $("input").focus(function () {
        $("#errorTips").hide();
        $(this).css("border-color", "#cccccc");
    });

    // 正则表达式检查手机号是否符合格式
    function checkPhone() {
        var phone = document.getElementById('user').value.trim();
        if (!(/^1(3|4|5|6|7|8|9)\d{9}$/.test(phone))) {
            return false;
        }
        return true;
    }

    // 正则表达式检查密码是否符合格式
    function checkPassword() {
        var password = document.getElementById('password').value.trim();
        if (!(/^[a-zA-Z]\w{5,17}$/.test(password))) {
            return false;
        }
        return true;
    }

    // 手机号码输入框失去焦点
    $("#user").blur(function () {
        if (!checkPhone()) {
            // 如果手机号码不符合格式
            $("#user-span").text("手机号长度11位，以13/14/15/16/17/18/19开头")
        } else {
            $("#user-span").text("");
        }
    })
    // 密码输入框失去焦点
    $("#password").blur(function () {
        if (!checkPassword()) {
            // 如果手机号码不符合格式
            $("#password-span").text("以字母开头，长度在6~18之间")
        } else {
            $("#password-span").text("")
        }
    })
    // 再次输入密码框失去焦点
    $("#password2").blur(function () {
        if ($("#password2").val() === $("#password").val()) {
            $("#password2-span").text("")
        } else {
            // 如果手机号码不符合格式
            $("#password2-span").text("两次输入的密码不相同");
        }
    })
    //发送验证码
    $("#getCode").click(function () {
        console.log("-- 点击发送验证码 --")
        if (checkPhone()) {
            $.ajax({
                url: "${pageContext.request.contextPath}/user/checkUserByPhone",
                method: "post",
                data: {
                    "phone": $("#user").val()
                }
            }).done(function (data) {
                if (data === 'true') {
                    console.log("--用户为新用户")
                    $.ajax({
                        url: "${pageContext.request.contextPath}/user/sendVerificationCode",//ajax提交表单
                        data: {
                            "phone": $("#user").val()
                        },
                        method: "post"
                    }).done(function (data) {
                        if (data === 'true') {
                            console.log("-- 验证码已发送 --");
                            // 发送验证码按钮开始倒计时
                            time(this);
                        } else {
                            alert("发送失败");
                        }
                    }).fail(function (ex) {
                        console.log("-- 发送验证码异步请求有误 --")
                    }).always(function () {

                    });
                } else {
                    console.log("用户已经注册过了");
                    $("#user-span").text("该用户已经注册过了～")
                }

            }).fail(function (data) {
                console.log("验证用户是否为第一次注册异步异常！")
            })
        } else {
            $("#user-span").text("请先输入手机号码")
        }

    });

    // 点击注册按钮
    $("#validate").click(function () {
        alert("点击注册按钮")
        if ($("#user").val().trim() != "" && $("#password").val().trim() != "" && $("#code").val().trim() != "") {
            var code = $("#code");
            if (code.val() == '') {
                code.css("border", "1px solid red");
                return;
            }
            // 验证手机验证码是否正确
            $.ajax({
                url: "${pageContext.request.contextPath}/user/checkVerificationCode",
                data: {
                    "code": $("#code").val()
                },
                type: "post"
            }).done(function (data) {
                if (data === 'true') {
                    console.log("-- 验证码验证成功! --");
<%--                    &lt;%&ndash;// 查看是否以前注册过&ndash;%&gt;--%>
<%--                    &lt;%&ndash;$.ajax({&ndash;%&gt;--%>
<%--                    &lt;%&ndash;    url: "${pageContext.request.contextPath}/user/checkUserByPhone",&ndash;%&gt;--%>
<%--                    &lt;%&ndash;    method: "post",&ndash;%&gt;--%>
<%--                    &lt;%&ndash;    data: {&ndash;%&gt;--%>
<%--                    &lt;%&ndash;        "phone": $("#user").val()&ndash;%&gt;--%>
<%--                    &lt;%&ndash;    }&ndash;%&gt;--%>
<%--                    &lt;%&ndash;}).done(function (data) {&ndash;%&gt;--%>
<%--                    &lt;%&ndash;    console.log("-- 验证用户是否注册过完成 --")&ndash;%&gt;--%>
<%--                        if (data === "true") {--%>
<%--                            console.log("-- 用户没有注册过 --")--%>
                            // 把新用户的基本信息写到数据库里面
                            $.ajax({
                                url: "${pageContext.request.contextPath}/user/userLogon",
                                method: "post",
                                data: {
                                    "userPhone": $("#user").val().trim(),
                                    "password": $("#password").val().trim()
                                }
                            }).done(function (data) {
                                if (data === "true") {
                                    alert("添加新用户成功");
                                    window.location.href = "/user/homepage";
                                } else {
                                    alert("添加用户失败");
                                }
                            }).fail(function () {
                                console.log("-- 将用户数据添加到数据库异步请求出错 --")
                            })
                    //     } else {
                    //         $("#user-span").text("该用户已经注册过了")
                    //         console.log("-- 用户已经注册过了 --")
                    //     }
                    // }).fail(function () {
                    //     console.log("-- 验证用户是否注册过的异步请求中有 Error --")
                    // })
                } else {
                    console.log("验证码错误");
                }
            }).fail(function () {
                console.log("发送失败请重新发送");
                // window.location.href = "./MyJsp.jsp";
            }).always(function () {

            })
        }
    })

    // 发送验证码倒计时
    var wait = 60;
    function time(obj) {
        if (wait == 0) {
            $("#getCode").removeAttr("disabled");
            $("#getCode").text("重新获取");
            wait = 60;
        } else {
            console.log(wait);
            $("#getCode").attr("disabled", "true");
            $("#getCode").text(wait + "s")
            wait--;
            setTimeout(function () {//倒计时的方法
                time(obj);
            }, 1000);        //1s
        }
    }

</script>
</body>
</html>
