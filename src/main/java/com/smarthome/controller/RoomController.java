package com.smarthome.controller;

import com.smarthome.websockets.WebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;

/**
 * Created by Olga on 25.05.2017.
 */
@Controller
public class RoomController {

    protected static WebSocket webSocket;

    static {
        try {
            webSocket = new WebSocket();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/room")
    public void open(){

    }
}
