<!DOCTYPE html>
<html>
<head>
<title>Web Sockets Basic Demo - Anish Sneh</title>
</head>
<body>
	<div>
		<input type="text" id="userinput" /> <br> <input type="submit" value="Send Message" onclick="start()" />
	</div>
	<div id="messages"></div>
	<script type="text/javascript">
		var webSocket = new WebSocket('ws://localhost:8080/web-sockets/helloworld');

		webSocket.onerror = function(event) {
			onError(event)
		};

		webSocket.onopen = function(event) {
			onOpen(event)
		};

		webSocket.onmessage = function(event) {
			onMessage(event)
		};

		function onMessage(event) {
			document.getElementById('messages').innerHTML += '<br />' + event.data;
		}

		function onOpen(event) {
			document.getElementById('messages').innerHTML = 'Connected';
		}

		function onError(event) {
			alert(event.data);
		}

		function start() {
			var text = document.getElementById("userinput").value;
			webSocket.send(text);
			return false;
		}
	</script>
</body>
</html>