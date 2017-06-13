package com.smarthome.websockets;

import java.io.*;
import java.util.*;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class WebSocket  implements Serializable {


	private static final String PATH = "C:\\Users\\Olga\\Desktop\\smarthome\\src\\main\\webapp\\docs\\states.txt";
	private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());
	private HashMap<String,String> listState = readFile(PATH);
	/* Датчики:
	"temperature","23"
	"light","off"
	"humidity","60"
	"lockDoor","on"
	"e-current","on"*/

	public WebSocket() throws IOException, ClassNotFoundException {
		listState.put("light",readFile(PATH).get("light"));
	}

	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {

			for (Session sess : session.getOpenSessions()) {
				if (sess.isOpen())
					sess.getBasicRemote().sendText(message);

			}

			listState.put("light", message);
	}


	@OnOpen
	public void onOpen(Session session) throws IOException, ClassNotFoundException, EncodeException {
		sessions.add(session);

		try {
			listState.put("light",readFile(PATH).get("light"));
		} finally {
			for (Session sess : sessions) {
				if (sess.isOpen())
					sess.getBasicRemote().sendText(listState.get("light"));
			}
		}

	}

	@OnClose
	public void onClose(Session session) throws IOException {

		try {
			System.out.println("Connection closed");
			sessions.remove(session);
		} finally {
			saveFile(listState,PATH);
		}
	}

	public void saveFile(HashMap<String, String> listOfStates, String pathFile)
			throws IOException
	{
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(pathFile))) {
			os.writeObject(listOfStates);
		}
	}

	public HashMap<String, String> readFile(String pathFile)
			throws ClassNotFoundException, IOException
	{
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(pathFile))) {
			return (HashMap<String, String>) is.readObject();
		}
	}

	private String getSensorNameFromMessage(String message){
		return message.substring(0,message.indexOf(","));
	}

	private String getSensorStateFromMessage(String message){
		return message.substring(message.indexOf(",")+1, message.length());
	}
}
