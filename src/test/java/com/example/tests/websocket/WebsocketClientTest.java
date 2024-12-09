package com.example.tests.websocket;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class WebsocketClientTest {

    @Test
    void tryToConnectToWebsocket() {
        try {
            URI serverUri = new URI("ws://localhost:8080/ws");
            WebSocketClientExample client = new WebSocketClientExample(serverUri);
            client.connect();

            //жду соединения
            Thread.sleep(500);

            //отправляю запасное сообщение еще одно сообщение, на всякий
            client.sendMessage("Spare message");

        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
