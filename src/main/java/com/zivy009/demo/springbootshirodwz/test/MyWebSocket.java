package com.zivy009.demo.springbootshirodwz.test;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.zivy009.demo.springbootshirodwz.service.impl.SysUserService;

/**
 * @author zivy
 * @date 2017年11月10日
 * @describe ws://192.168.1.124:8088/springbootshirodwz/webSocket2 最简单的websocket
 *           只要加注解就ok 。 但和spring是不整合的。也就是不能直接用spring容器中的组件
 * 
 */

@ServerEndpoint(value = "/webSocket2")
@Component
public class MyWebSocket {
    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();
    private Session session;
    private static ApplicationContext applicationContext;
// 需要在springboot启动时调用这个。
    public static void setApplicationContext(ApplicationContext context) {
        applicationContext = context;
    }

    public MyWebSocket() {
        System.out.println("new 一个实例出来 不是单例 ");
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        addOnlineCount();
        SysUserService sysUserService=applicationContext.getBean(SysUserService.class);
        sysUserService.countRow();
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("来自客户端的消息:" + message);
        // 群发消息
        for (MyWebSocket item : webSocketSet) {
            item.sendMessage(message);
        }
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount() {
        return MyWebSocket.onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}
