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

    var image = document.getElementById("frameForLightBulb");

    switch (event.data){
        case "on" : {
            $(image).attr("src", "./resources/lighton.jpg");
            break;
        }
        case "off" : {
            $(image).attr("src", "./resources/lightoff.jpg");
            break;
        }
        default : break;
    }

}

function onOpen(event) {

    var image = document.getElementById("frameForLightBulb");

    switch (event.data){
        case "on" : {
            $(image).attr("src", "./resources/lighton.jpg");
            break;
        }
        case "off" : {
            $(image).attr("src", "./resources/lightoff.jpg");
            break;
        }
        default : break;
    }

}

function onError(event) {
    alert(event.data);
}

function start() {
    var image = document.getElementById("frameForLightBulb");

    if ($(image).attr("src") == "./resources/lightoff.jpg"){
        $(image).attr("src", "./resources/lighton.jpg");
        webSocket.send("on");
    } else {
        $(image).attr("src", "./resources/lightoff.jpg");
        webSocket.send("off");
    };
    return false;
}


$(window).onunload(function() {
    webSocket.close();
});
