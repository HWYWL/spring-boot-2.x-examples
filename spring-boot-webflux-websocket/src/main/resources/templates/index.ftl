<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset = utf-8"/>
		<title>WebSocket客户端</title>
	<script type="text/javascript">
		var socket;
		if(!window.WebSocket){
			window.WebSocket = window.MozWebSocket;
		}

		if(window.WebSocket){
			socket = new WebSocket("ws://localhost:8080/echo");
			socket.onmessage = function(event){
                var data = event.data;
                var message = JSON.parse(data);

				var ta = document.getElementById('responseContent');
				ta.value += message.msg + "\r\n";

				var pelpeo = document.getElementById('pelpeo');
				pelpeo.innerHTML = '当前在线人数:' + message.online;
			};

			socket.onopen = function(event){
				var ta = document.getElementById('responseContent');
				ta.value = "你当前的浏览器支持WebSocket,请进行后续操作\r\n";
			};

			socket.onclose = function(event){
				var ta = document.getElementById('responseContent');
				ta.value = "";
				ta.value = "WebSocket连接已经关闭\r\n";
			};
		}else{
			alert("您的浏览器不支持WebSocket");
		}


		function send(message){
			if(!window.WebSocket){
				return;
			}
			if(socket.readyState == WebSocket.OPEN){
				socket.send(message);
			}else{
				alert("WebSocket连接没有建立成功！！");
			}
		}
	</script>
	</head>
	<body>
		<form onSubmit="return false;">
			<input type = "text" name = "message" value = ""/>
			<br/><br/>
			<input type = "button" value = "发送WebSocket请求消息" onClick = "send(this.form.message.value)"/>
			<hr color="red"/>
			<h2>客户端接收到服务端返回的应答消息</h2>
			<textarea id = "responseContent" style = "width:1024px; height:300px"></textarea>
			<h2 id = "pelpeo">当前在线人数</h2>
		</form>
	</body>
</html>