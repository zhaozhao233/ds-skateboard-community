<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--    <link rel="stylesheet" href="/static/layui/css/layui.css">--%>
<%--    <script src="/static/js/jquery-3.4.1.js"></script>--%>
<%--    <script src="/static/layui/layui.js"></script>--%>
<%--    <script src="/static/bootstrap-3.3.7/js/bootstrap.js"></script>--%>
<%--    <link rel="stylesheet" href="/static/bootstrap-3.3.7/css/bootstrap.css">--%>
<%--    <link rel="stylesheet" href="/static/css/user-dynamic.css">--%>
<%--    发表动态的 js--%>
<script src="/static/js/user-dynamic.js"></script>
<c:forEach items="${userDynamic}" var="dynamic">
    <div class="dynamic-body" style="background-color: white;margin:2px 0px;float: left;
    border-radius: 5px;width: 700px;" dynamicId="${dynamic.userDynamicId}">
            <%--            头像圆框--%>
        <div class="head-image-div" style="margin: 10px 0px 0px 10px;border-radius: 50%;overflow: hidden;float: left;">
            <a href="#">
                <img style="object-fit: cover;width: 50px;height: 50px"
                     src="/file/getFileSource?filename=${dynamic.userInfo.userImageUrl}">
            </a>
        </div>
            <%--            名字和日期--%>
        <div class="user-name-div" style="float: left;">
            <div class="user-name" style="font-size: 15px;margin:20px 0px 0px 15px;">${dynamic.userInfo.userName}</div>
            <div class="dynamic-time" style="font-size: 12px; border-radius: 5px; margin: 10px 0px 0px 15px;color: gray;">${dynamic.dynamicTime}</div>
        </div>
        <div class="delete-dynamic" style="float: right">
            <button class="delete-dynamic-but" dynamicid="${dynamic.userDynamicId}">X</button>
        </div>
            <%--                帖子的具体内容--%>
        <div class="dynamic-content-div" style="margin: 10px 20px 10px 80px;float: left;">
                <%--帖子内容--%>
            <div class="dynamic-content" style="width: 550px;font-size: 14px;">
                <div style="">${dynamic.dynamicContent}</div>
                    <%--                放图片的div--%>
                <div class="dynamic-div" style="margin: 5px 0px;">
                    <c:forEach items="${dynamic.dynamicImage}" var="image">
                        <img style="object-fit: cover;width: 150px;height: 150px;border-radius:5px;margin: 2px;"
                             class="dynamic-div-img" src="/file/getFileSource?filename=${image.dynamicImageUrl}">
                    </c:forEach>
                </div>
            </div>
                <%--点赞、收藏、转发按钮--%>
            <div class="like-div" style="margin: 20px 0px;">
                <div style="float: left;font-size: 5px;">
                    <span class="open-comment">
                        <button type="button" class="layui-btn layui-btn-primary">
                            <span class="layui-icon layui-icon-reply-fill"></span>评论
                        </button>
                    </span>
                    <span>
                        <button type="button" class="layui-btn layui-btn-normal">
                            <span class="layui-icon layui-icon-praise"></span>点赞
                        </button>
                    </span>
                </div>
            </div>
        </div>
            <%--                评论区--%>
        <div class="comment-div" style="display:none;float: left;width: 700px;">
            <div>------------------------------------评论---------------------------------------------</div>
            <div class="publish-content-div"
                 style="padding: 10px;border-radius:5px;background-color: #9acfea;">
                <div class="container" style="padding-left: 0px;">
                        <%--                                文本域--%>
                    <textarea class="comment-text" name="dynamicContent" commentID=""
                              style="float: left;border:0;border-radius:5px;background-color:rgba(241,241,241,.98);width: 500px;padding: 10px;resize: none;"
                              wrap="hard" placeholder="请输入内容">123123</textarea>
                    <div class="publish-comment" style="float: left;border: 3px skyblue solid;">发表评论</div>
                </div>
                    <%--                                文本域额外功能div--%>
                <div class="comment-function" style="border: 1px greenyellow solid">
                        <%--                        选择表情--%>
                    <span class="layui-icon layui-icon-face-smile-b comment-image-span"></span>
                    <div class="comment-image-div"
                         style="float: left;display: none;width: 200px;height: 200px;position: absolute;background-color: white;"></div>
                </div>
            </div>
                <%--            本动态的评论DIV--%>
            <div class="this-dynamic-content-div">

            </div>
        </div>
    </div>
