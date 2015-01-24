package com.anishsneh.demo.websockets;

import java.io.IOException;
import java.util.UUID;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * The Class HelloWebSocket.
 * 
 * @author Anish Sneh
 */
@ServerEndpoint("/helloworld")
public class HelloWebSocket {

	/**
	 * On message.
	 *
	 * @param message the message
	 * @param session the session
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		System.out.println("User input: " + message);
		for (int i = 0; i < 60; i++) {
			session.getBasicRemote().sendText(message + "@" + UUID.randomUUID().toString());
			Thread.sleep(1000);
		}
	}

	/**
	 * On open.
	 */
	@OnOpen
	public void onOpen() {
		System.out.println("Client connected");
	}

	/**
	 * On close.
	 */
	@OnClose
	public void onClose() {
		System.out.println("Connection closed");
	}
}
