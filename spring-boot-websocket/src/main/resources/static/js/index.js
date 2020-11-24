var ws = null;
var username = $("#username").val();

function connect(){
    if(username!=null){
        $("#content").html('');

        if ('WebSocket' in window){
            ws = new WebSocket("ws://127.0.0.1:8080/socketServer/"+$("#username").val());
        } else if ('MozWebSocket' in window){
            ws = new MozWebSocket("ws://127.0.0.1:8080/socketServer/"+$("#username").val());
        } else{
            alert("该浏览器不支持websocket");
        }


        ws.onmessage = function(evt) {
            var content = $("#content").html();
            $("#content").html(content+'<div style="text-align: right;margin-bottom: 8px">\n' +
                '<p><q style="color: mediumorchid">服务端:</q><span>'+evt.data+ '</span></p>\n' +
                '</div>\n' +
                '<br/>');
        };

        ws.onclose = function(evt) {
            var content = $("#content").html();
            $("#content").html(content+'<div style="margin-bottom: 8px">\n' +
                '<p><q style="color: coral">客户端:</q><span>连接中断</span></p>\n' +
                '</div>\n' +
                '<br/>');
        };

        ws.onopen = function(evt) {
            $("#content").html('<div style="margin-bottom: 8px">\n' +
                '<p><q style="color: coral">客户端:</q><span>连接成功...</span></p>\n' +
                '</div>\n' +
                '<br/>');
        };
    }else{
        alert("请输入您的昵称");
    }
}

function sendMsg() {
    ws.send($("#writeMsg").val());
    var content = $("#content").html();
    $("#content").html(content+'<div>\n' +
        '<p><q style="color: coral">客户端:</q><span>'+$("#writeMsg").val()+ '</span></p>\n' +
        '</div>\n' +
        '<br/>');
}