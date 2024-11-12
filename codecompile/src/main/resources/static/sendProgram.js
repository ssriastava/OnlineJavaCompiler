function sendCode(){
	var code = window.editor.getValue();
	var language = "Java";

	console.log(code);
	console.log(language);

	if(code!=null && language!=null){
		let xhr = new XMLHttpRequest();
		xhr.open("POST", "http://localhost:8082/compileAndRun");
		xhr.setRequestHeader("Accept", "application/json");
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
  			if (xhr.readyState === 4 && this.status == 200) {
  			var responseObj = JSON.parse(this.responseText);
  			webSocketData(responseObj.message);
  		}};
  		console.log("1");
  		const obj ={
			  "code":code,
			  "language":language
		  };
		console.log("Sent Code");
		xhr.send(JSON.stringify(obj));
	}
}

function webSocketData(cid){
const clientId=cid;
console.log(clientId);
const url='ws://localhost:8084/ws?clientId=${encodeURIComponent(clientId)}';
const url1='ws://localhost:8084/ws?clientId='+encodeURIComponent(clientId);
const url2='ws://localhost:8084/ws?clientId='.concat(clientId);
console.log(url);
console.log(url1);
console.log(url2);


const socket = new WebSocket(url1);

socket.onopen = function() {
    console.log("WebSocket connection established");
};

socket.onmessage = function(event) {
    console.log("Received message from server:", event.data);
    // Handle the received output here
    document.getElementById("output").innerText = event.data;
};

socket.onclose = function(event) {
    console.log("WebSocket connection closed", event);
};

socket.onerror = function(error) {
    console.error("WebSocket error", error);
};
}