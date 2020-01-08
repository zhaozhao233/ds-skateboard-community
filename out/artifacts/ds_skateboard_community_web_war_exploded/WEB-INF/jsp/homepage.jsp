<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人主页</title>
    <link rel="stylesheet" href="/static/layui/css/layui.css">
    <script src="/static/js/jquery-3.4.1.js"></script>
    <script src="/static/layui/layui.js"></script>
    <script src="/static/bootstrap-3.3.7/js/bootstrap.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.7/css/bootstrap.css">
<%--    发表动态的样式--%>
<%--    <link rel="stylesheet" href="/static/css/user-dynamic.css">--%>


<%--    安装 axios--%>
<%--    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>--%>

    <style>
        /*小表情*/
        .emoji {
            /*border: 1px red solid;*/
            margin: 1px;
        }
        .uploadimage {
            object-fit: cover;
            width: 100px;
            height: 100px;
            border-radius:5px;
            margin: 2px;
        }

    </style>


</head>
<body style="background-color: #f4f5f7;overflow: auto;">
<%--最外面的div--%>
<div style="width: max-content;margin:auto;display: block">
    <div style="margin-bottom: 5px; background-color: #adadad; ">
        <button style="" type="button" class="btn btn-default">
            <a href="/user/deleteDynamic?imageId=1">登录</a>
        </button>
    </div>
<%--    左边DIV--%>
    <div style="border: 1px black solid;width:160px;float: left">

    </div>
