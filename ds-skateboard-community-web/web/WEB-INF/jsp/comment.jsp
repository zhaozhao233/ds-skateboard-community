<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--    <title>Title</title>--%>
<%--    <link rel="stylesheet" href="/static/layui/css/layui.css">--%>
<%--    <script src="/static/js/jquery-3.4.1.js"></script>--%>
<%--    <script src="/static/layui/layui.js"></script>--%>
<%--    <script src="/static/bootstrap-3.3.7/js/bootstrap.js"></script>--%>
<%--    <link rel="stylesheet" href="/static/bootstrap-3.3.7/css/bootstrap.css">--%>
<%--    <link rel="stylesheet" href="/static/css/user-dynamic.css">--%>

<%--                评论区--%>
<%--<div class="comment-div" style="border: 1px red solid;float: left;width: 700px;">--%>
<%--    <div>------------------------------------评论---------------------------------------------</div>--%>
<%--    <div class="publish-content-div" dynamicId="${dynamicId}"--%>
<%--         style="padding: 10px;border-radius:5px;background-color: #9acfea;">--%>
<%--        <div class="container" style="padding-left: 0px;">--%>
<%--            &lt;%&ndash;                                文本域&ndash;%&gt;--%>
<%--            <textarea class="comment-text" comment-level="1" name="dynamicContent"--%>
<%--                      style="float: left;border:0;border-radius:5px;background-color:rgba(241,241,241,.98);width: 500px;padding: 10px;resize: none;"--%>
<%--                      wrap="hard" placeholder="请输入内容">123123</textarea>--%>
<%--            <button class="publish-comment" style="float: left;">发表评论</button>--%>
<%--        </div>--%>
<%--        &lt;%&ndash;                                文本域额外功能div&ndash;%&gt;--%>
<%--        <div class="comment-function" style="border: 1px greenyellow solid">--%>
<%--            &lt;%&ndash;                        选择表情&ndash;%&gt;--%>
<%--            <span class="layui-icon layui-icon-face-smile-b comment-image"></span>--%>
<%--            <div class="comment-image-div"--%>
<%--                 style="float: left;display:none;width: 200px;height: 200px;position: absolute;border: 5px red solid;background-color: white;"></div>--%>
<%--        </div>--%>
<%--    </div>--%>
    <%--    =============================================== 循环显示评论--%>

    <c:forEach items="${comments}" var="comment">

        <%--            评论DIV--%>
        <div class="comment-div" commentID="${comment.dynamicCommentId}" style="border-radius: 5px;overflow: hidden;">
            <div style="">
                    <%--             用户信息的头像--%>
                <div style="float: left;">
                        <%--         头像框--%>
                    <div style="border-radius: 50%;overflow:hidden;float: left;">
                        <img style="object-fit: cover;width: 50px;height: 50px;"
                             src="/file/getFileSource?filename=${comment.userInfo.userImageUrl}">
                    </div>

                </div>
                    <%--                        评论内容--%>
                <div style="width: 550px;margin-left:10px;float: left;">
                        <%--            用户名字--%>
                    <div class="comment-user-name-div" userName="${comment.userInfo.userName}" style="">${comment.userInfo.userName}</div>
                        <%--    评论内容--%>
                    <div>评论内容：${comment.commentContent}</div>
                        <%--                        评论时间 & 点赞 & 回复--%>
                    <div>
                            <%--                评论时间--%>
                        <span>${comment.commentTime}</span>
                        <span style="margin-left:50px;">
                            <i class="layui-icon layui-icon-praise"></i><span>999</span>
                        </span>
                        <span class="read-reply" style="margin-left:50px;">
<%--                            <i class="glyphicon glyphicon-comment"></i>--%>
                            <span>查看回复</span>
                        </span>
                        <span class="reply-comment" style="margin-left:50px;">
