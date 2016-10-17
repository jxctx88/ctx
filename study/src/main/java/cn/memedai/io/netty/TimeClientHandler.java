package cn.memedai.io.netty;

import java.net.SocketAddress;

import org.apache.commons.codec.Charsets;
import org.apache.log4j.Logger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class TimeClientHandler extends ChannelHandlerAdapter{

	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
	
	private final ByteBuf firstMessage;
	
	public TimeClientHandler() {
		byte[] req = "QUERY TIME ORDER".getBytes();
		firstMessage = Unpooled.buffer(req.length);
		firstMessage.writeBytes(req);
	}
	
	//客户端和服务端TCP链路建立成功之后，NIO线程会调用channelActive方法，发送查询时间的指令给服务端
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		//将请求消息发送给服务端
		ctx.writeAndFlush(firstMessage);
	}
	
	//服务器返回应答消息时，channelRead方法被调用
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		ByteBuf buf = (ByteBuf) msg;
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req,Charsets.UTF_8);
		System.out.println("Now is : " + body);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		//释放资源
		logger.warn("Unexpected exception from downstream : " + cause.getMessage());
		ctx.close();
		
		
	}
	
	
	
}

