<%--    右边DIV--%>
    <div style="border: 1px black solid;width: 160px;float:right">

    </div>
    <div style="border: 1px black solid;width: 880px;float: left;margin:0px 10px;">
        <%--最大的，上部分的div--%>
        <div style="margin: auto; width:875px; height: 250px; border: 1px solid blueviolet;">
            <%--    中间放背景图片的div--%>
            <div style="background-image: url('/img/beijing1.jpg'); background-size:cover; width:100%;height: 100%;"
                 id="xxx">
                <%--        用户信息上面空白地方div--%>
                <div style="color: white; padding: 160px 0px 0px 20px;">
                    <%--            显示用户基本信息的div--%>
                    <div id="wodetouxiang" style="float: left;border-radius: 10px;">
                        <%--                头像框div--%>
                        <div style="width: 80px;height: 80px;border-radius: 50%;border: 2px solid white;overflow: hidden;float: left">
                            <a data-toggle="modal" data-target="#myModal">
                                <img style="width: 100%;height: 100%;"
                                     src="/file/getFileSource?filename=${user.userImageUrl}">
                            </a>
                        </div>
                        <%--                除了头像框的右部分div--%>
                        <div style="float: left;">
                            <%--                    放用户名字的div--%>
                            <div style="font-size: 22px;margin:10px 0px 0px 20px;"><b>${user.userName}</b>
                                <span class="layui-badge layui-bg-blue">LV${user.userSkateGrade}</span>
                            </div>
                            <%--                    放个性签名的div--%>
                            <div style=" border-radius: 5px; width:700px;margin: 10px 0px 0px 20px;">
                                编辑个性签名
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--最大的，导航栏div--%>
        <div style="width: 875px; margin: auto;">
            <%--    放导航栏本体的div--%>
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief" style="background-color:#E5E9E6; ">
                <ul class="layui-tab-title" style="">
                    <li style="margin:0px 10px; font-size: 15px;" class="layui-this" name="dynamicLi"><b>动态</b></li>
                    <li style="margin:0px 10px;font-size: 15px;"><b>精彩时刻</b></li>
                    <li style="margin:0px 10px;font-size: 15px;"><b>滑板部落</b></li>
                    <li name="collectLi" style="margin:0px 10px;font-size: 15px;"><b>收藏</b></li>
                </ul>
            </div>
        </div>
    </div>
    <%--    最外面，下部分--%>
    <div style="border:1px blue solid;margin: auto;width: 880px;margin: auto">
        <div>
            <%--        在这个div里面添加内容--%>
            <div id="contentdic" style="background-color: white;border-radius: 5px;">
                <div style="background-color: white;margin:5px 0px;float: left;border-radius: 5px;width: 700px;">
                    <%--            头像圆框--%>
                    <div style="width: 50px;height: 50px; margin: 10px 0px 0px 10px; border-radius: 50%;border: 2px solid white;overflow: hidden;float: left;">
                        <a href="#">
                            <img style="width: 100%;height: 100%;"
                                 src="">
                        </a>
                    </div>
                    <%--            名字和日期--%>
                    <div style="float: left;">
                        <div style="font-size: 15px;margin:20px 0px 0px 15px;">背景图里的大西几xxxxxxxxxxxx</div>
                        <div style="font-size: 12px; border-radius: 5px; margin: 10px 0px 0px 15px;color: gray">08-17
                        </div>
                    </div>
                    <div style="margin: 10px 20px 10px 80px;float: left;">
                        <%--                帖子内容--%>
                        <div style="width: 700px;font-size: 14px;">
                            <div id="ppp">大滑稽</div>
                            <br>
                            <img style="width: 150px;border-radius: 5px"
                                 src="">
                        </div>
                        <%--    点赞、收藏、转发按钮--%>
                        <div style="margin: 20px 0px;">
                            <div style="float: left;">
                        <span>
                            <button type="button" class="layui-btn layui-btn-primary" style="">
                                <span class="layui-icon layui-icon-share"></span>
                                转发
                            </button>
                        </span>
                                <span>
                            <button type="button" class="layui-btn layui-btn-primary" style="">
                                <span class="layui-icon layui-icon-star"></span>
                                收藏
                            </button>
                        </span>
                                <span>
                            <button type="button" class="layui-btn layui-btn-normal">
                                <span class="layui-icon layui-icon-praise"></span>
                                点赞
                            </button>
                        </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>


        <!-- 模态框（Modal） -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <%--                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                        <h4 class="modal-title" id="myModalLabel">更换头像</h4>
                    </div>
                    <div class="modal-body" style="height: 400px;">
                        <h1>选择文件</h1>
                        <form id="img-form" action="/file/uploadImage" method="post" enctype="multipart/form-data">
                            <input type="file" name="userImage" id="imgInput"/>
                            <div id="checkimagediv"
                                 style="display: none;width: 100px;height: 100px;border-radius: 50%;border: 2px solid white;overflow: hidden;float: left">
                                <img src="" alt="选择图片" class="img"
                                     style="display: inherit;width: 100%;display: inline;margin: auto"/>
                            </div>
                            <div style="width: 100px;height: 100px;border-radius: 50%;border: 2px solid white;overflow: hidden;float: right">
                                <img src="/file/getFileSource?filename=${user.userImageUrl}" class="img"
                                     style="height: 100%;width: 100%;"/>
                            </div>
<%--                            <input type="submit" value="提交">--%>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="submitImageForm()">提交更改</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal -->
        </div>

        <%-- 富文本编辑器--%>
        <div class="publishDynamic" style="padding: 10px;border-radius:5px;background-color: #9acfea;">
<%--            <form id="dynamicForm" method="post" action="/file/inDynamic" enctype="multipart/form-data">--%>
                <div class="container" style="padding-left: 0px;">
                    <div id="content" name="dynamicContent" contenteditable="true"
                         style="float:left;border:0;border-radius:5px;background-color:rgba(241,241,241,.98);width: 500px;padding: 10px;resize: none;"
                         wrap="hard" placeholder="请输入内容">
                        123123
                        <div style="display: inline">
                            <span><img style="display:run-in" src="/static/emoji/0.gif"></span><span><img style="display: run-in" src="/static/emoji/0.gif"></span>
                            内敛</div>
                    </div>
                    <%--                <textarea  style="border:0;border-radius:5px;background-color:rgba(241,241,241,.98);width: 500px;height: 100px;padding: 10px;resize: none;" wrap="hard" placeholder="请输入内容" ></textarea>--%>
