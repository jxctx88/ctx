package cn.memedai.io.netty.unpack.delimiterbasedframedecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;

public class EchoClientHandler extends ChannelHandlerAdapter{

	private static final Logger logger = Logger.getLogger(EchoClientHandler.class.getName());
	
	private int counter;
	
	static final String ECHO_REQ = "Hi,Lilinfeng. Welcome to Netty.$_";
	
	
	public EchoClientHandler() {
	}
	
	//客户端和服务端TCP链路建立成功之后，NIO线程会调用channelActive方法，发送查询时间的指令给服务端
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<10;i++){
			//将请求消息发送给服务端
			ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
		}
	}
	
	//服务器返回应答消息时，channelRead方法被调用
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		String body = (String) msg;
		System.out.println("Now is : " + body + "; the counter is : " + ++counter);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		//释放资源
		logger.warn("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
		
		
	}
	
	
	
}

























