package com.smhrd.handler;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@ServerEndpoint("/chat")
public class ChattingHandler{

	 private static List<Session> list = new ArrayList<>();
	 

	 	@OnMessage
	    public void handleTextMessage(Session session, String msg) throws Exception {

	        for(Session sess: list) {
	            sess.getBasicRemote().sendText(msg);
	        }
	    }

	    /* Client가 접속 시 호출되는 메서드 */
	 	@OnOpen
	    public void afterConnectionEstablished(Session session) throws Exception {

	        list.add(session);

	    }

	    /* Client가 접속 해제 시 호출되는 메서드드 */
	 	@OnClose
	    public void afterConnectionClosed(Session session) throws Exception {

	        list.remove(session);
	    }
}