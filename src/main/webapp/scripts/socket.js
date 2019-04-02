var socket = null;

/*var ep = '/vehicle-locator-websocket/websocket/log';
if (window.location.protocol == 'http:') {
	host = 'ws://' + window.location.host + ep;
} else {
	host = 'wss://' + window.location.host + ep;
}

if ('WebSocket' in window) {
    socket = new WebSocket(host);
} else if ('MozWebSocket' in window) {
    socket = new MozWebSocket(host);
}*/


var url = 'http://' + window.location.host + '/vehicle-locator-websocket/log-socket'
var sock = new SockJS(url);
var stomp = Stomp.over(sock);

stomp.connect({}, function(frame) {
    console.log('*****  Connected  *****');
    //stomp.subscribe("/topic/marco", handlePolo);
   
  });



