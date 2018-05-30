package com.bootdo.socket;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class SocketService {
	   
    private static int port = 8000;
    
    /**
    * 创建byte处理服务器
    * @param time 超时时间
    * @throws IOException
    */
    public static void startByteService(int time) throws IOException{
	    // 创建服务器监听
		IoAcceptor acceptor = new NioSocketAcceptor();
		// 增加日志过滤器
		//acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		//调用编解码工厂进行编解码
		acceptor.getFilterChain().addLast("mycoder", new ProtocolCodecFilter(new ByteArrayCodecFactory()));
		//这里不添加字符编码过滤器
		// 指定业务逻辑处理器
		acceptor.setHandler(new TimeServerHandler());
		// 设置buffer的长度
		acceptor.getSessionConfig().setReadBufferSize(2048);
		// 设置连接超时时间
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, time);
		// 绑定端口
		acceptor.bind(new InetSocketAddress(port));
		System.out.println("服务器在端口："+port+"已经启动");
    }
    
    public static void main(String[] args) {
    	try {
			SocketService.startByteService(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