</c:forEach>
<script>

    // 发表评论
    // 发表评论（针对动态的评论，评论级别为1）
    // $(".publish-comment").click(function () {
    //     alert("点击发表评论"+$(this).html());
    //     // 评论内容
    //     var val = $(this).parent().find(".comment-text").val();
    //     // 评论的动态ID
    //     var dynamicId = $(this).parents(".publish-content-div").attr("dynamicId");
    //     // 回复的评论
    //     var replyCommentId = "";
    //     // replyCommentId = $(this).parent().find(".comment-text").attr("ready-Comment-Id");
    //
    //     alert("评论内容：" + val + "评论的动态ID" + dynamicId + "回复ID" + replyCommentId);
    //     $.ajax({
    //         method: "POST",
    //         url: "/user/publishComment",
    //         data: {
    //             'commentContent': val,              //评论内容
    //             'dynamicId': dynamicId,             //发表动态ID
    //             'replyCommentId': replyCommentId    //（可能有）要回复的评论
    //         }
    //     }).done(function (result) {
    //         alert("发表评论异步成功" + result)
    //     }).fail(function () {
    //         alert("发表评论异步失败")
    //     })
    // })

    // 打开所有评论
    $(".open-comment").click(function () {
        var $dynamicBody = $(this).parents(".dynamic-body");// 动态div
        var $find = $dynamicBody.find(".comment-div");      // 评论div
        if ($find.is(':hidden')) {
            getCommentForDynamic($dynamicBody);
            $find.show();
        } else {
            $find.hide();
        }
    })

    // 点击打开评论
    function getCommentForDynamic($dynamicBody) {
        // var $dynamicBody = $(this).parents(".dynamic-body");    // 动态DIV
        var $dynamicContentDiv = $dynamicBody.find(".this-dynamic-content-div");    // 隐藏显示和 ajax那边用的不是一个对象的话，会有问题
        var dynamicId = $dynamicBody.attr("dynamicId");
        ajaxDynamicComment(dynamicId,$dynamicContentDiv);  //异步添加本动态的评论到 评论div

        console.log("打开评论,获取的动态id为"+dynamicId);
    }

    // 异步请求本动态评论，添加到评论div中
    function ajaxDynamicComment(dynamicId,$dynamicContentDiv) {
        $.ajax({
            method:"POST",
            url:"/user/comment",
            data:{
                dynamicId:dynamicId
            }
        }).done(function (result) {
            $dynamicContentDiv.html(result);
        }).fail(function () {
            alert("异步失败")
        })
    }

    function listDynamicComment(dynamicId) {
        $.ajax({
            method:"POST",
            url:"/user/listDynamicComment",
            data:{
                dynamicId:dynamicId
            }
        }).done(function () {
            alert("获取评论异步成功")
        }).fail(function () {
            alert("获取评论异步失败")
        })
    }


    // 点击显示隐藏表情选择框
    $(".comment-image-span").click(function () {
        // alert("显示隐藏表情框")
        // 注意：应为子元素的class名字有相同的，小心使用
        var $parents = $(this).parents(".comment-div:first");
        var $find = $($parents).find(".comment-image-div:first");
        var boolean = false;
        if ($find.is(':hidden')) {
            boolean = true;
        }
        $(".comment-image-div").hide();//先隐藏所有的表情选择框
        $("#emojidiv").slideUp(100);
        if (boolean) {
            $find.show();
        } else {
            $find.hide();
        }
    })

    // 发表评论(针对动态的评论，评论级别为1)
    $(".publish-comment").click(function () {
        alert("点击发表评论");
        // 评论内容
        var val = $(this).parent().find(".comment-text").val();
        // 评论的动态ID
        var $dynamicBody = $(this).parents(".dynamic-body:first")
        var dynamicId = $dynamicBody.attr("dynamicId");
        // 回复的评论
        var replyCommentId = $(this).parent().find(".comment-text").attr("commentID");

        // var $dynamicBody = $(this).parents(".dynamic-body");
        // var $find = $dynamicBody.find(".comment-div");
        alert("评论内容："+val+"评论ID"+dynamicId+"回复ID"+replyCommentId);
        $.ajax({
            method:"POST",
            url:"/user/publishComment",
            data:{
                'commentContent':val,
                'dynamicId':dynamicId,
                'replyCommentId':replyCommentId
            }
        }).done(function () {
            alert("发表评论异步成功")
            // 刷新评论
            getCommentForDynamic($dynamicBody);
        }).fail(function () {
            alert("发表评论异步失败")
        })
    })

    emojiload();
    function emojiload() {
        var ImgInputHandler = {
            facePath: [
                {faceName: "[[微笑]]", facePath: "0.gif"},
                {faceName: "[[撇嘴]]", facePath: "1.gif"},
                {faceName: "[[色]]", facePath: "2.gif"},
                {faceName: "[[发呆]]", facePath: "3.gif"},
                {faceName: "[[得意]]", facePath: "4.gif"},
                {faceName: "[[流泪]]", facePath: "5.gif"},
                {faceName: "[[害羞]]", facePath: "6.gif"},
                {faceName: "[[闭嘴]]", facePath: "7.gif"},
                {faceName: "[[大哭]]", facePath: "9.gif"},
            ]
        }
        // 显示表情的对象
        var emojidiv = document.getElementsByClassName("comment-image-div");
        // 获取所有的表情框对象
        var $emoji = $(".comment-image-div");

        for (var i = 0; i < ImgIputHandler.facePath.length; i++) {
            var $newDiv=$("<div style='float: left;margin: 1px;' class=comment-emoji >" +
                "<img emojicode=" + ImgIputHandler.facePath[i].faceName + " src='/static/emoji/" + ImgIputHandler.facePath[i].facePath + "' />"+
                "</div>")
            $emoji.append($newDiv);
        }
    }

    // 点击添加表情到输入框中
    $(".comment-emoji").click(function () {
        // alert("点击添加表情")
        var $attr = $(this).children('img').attr("emojicode");
        var $commentText = $(this).parents(".dynamic-body:first").find(".comment-text");
        alert($commentText.attr("class"))
        $commentText[0].focus();
        // 将jquery对象转换为DOM元素
        // var commentDiv = $commentText[0];
        // 这种办法只能用于 input textarea !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        var selectionStart = $commentText[0].selectionStart;
        var selectionEnd = $commentText[0].selectionEnd;
        var commentval = $commentText.val();
        var val = "["+$attr+"]";

        // 在输入框原来的内容（在光标选择开始和结束之间插入内容）123'45'6 -> 123xx6
        $commentText.val(commentval.substring(0,selectionStart)+'['+$attr+']'+commentval.substring(selectionEnd,commentval.length));
        $commentText[0].setSelectionRange(selectionStart+val.length,selectionStart+val.length)

    })


</script>





