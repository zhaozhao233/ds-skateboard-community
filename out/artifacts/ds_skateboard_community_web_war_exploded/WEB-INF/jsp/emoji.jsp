<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="/static/js/jquery-3.4.1.js"></script>


</head>
<body>
<h1>窝头</h1>
<div id="openEmoji"><img src='/static/emoji/0.gif'/></div>
<div id="emojixx"></div>

<div id="emojidiv" style="display: none;background-color: white;height: 200px;width: 100px;border: 1px blue solid;">

</div>
<div style="border: 1px yellow solid;">
    123
</div>

<script>
    var ImgIputHandler = {
        facePath:[
            {faceName:"微笑",facePath:"0.gif"},
            {faceName:"撇嘴",facePath:"1.gif"},
            {faceName:"色",facePath:"2.gif"},
            {faceName:"发呆",facePath:"3.gif"},
            {faceName:"得意",facePath:"4.gif"},
            {faceName:"流泪",facePath:"5.gif"},
            {faceName:"害羞",facePath:"6.gif"},
            {faceName:"闭嘴",facePath:"7.gif"},
            {faceName:"大哭",facePath:"9.gif"}
        ]
    }

    loadEmoji();
    function loadEmoji() {
        var emojidiv = document.getElementById("emojidiv");
        alert(ImgIputHandler.facePath.length);
        for (var i = 0; i < ImgIputHandler.facePath.length; i++) {
            var imgdiv = document.createElement("div");
            imgdiv.style.float = "left";
            imgdiv.className = "emoji";
            imgdiv.innerHTML="<img emojiId="+i+" src='/static/emoji/"+i+".gif' />"
            emojidiv.append(imgdiv);
        }
        $("#openEmoji").bind("click",function () {

            // if ($("#emojidiv").slideDown){
            //     $("#emojidiv").slideToggle(0);
            // } else {
            //     $("#emojidiv").slideDown(5000);
            // }
            $("#emojidiv").slideToggle(100);

            $(".emoji").bind("click", function () {
                // alert("点击了表情"+$(this).children(":first").attr("emojiid"));
                alert("||" + $(this).html() + "||")
            })
        })
    }





</script>
</body>
</html>
