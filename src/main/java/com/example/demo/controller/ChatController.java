package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@RestController
@ServerEndpoint(value="/chatWebSocket/Room/{username}") // 当创建好一个（服务）端点之后，将它以一个指定的URI发布到应用当中，这样远程客户端就能连接上它了
public class ChatController {
    private static ApplicationContext applicationContext;
    private static List<Session> sessions = new ArrayList<Session>();
    public static void  setConfigurableApplicationContext(ApplicationContext applicationContext){
        ChatController.applicationContext=applicationContext;
    }
    @OnOpen
    public void OnOpen(Session session, @PathParam(value = "username") String username) {
        sessions.add(session);
        UserMapper bean = applicationContext.getBean(UserMapper.class);
        User userInfo = bean.getUserInfo(username);
        if(userInfo.getRoles().getRole().equals("admin")){
            sendTextMsg("管理员 [" + username + "]在线");
        }else {
            sendTextMsg("用户 [" + username + "]进入");
        }

    }
    @OnMessage
    public void OnMsg(String msg,@PathParam(value = "username") String username) {
        sendTextMsg(username + ":" +msg);
    }
    @OnClose
    public void OnClose(Session session, @PathParam("username") String username) throws IOException {
        sessions.remove(session);
        sendTextMsg("用户 ["+ username + "] 已下线");
    }
    @OnError
    public void OnError(Throwable e) {
        e.printStackTrace();
    }
    private void sendTextMsg(String msg) {
        for (Session session : sessions) {
            session.getAsyncRemote().sendText(msg);
        }
    }
}