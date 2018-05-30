package com.bootdo.socket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession; 

public class TimeServerHandler extends IoHandlerAdapter {
	
	@Override 
	public void sessionCreated(IoSession session) throws Exception { 
		System.out.println("服务端与客户端创建连接..."); 
		super.sessionCreated(session); 
	} 

	@Override 
	public void sessionOpened(IoSession session) throws Exception { 
		System.out.println("服务端与客户端连接打开..."); 
		super.sessionOpened(session); 
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		//获取客户端发过来的key
		String key = message.toString();
		System.out.println("接收客户端信息" + message.toString());
		String carPark_id = key.substring(key.indexOf("=") + 1);
		System.out.println("carPark_id :"+carPark_id);

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception { 
		System.out.println("服务端发送信息成功..."+message.toString());
		System.out.println(ByteUtils.byteToHexStr((byte[]) message));
		super.messageSent(session, message); 
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception { 
		System.out.println("服务端发送异常..."+cause.getMessage()); 
		cause.printStackTrace(); 
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("服务端与客户端连接关闭...");
		super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println("服务端进入空闲状态... " + session.getIdleCount(status)); 
	} 
}
