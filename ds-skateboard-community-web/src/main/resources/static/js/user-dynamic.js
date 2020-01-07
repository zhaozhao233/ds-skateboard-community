timestampToTime();
function timestampToTime() {
    console.log("调整事件格式")
    var obj = $(".dynamic-time");
    for (var i = 0; i < obj.length; i++) {
        var html = obj.eq(i).html();
        var date = new Date(html);
        var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        var D = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
        obj.eq(i).html(M + D);
    }
}

$(".delete-dynamic-but").click(function () {
    var dynamicId = $(this).attr("dynamicid");
    if(confirm("是否删除？")){
        $.ajax({
            url: "/user/deleteDynamic",
            method: "POST",
            data: {
                "dynamicId": dynamicId
            }
        }).done(function (data) {
            console.log("删除异步成功"+data.code)
        }).fail(function (data) {
            console.log("删除异步失败")
        })
        window.location.reload();
    }


})