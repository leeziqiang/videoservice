package com.de.service;

import com.de.entity.Image;
import com.de.entity.ImageEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.logging.Logger;

@ClientEndpoint(encoders = {ImageEncoder.class})
@Component
@Slf4j
public class WebSocketClient{

    private Session session;
    @OnOpen
    public void onOpen(Session session){
        log.info("Client WebSocket is opening...");
        this.session=session;
    }

    @OnMessage
    public void onMessage(String message){
        log.info("接受服务端信息===="+message);

    }
    @OnError
    public void onError(Session session,Throwable error){
        log.error("websocket错误！");
        error.printStackTrace();
    }

    @OnClose
    public void onClose() throws IOException {
        if(this.session.isOpen()){
            this.session.close();
        }
        log.info("关闭websocket连接！");
    }

    public void sendMessageByStr(String message) {
        if (StringUtils.isNotBlank(message)) {
                this.session.getAsyncRemote().sendText(message);
        }
    }

    public void sendMessageByObject(Object message) {
        if (message != null) {
                this.session.getAsyncRemote().sendObject(message);
        }
    }

    public void sendBinary(ByteBuffer message) {
        if (message != null) {
                this.session.getAsyncRemote().sendBinary(message);
        }
    }
}