<%--                    <input type="submit"/>--%>
                    <button style="float: left;" id="button" onclick="inDynamic()">惦记我</button>
                </div>
                <div style="">
                    <%--                点击显示头像选择框--%>
                    <div id="openEmoji"><img src='/static/emoji/0.gif'/></div>
                    <%--                头像选择框--%>
                    <div id="emojidiv"
                         style="position: absolute;display:none;background-color: white;height: 100px;width: 200px;border: 1px blue solid;"></div>
                </div>
                <div id="upload-image-div" style="border: 1px red solid;">
                    <img class="uploadimage" src="/file/getFileSource?filename=${user.userImageUrl}">
                    <img class="uploadimage" src="/file/getFileSource?filename=head_portrait/girl1.jpg">
                </div>
                <input id="files" type="file" multiple="multiple" accept="image/jpeg,image/png,image/jpg"/>
<%--            </form>--%>
        </div>


        <%--        <script>--%>
        <%--            layui.use('layedit', function(){--%>
        <%--                var layedit = layui.layedit;--%>
        <%--                layedit.build('demo'); //建立编辑器--%>
        <%--            });--%>
        <%--        </script>--%>
        <%--        底部的白色--%>
        <div style="background-color: white;margin:10px 0px;float: left;border-radius: 5px;">
            <div style="margin: 10px 20px 10px 115px;float: left;width: 740px;">这里是世界的尽头~~~</div>
        </div>


    </div>
    <%--    这个是 取消浮动效果，在最后一个div加上，就不会出现浮动的div显示脱离父div的范围--%>
    <div style="clear:both;"></div>
</div>


<script>

    window.onload = function () {
        dynamicAjax();
    }

    function dynamicAjax() {
        $.ajax({
            method:"POST",
            url:"/user/dynamic",
            data:{
                userId:${user.userId}
            }
        }).done(function (data) {
            $("#contentdic").html(data);
        }).fail(function () {
            console.log("异步请求失败")
        })
    }
</script>