<%--                            <i class="glyphicon glyphicon-comment"></i>--%>
                            <span>回复</span>
                        </span>
                    </div>

                </div>
                    <%--                        加一条横线把浮动的div包起来--%>
                <hr/>
            </div>

                <%--                        子评论div--%>
            <div class="child-comment-div" style="display: none;">

            </div>
                <%--                回复别人的评论的评论,输入框div--%>
            <div class="reply-content-div" style="display: none;padding: 10px;border-radius:5px;background-color: #9acfea;">
                <div class="container" style="padding-left: 0px;">
                        <%--                                文本域--%>
                    <textarea class="chile-comment-text" name="dynamicContent" ready-Comment-Id=""
                              style="float: left;border-radius:5px;background-color:rgba(241,241,241,.98);width: 500px;padding: 10px;resize: none;"
                              wrap="hard" placeholder="请输入内容">123123</textarea>
                    <button class="publish-chile-but" style="float: left;">评论别人的评论</button>
                </div>
                    <%--                                文本域额外功能div--%>
                <div class="comment-function" style="border: 1px greenyellow solid">
                        <%--                        选择表情--%>
                    <span class="layui-icon layui-icon-face-smile-b chile-comment-image"></span>
                    <div class="comment-image-div"
                         style="float: left;display:none;width: 200px;height: 200px;position: absolute;border: 5px red solid;background-color: white;"></div>
                </div>
            </div>
        </div>
        <%--                    横线隔开，显示吓一条评论--%>
        <div style="border-bottom: 2px black solid"></div>
    </c:forEach>

<%--</div>--%>

