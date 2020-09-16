package com.grouporder.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/front-end/groupclass/GroupOrderSocket/{mem_id}")
public class GroupOrderSocket {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
//	Gson gson = new Gson();
	
	@OnOpen
	public void onOpen(@PathParam("mem_id") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
	}
	
	
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		Session receiverSession = sessionsMap.get(message);
		System.out.println("sendMsgs");
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText("有會員購買您上架的團課哦");
//			userSession.getAsyncRemote().sendText(message);
		}
	}
	
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}
	
	
	
	
	
}
