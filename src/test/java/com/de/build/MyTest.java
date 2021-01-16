package com.de.build;
import com.de.service.WebSocketClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTest {
    @Test
    public void websocketClientTest() throws URISyntaxException, IOException, DeploymentException, InterruptedException {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        WebSocketClient client = new WebSocketClient();
        container.connectToServer(client,new URI("ws://202.115.52.99:8124"));
        int turn =0;
        while (turn++<10){
            client.sendMessageByStr("send text:  "+turn);
            Thread.sleep(1000);
        }
        client.onClose();
    }

}
