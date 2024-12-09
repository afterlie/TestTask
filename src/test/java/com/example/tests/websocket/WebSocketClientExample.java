package com.example.tests.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class WebSocketClientExample extends WebSocketClient {

    public WebSocketClientExample(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to the server!");
        sendMessage("Finally we are connected to the WS, congrats!");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message from server: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed connection: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    // создаю метод для отправки сообщений на сервер
    public void sendMessage(String message) {
        if (this.isOpen()) {
            send(message);
            System.out.println("Message sent: " + message);
        }
    }
}