<script>
    //定义最后光标对象
    var lastEditRange;
    //标记框点击事件
    document.getElementById("content").onclick = function () {
        console.log("--------点击事件，点击了编辑框--------");
        // 获取选定对象
        var selection = getSelection()
        // 设置最后光标对象
        lastEditRange = selection.getRangeAt(0)
    }
    //标记狂按钮弹起事件（鼠标按下拖动选择，松开的时候）
    document.getElementById("content").onkeyup = function () {
        console.log("-------拖动选择按钮弹起事件--------")
        //获取选定对象
        var selection = getSelection();
        //设置最后光标对象
        lastEditRange = selection.getRangeAt(0);
    }

    // 表情点击事件（正在使用）
    function addemoji(emojiValue) {
        console.log("------------添加一个表情 调用addemoji方法--------------")
        //获取编辑框对象
        var edit = document.getElementById("content");
        //获取输入框对象(要输入什么表情)
        var emojiInput = document.getElementById("emojiInput")
        //编辑框设置焦点
        edit.focus();
        //获取选定对象（表示文档中被选中的文本）
        var selection = getSelection();
        //判断是否有最后光标对象存在
        if (lastEditRange) {
            // 存在最后光标对象，选定对象清除所有光标并添加光标还原之前的状态（意思就是页面加载之后有点过过编辑框，保存了一个最后的光标，就使用这个光标插入）
            selection.removeAllRanges();
            selection.addRange(lastEditRange)
        }
        // 判断选定对象范围是编辑框还是文本节点
        console.log("--selection.anchorNode.nodeName--" + selection.anchorNode.nodeName)
        // selection.anchorNode 返回选区开始位置所属的节点（Node）
        console.log("---------什么编辑框范围之类的--" + selection.anchorNode.nodeName);
        // if (selection.anchorNode.nodeName != '#text') {
        //     alert("如果是编辑框范围，则创建表情节点进行插入")
        //     // 如果是编辑框范围，则创建表情文本节点进行插入(插入表情)！！！！！！！！！！！！！！！
        //     // var emojiTest = document.createTextNode(emojiInput.value)
        //     var emojiTest = '<img style=\'object-fit: fill;width: 20px;height: 20px;border-radius:5px;margin: 2px;\' src=\'/file/getFileSource?filename=head_portrait/20191212120904103touxiang.jpg\'>'
        //     if (edit.childNodes.length > 0) {
        //         alert("选取了多个文本？对象？编辑框对象的childNodes数量为"+edit.childNodes.length)
        //         alert("起始位置？最后位置？"+selection.anchorOffset)
        //         // 如果文本框的子元素大于0，则表示有其他元素，则按照位置插入表情节点
        //         for (var i = 0; i < edit.childNodes.length; i++) {
        //             alert(edit.childNodes[i].nodeName)
        //             if (i == selection.anchorOffset) {
        //                  edit.insertBefore(emojiTest,edit.childNodes[i])
        //             }
        //         }
        //     } else {
        //         //否则直接插入一个表情元素
        //         // edit.appendChild(emojiTest)
        //
        //     }
        //     //创建新的光标对象
        //     var range = document.createRange();
        //     //光标对象的范围界定为新建的表情节点
        //     range.selectNodeContents(emojiTest)
        //     //光标位置定位在表情节点的最大长度
        //     range.setStart(emojiTest,emojiTest.length)
        //     //使光标开始和光标结束重叠
        //     range.collapse(true)
        //     //清除选定对象的所有光标对象
        //     selection.removeAllRanges()
        //     //插入新的光标对象
        //     selection.addRange(range)
        // } else {
        console.log("开始选择的是文本")

        //如果是文本节点则先获取光标对象
        var range = selection.getRangeAt(0);
        //获取光标对象的范围界定对象，一般就是textNode对象
        var textNode = range.startContainer;
        //获取光标位置
        var rangeStartOffset = range.startOffset;

        //文本节点在光标位置处插入新的表情内容
// ==========================================================================
        range.deleteContents();//删除选中的内容
        //------------------------------------------------------------------
        let el = document.createElement("div"); //创建一个空的div外壳
        // el.innerHTML = emojiInput.value;        //设置div内容为我们想要插入的内容
        el.innerHTML = emojiValue;
        let frag = document.createDocumentFragment();//创建一个空白的文档片段，便于之后插入dom树
        let node = el.firstChild;
        let lastNode = frag.appendChild(node);
        //------------------------------------------------------------------
        range.insertNode(frag);                 //设置选择范围的内容为插入的内容
        // 这里和下面的作用是一样的，写法不同而已
        let contentRange = range.cloneRange();  //克隆选区
        contentRange.setStartAfter(lastNode);   //设置光标位置为插入内容的末尾
        contentRange.collapse(true);            //移动光标位置到末尾
        selection.removeAllRanges();            //移出所有选区
        selection.addRange(contentRange);       //添加修改后的选区

        // range.setStart(textNode, rangeStartOffset + emojiInput.value.length);  //光标移动到原来的位置上加上新内容的长度，有问题？？
        // range.collapse(true);           //光标开始和光标结束重叠
        // selection.removeAllRanges();    //清除选定对象的所有光标对象
        // selection.addRange(range);      //插入新的光标对象
        // }
        // 无论如何都要记录最后光标对象（有点问题）
        lastEditRange = selection.getRangeAt(0)
    }
</script>

<script>
    var ImgIputHandler = {
        facePath: [
            {faceName: "微笑", facePath: "0.gif"},
            {faceName: "撇嘴", facePath: "1.gif"},
            {faceName: "色", facePath: "2.gif"},
            {faceName: "发呆", facePath: "3.gif"},
            {faceName: "得意", facePath: "4.gif"},
            {faceName: "流泪", facePath: "5.gif"},
            {faceName: "害羞", facePath: "6.gif"},
            {faceName: "闭嘴", facePath: "7.gif"},
            {faceName: "大哭", facePath: "9.gif"},
        ]
    }
    // 显示表情的对象
    var emojidiv = document.getElementById("emojidiv");

    for (var i = 0; i < ImgIputHandler.facePath.length; i++) {
        var imgdiv = document.createElement("div");
        imgdiv.style.float = "left";
        imgdiv.className = "emoji";
        imgdiv.innerHTML = "<img emojiId=" + i + " src='/static/emoji/" + ImgIputHandler.facePath[i].facePath + "' />"
        emojidiv.append(imgdiv);
    }

    // 点击显示隐藏表情选择DIV
    $("#openEmoji").bind("click", function () {
        $(".comment-image-div").hide();
        $("#emojidiv").slideToggle(100);
    })
    $(".emoji").bind("click", function () {
        console.log("-----||" + $(this).html() + "||-----");
        var emojiValue = $(this).html();
        addemoji(emojiValue);
    })
