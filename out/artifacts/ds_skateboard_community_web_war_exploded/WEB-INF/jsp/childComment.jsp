<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:forEach items="${chileComment}" var="comment">
    <div class="child-comment-div" commentID="${comment.dynamicCommentId}"
         style="margin-left: 65px;border: 1px gainsboro solid">
        <div style="">
                <%--             用户信息的头像--%>
            <div style="border: 2px white solid;float: left;">
                    <%--         头像框--%>
                <div style="border-radius: 50%;overflow:hidden;border: 1px gray solid;float: left;">
                    <img style="object-fit: cover;width: 20px;height: 20px;"
                         src="/file/getFileSource?filename=${comment.userInfo.userImageUrl}">
                </div>

            </div>
                <%--                        评论内容--%>
            <div style="width: 550px;margin-left:10px;float: left;">
                    <%--            用户名字--%>
                <div class="child-comment-username" userName="${comment.userInfo.userName}" style="">${comment.userInfo.userName}</div>
                    <%--    评论内容--%>
                <div>评论内容：${comment.commentContent}</div>
                    <%--                        评论时间 & 点赞 & 回复--%>
                <div>
                        <%--                评论时间--%>
                    <span>${comment.commentTime}</span>
                    <span style="margin-left:50px;">
                    <i class="layui-icon layui-icon-praise"></i><span>999</span>
                </span>
                    <span class="reply-chile-comment" style="margin-left:50px;">
                    <span>回复</span>
                </span>
                </div>
            </div>
                <%--                        加一条横线把浮动的div包起来--%>
            <hr/>
        </div>
    </div>
</c:forEach>

<script>
    // 点击回复显示回复框，并改变回复对象
    $(".reply-chile-comment").click(function () {
        var $childCommentDiv = $(this).parents(".child-comment-div");
        var commentId = $childCommentDiv.attr("commentId");
        var userName = $childCommentDiv.find(".child-comment-username").attr("userName");
        console.log("评论ID为："+commentId+"用户名字为"+userName);
        var $commentDiv = $(this).parents(".comment-div:first");
        var $replyContentDiv = $commentDiv.find(".reply-content-div");
        var $childCommentText = $commentDiv.find(".chile-comment-text");
        $childCommentText.attr("placeholder","回复 @"+userName+":");
        $childCommentText.attr("ready-Comment-Id",commentId);
        if ($replyContentDiv.is(":hidden")) {
            $replyContentDiv.show();
        }

    })


</script>

