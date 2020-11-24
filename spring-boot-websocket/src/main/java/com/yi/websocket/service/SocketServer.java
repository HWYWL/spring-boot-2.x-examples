package com.yi.websocket.service;

import com.yi.websocket.config.WebSocketConfig;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebSocket业务处理
 * @author YI
 * @date 2018-9-27 14:51:33
 */
@Component
@ServerEndpoint(value = "/socketServer/{userId}")
public class SocketServer {

	private Session session;
	private static Map<String,Session> sessionPool = new HashMap<>(16);
	private static Map<String,String> sessionIds = new HashMap<>(16);

	/**
	 * 用户连接时触发
	 * @param session	session
	 * @param userId 用户id
	 */
	@OnOpen
	public void open(Session session, @PathParam(value="userId")String userId){
		this.session = session;
		sessionPool.put(userId, session);
		sessionIds.put(session.getId(), userId);
	}

	/**
	 * 收到信息时触发
	 * @param message 收到的消息
	 */
	@OnMessage
	public void onMessage(String message){
		sendMessage(sessionIds.get(session.getId()) + "<--" + message, WebSocketConfig.getAdmin());

		System.out.println("发送人:" + sessionIds.get(session.getId()) + " 内容:" + message);
	}

	/**
	 * 连接关闭触发
	 */
	@OnClose
	public void onClose(){
		sessionPool.remove(sessionIds.get(session.getId()));
		sessionIds.remove(session.getId());
	}

	/**
	 * 发生错误时触发
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

	/**
	 *信息发送的方法
	 * @param message	发送的数据
	 * @param userId	用户id
	 */
	public static void sendMessage(String message,String userId){
		Session s = sessionPool.get(userId);

		if(s != null){
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取当前连接数
	 * @return
	 */
	public static int getOnlineNum(){
		if(sessionIds.values().contains(WebSocketConfig.getAdmin())) {

			return sessionPool.size()-1;
		}

		return sessionPool.size();
	}

	/**
	 * 获取在线用户名
	 * @return
	 */
	public static List getOnlineUsers(){
		List<String> userList = new ArrayList<>();

		for (String key : sessionIds.keySet()) {
			// 主机是服务端自己的连接，不能算在线人数
	    	if (!WebSocketConfig.getAdmin().equals(sessionIds.get(key))) {
				userList.add(sessionIds.get(key));
			}
		}

	    return userList;
	}

	/**
	 * 信息群发
	 * @param msg	发送的数据
	 */
	public static void sendAll(String msg) {
		for (String key : sessionIds.keySet()) {
			if (!WebSocketConfig.getAdmin().equals(sessionIds.get(key))) {
				sendMessage(msg, sessionIds.get(key));
			}
	    }
	}

	/**
	 * 多个人发送给指定的几个用户
	 * @param msg	发送的数据
	 * @param persons	用户
	 */
	public static void SendMany(String msg, String[] persons) {
		for (String userId : persons) {
			sendMessage(msg, userId);
		}
	}
}
