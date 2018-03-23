package ru.kpfu.itis.app.model;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class EchoHandler extends TextWebSocketHandler {

    private Map<String, WebSocketSession> sessions;


    public EchoHandler() {
        sessions = new HashMap<>();
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
        System.out.println("New request from client : port is " + session.getRemoteAddress().getPort()
                + ", session id is " + session.getId());
    }



    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
        String message = textMessage.getPayload();
        for (Map.Entry<String, WebSocketSession> entry : sessions.entrySet()) {
            if(entry.getValue().isOpen()) {
                entry.getValue().sendMessage(new TextMessage(message));
            }
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("client websocket " + session.getId() + " is closed");
    }
}