</script>

<%--动态文件上传--%>
<script>
    // 初始化FormData对象
    var formDate = new FormData();
    $("#files").change(function () {
        var $files = $("#files");
        var fileObj = $files[0].files;
        var imagediv = document.getElementById("upload-image-div");
        var divNum = $("#upload-image-div").children().length;
        var n = fileObj.length;
        alert("选择的文件个数："+n+"放图片的div图片数量为："+divNum);
        var uploadImageDiv = $("#upload-image-div");
        for (var i = 0; i < fileObj.length; i++) {
            if (divNum+i+1 <=9) {   //图片数量不能大于9张
                var img = document.createElement("img");
                $(img).attr("src", URL.createObjectURL($(this)[0].files[i]));
                img.className = 'uploadimage';
                imagediv.append(img);
                // formDate.append("files",fileObj[i])
            } else {
                alert("上传文件不能超过9张");
            }
        }
        //获取目前多文件上传的文件
        getUploadFiles();
    })

    // 获取目前多文件上传的文件
    function getUploadFiles() {
        var $files = $("#files");
        var fileObj = $files[0].files;
        var divNum = $("#upload-image-div").children().length;
        formDate.delete("files")
        for (var i = 0; i < fileObj.length; i++) {
            if (divNum+i+1 <=9) {   //图片数量不能大于9张
                formDate.append("files",fileObj[i])
            } else {
                alert("上传文件不能超过9张");
            }
        }
    }

    $("#files").click(function (e) {
        var uploadImageDiv = $("#upload-image-div");
        if (uploadImageDiv.children().length >= 9) {
            e.preventDefault();
        }

    })
</script>

