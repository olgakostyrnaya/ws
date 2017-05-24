/**
 * Created by Olga on 16.05.2017.
 */

var webSocket = new WebSocket('ws://localhost:8080/room/websocket');

webSocket.onerror = function (event) {
    onError(event)
};

webSocket.onopen = function (event) {
    onOpen(event)
};

webSocket.onmessage = function (event) {
    onMessage(event)
};

webSocket.onclose = function (closeEvent) {
    alert(closeEvent.code);
}

function onMessage(event) {
    /*document.getElementById('but').innerHTML += '<br />'
        + event.data;*/
    var but = document.getElementById('but');
    $(but).attr("value",event.data);
}

function onOpen(event) {
    document.getElementById('messages').innerHTML = 'Now Connection established';

}

function onError(event) {
    alert(event.data);
}

function start(sensor,state) {
    var text = /*document.getElementById(sensor).value*/ sensor +"," +document.getElementById(state).value ;

    webSocket.send(text);
    return false;
}