<script>

    // 点击显示隐藏表情选择框
    $(".chile-comment-image").click(function () {
        var $parents = $(this).parents(".comment-function:first");
        var $find = $($parents).find(".comment-image-div");
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

    // 点击回复（需要有父评论ID）
    // 显示评论评论框
    $(".reply-comment").click(function () {
        var $commentDiv = $(this).parents(".comment-div:first");    // 评论div
        var $dynamicDiv = $(this).parents(".dynamic-body:first");   // 动态div
        var commentId = $commentDiv.attr("commentID");  // 要回复的评论ID
        var dynamicId = $dynamicDiv.attr("dynamicId");  // 要评论的动态ID
        var userName = $commentDiv.find(".comment-user-name-div").attr("userName"); // @用户名名字
        var $commentText = $commentDiv.find(".chile-comment-text");   // 输入框对象
        $commentText.attr("placeholder","回复 @"+userName+":");
        $commentText.attr("ready-Comment-Id",commentId);
        alert("评论的动态："+dynamicId+"要评论的评论Id为："+commentId+"评论目标的用户名字为:"+userName)
        var $find = $commentDiv.find(".reply-content-div");
        if ($find.is(':hidden')) {
            $find.show();
        }
    })

    publishChileComment();
    // 发表评论别人的评论
    function publishChileComment() {
        $(".publish-chile-but").click(function () {
            var $commentDiv = $(this).parents(".comment-div:first");    // 评论div
            var $dynamicDiv = $(this).parents(".dynamic-body:first");   // 动态div
            var commentId = $commentDiv.attr("commentID");  // 要回复的评论ID
            var dynamicId = $dynamicDiv.attr("dynamicId");  // 要评论的动态ID
            var userName = $commentDiv.find(".comment-user-name-div").attr("userName"); // @用户名名字
            var $commentText = $commentDiv.find(".chile-comment-text");   // 输入框对象
            var commentContent = $commentText.val();
            console.log("评论内容"+commentContent+"评论的动态："+dynamicId+"要评论的评论Id为："+commentId+"评论目标的用户名字为:"+userName)
            var $childCommentDiv = $commentDiv.find(".child-comment-div");  // 子评论div
            $.ajax({
                method:"POST",
                url:"/user/publishComment",
                data:{
                    commentContent:commentContent,
                    dynamicId:dynamicId,
                    replyCommentId:commentId
                }
            }).done(function (result) {
                alert("发表评论的评论异步成功");
                // 清空输入框
                $commentText.val("")
                $commentText.attr("placeholder","请输入内容")
                // 显示子评论
                ajaxDynamicChildComment(commentId,$childCommentDiv);
            }).fail(function () {
                alert("发表评论的评论异步失败")
            })
        })


    }


    showReplyDiv();
    // 点击“查看回复”,显示回复div（异步获取子评论页面）
    function showReplyDiv() {
        $(".read-reply").click(function () {
            // var $dynamicId = $(this).parents(".dynamic-body").attr("dynamicId");    // 发表动态ID
            var $commentDiv = $(this).parents(".comment-div:first");
            var $childCommentDiv = $commentDiv.find(".child-comment-div");  // 子评论div
            var commentId = $commentDiv.attr("commentId");  // 评论ID
            // console.log("获取到的发表动态id为：" + $dynamicId);
            console.log("需要获取要查看子评论的评论ID"+commentId)

            if ($childCommentDiv.is(':hidden')) {
                ajaxDynamicChildComment(commentId,$childCommentDiv);
                // $childCommentDiv.show();
            } else {
                $childCommentDiv.hide();
            }
        })
    }

    // 异步获取子评论jsp
    function ajaxDynamicChildComment(commentId, $childCommentDiv) {
        $.ajax({
            method: "POST",
            url: "/user/childComment",
            data: {
                commentId: commentId
            }
        }).done(function (result) {
            console.log("获取子评论异步成功")
            $childCommentDiv.html(result);
            $childCommentDiv.show();        //显示
        }).fail(function () {
            alert("获取子评论异步失败")
        })
    }

    emojiload();

    function emojiload() {
        var ImgInputHandler = {
            facePath: [
                {faceName: "[微笑]", facePath: "0.gif"},
                {faceName: "[撇嘴]", facePath: "1.gif"},
                {faceName: "[色]", facePath: "2.gif"},
                {faceName: "[发呆]", facePath: "3.gif"},
                {faceName: "[得意]", facePath: "4.gif"},
                {faceName: "[流泪]", facePath: "5.gif"},
                {faceName: "[害羞]", facePath: "6.gif"},
                {faceName: "[闭嘴]", facePath: "7.gif"},
                {faceName: "[大哭]", facePath: "9.gif"},
            ]
        }
        // 显示表情的对象
        var emojidiv = document.getElementsByClassName("comment-image-div");
        // 获取所有的表情框对象
        var $emoji = $(".comment-image-div");

        for (var i = 0; i < ImgInputHandler.facePath.length; i++) {
            var $newDiv = $("<div style='float: left;margin: 1px;' class=comment-emoji >" +
                "<img emojicode=" + ImgInputHandler.facePath[i].faceName + " src='/static/emoji/" + ImgInputHandler.facePath[i].facePath + "' />" +
                "</div>")
            $emoji.append($newDiv);
        }
    }

    // 点击添加表情
    $(".comment-emoji").click(function () {
        // alert("点击添加表情")
        var $attr = $(this).children('img').attr("emojicode");
        var $commentText = $(this).parents(".comment-div:first").find(".chile-comment-text");
        // alert($commentText.attr("class"))
        $commentText[0].focus();
        // 将jquery对象转换为DOM元素
        // var commentDiv = $commentText[0];
        // 这种办法只能用于 input textarea !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        var selectionStart = $commentText[0].selectionStart;
        var selectionEnd = $commentText[0].selectionEnd;
        var commentval = $commentText.val();
        var val = $attr;

        // 在输入框原来的内容（在光标选择开始和结束之间插入内容）123'45'6 -> 123xx6
        $commentText.val(commentval.substring(0, selectionStart) + val + commentval.substring(selectionEnd, commentval.length));
        $commentText[0].setSelectionRange(selectionStart + val.length, selectionStart + val.length)

    })
</script>

