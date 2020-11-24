layui.use('form', function () {
    var form = layui.form;
});
function sendMsg(){
    $ = layui.jquery;
    var user = [];
    //把所有被选中的复选框的值存入数组
    $("input[name='check']:checked").each(function(i){
        user =user+$(this).attr("title")+","
    });

    if (user.length > 0) {
        user = user.substr(0, user.length - 1);
    } else {
        var content = $("#content").html();
        $("#content").html(content+'<div style="margin-bottom: 8px">\n' +
            '<p><q style="color: red">'+'系统提示:请在多选框中选择要发送的用户'+ '</span></p>\n' +
            '</div>\n' +
            '<br/>');
        return
    }

    var msg = $("#msg").val();
    if(msg!=null){
        $.ajax({
            method: 'get',
            url: '/sendMsg',
            data: {
                "username": user,
                "msg": msg
            },
            success:function(data) {
                var content = $("#content").html();
                $("#content").html(content+'<div style="margin-bottom: 8px">\n' +
                    '<p><q style="color: #eb7350">'+'服务器推送  '+msg+' -->'+user+ '</span></p>\n' +
                    '</div>\n' +
                    '<br/>');
            }
        })
    }else{
        alert("请填写要发送的用户昵称或者发送内容");
    }
}

function sendAll(){
    var msg = $("#msg").val();
    if(msg!=null){
        $.ajax({
            method: 'get',
            url: '/sendAll',
            data:{
                msg:msg
            },
            success:function(data) {
                var content = $("#content").html();
                $("#content").html(content+'<div style="margin-bottom: 8px">\n' +
                    '<p><q style="color: #eb7350">'+'服务器推送  ' + msg+' --> 所有用户' + '</span></p>\n' +
                    '</div>\n' +
                    '<br/>');
            }
        })
    }else{
        alert("请填写要发送的内容");
    }
}

function connect(){
    if ('WebSocket' in window){
        ws = new WebSocket("ws://127.0.0.1:8080/socketServer/YI");
    } else if ('MozWebSocket' in window){
        ws = new MozWebSocket("ws://127.0.0.1:8080/socketServer/YI");
    }
    else{
        alert("该浏览器不支持websocket");
    }
    ws.onmessage = function(evt) {
        var content = $("#content").html();
        $("#content").html(content+'<div style="text-align: right;margin-bottom: 8px">\n' +
            '<p><q style="color: mediumorchid;text-align: right">'+evt.data+ '</span></p>\n' +
            '</div>\n' +
            '<br/>');
        console.log(msg)
    };

    ws.onclose = function(evt) {
        console.log('连接关闭')
    };

    ws.onopen = function(evt) {
        var content = $("#content").html();
        $("#content").html(content+'<div style="margin-bottom: 8px">\n' +
            '<p><q style="color: #eb7350">'+'服务器初始化成功...'+ '</span></p>\n' +
            '</div>\n' +
            '<br/>');
    };
}

connect();