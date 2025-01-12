package com.example.tests.websocket;

import com.example.tests.helper.listener.RetryListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URI;
import java.net.URISyntaxException;

@ExtendWith(RetryListener.class)
public class WebsocketClientTest {

    @AfterAll
    public static void saveFailed(){
        RetryListener.saveFailedTests();
    }

    @Tag("websocket")
    @Test
    void tryToConnectToWebsocket() {
        try {
            URI serverUri = new URI("ws://localhost:8080/ws");
            WebSocketClientExample client = new WebSocketClientExample(serverUri);
            client.connect();

            //жду соединения
            Thread.sleep(500);

            //отправляю запасное сообщение, на всякий
            client.sendMessage("Spare message to approve connection");

        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