<%--开始写 js 代码--%>
<script>


    // function EditsubmitForm() {
    //     formDate.delete("dynamicContent");
    //     formDate.append("dynamicContent",val);
    //     $("#dynamicForm").form('submit',{
    //         url:"/file/inDynamic",
    //         data:formDate,
    //         onSubmit:function () {  // 表单提交前的回调函数
    //             alert("表单提交前的回调函数")
    //         },
    //         success:function () {   //表单提交成功后的回调函数
    //             alert("表单提交成功后的回调函数")
    //             location.reload();
    //         }
    //     })
    // }

    // 正在使用（发表动态）
    function inDynamic() {
        var val = $("#content").html();
        // var des = val.replace(/\r\n/g, '<br>')
        //     .replace(/\n/g, '<br>')
        //     // .replace(/\s/g, ' ')    // 空格
        //     .replace(/\s/g,"&nbsp;");
        //转换格式
        // $("#ppp").html(val)
        formDate.delete("dynamicContent");
        formDate.append("dynamicContent",val);
        $.ajax({
            method: "POST",
            url: "/file/inDynamic",
            data: formDate,
            dataType: "json",
            async:false,    // false 为同步请求 true 为异步请求，同步请求完成之前会把浏览器锁死
            contentType: false,  // https://www.cnblogs.com/zhangym118/p/9029269.html 我为什么要加这个的解释
            processData: false   // 让jquery 不要对 formData做处理，如果processData不设置为false，jquery会把formData转换为字符串。
        }).done(function (result) {
            console.log("上传文件异步状态：" + result.code + "上传文件信息为" + result.msg)
            dynamicAjax();
        }).fail(function (result) {
            console.log("上传文件异步失败")
        })
    }

    function biaoqing() {
        alert("添加表情")
        var vvv = $("#content").html();
        let imgTag = '<img style=\"object-fit: fill;width: 20px;height: 20px;border-radius:5px;margin: 2px;\" src=\"/file/getFileSource?filename=${user.userImageUrl}\">'
        // 调用insertContent方法，在光标处插入内容
        this.insertContent(imgTag)

    }

    // 在光标处插入内容（未使用）
    function insertContent(context) {
        console.log("---调用insertContent方法")
        if (!context) {
            return;
        }
        let sel = null;
        if (document.selection) {   //IE9以下
            sel = document.selection;
            sel.createRange().pasteHTML(context);
        } else {
            sel = window.getSelection();    //光标的当前位置？
            alert(sel)
            if (sel.rangeCount > 0) {
                if (isInDiv(event)) {
                    alert(isInDiv(event));
                    let range = sel.getRangeAt(0);//获取选择范围
                    range.deleteContents();//删除选中的内容
                    let el = document.createElement("div");//创建一个空的div外壳
                    el.innerHTML = context;//设置div内容为我们想要插入的内容
                    let frag = document.createDocumentFragment();//创建一个空白的文档片段，便于之后插入dom树
                    let node = el.firstChild;
                    let lastNode = frag.appendChild(node);
                    range.insertNode(frag);                 //设置选择范围的内容为插入的内容
                    let contentRange = range.cloneRange();  //克隆选区
                    contentRange.setStartAfter(lastNode);          //设置光标位置为插入内容的末尾
                    contentRange.collapse(true);                   //移动光标位置到末尾
                    sel.removeAllRanges();                  //移出所有选区
                    sel.addRange(contentRange);             //添加修改后的选区
                }
            }
        }
    }


    $("#tijiao").on("click", function () {
        alert(ue.getContent());
    })

    // 浏览器的内核兼容
    // function getObjectURL(file) {
    //     if (file == undefined) return "";
    //     if (window.createObjectURL !== undefined) return window.createObjectURL(file);
    //     if (window.URL.createObjectURL !== undefined) return window.URL.createObjectURL(file);
    //     if (window.webkitURL.createObjectURL !== undefined) return window.webkitURL.createObjectURL(file);
    // }

    // 换头像时，当选择图片时，把得到的图片路径交给 <img>标签显示
    $("#imgInput").change(function () {
        alert("得到文件显示出来")
        $("#checkimagediv").css("display", "inline");
        $(".img").attr("src", URL.createObjectURL($(this)[0].files[0]));
        // $(".img").css("display","inline");

    })

    function submitImageForm() {
        alert("提交头像上传表单")
        var serialize = $("#img-form").serialize();// 表单序列化
        var formData = new FormData($("#img-form")[0]);
        alert(serialize);
        alert(formData);
        $.ajax({
            method: "POST",
            url: "/file/uploadImage",
            data: formData,
            dataType: "json",
            // async:false,    // false 为同步请求 true 为异步请求，同步请求完成之前会把浏览器锁死
            contentType: false,  // https://www.cnblogs.com/zhangym118/p/9029269.html 我为什么要加这个的解释
            processData: false   // 让jquery 不要对 formData做处理，如果processData不设置为false，jquery会把formData转换为字符串。
        }).done(function (result) {
            alert("上传文件异步状态：" + result.code + "上传文件信息为" + result.msg)
        }).fail(function (result) {
            alert("上传文件异步失败")
        })
    }

    // 点击导航栏切换
    $(".layui-tab-title li").on("click", function () {
        $li = $(".layui-tab-title li");
        // 全部取消选中
        for (var i = 0; i < $li.length; i++) {
            $li.removeClass("layui-this");
        }
        // 点击的选中
        $(this).addClass("layui-this");
        if ($(this).attr("name") === 'dynamicLi') {
            dynamicAjax();
        }
    })

    $("#wodetouxiang").mouseenter(function () {
        $("#div123").fadeIn();
    })

    $("#wodetouxiang").mouseleave(function () {
        $("#div123").fadeOut();

    })
</script>
</body>
</html>
