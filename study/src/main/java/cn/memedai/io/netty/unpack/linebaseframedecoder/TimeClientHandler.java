package cn.memedai.io.netty.unpack.linebaseframedecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import org.apache.log4j.Logger;

public class TimeClientHandler extends ChannelHandlerAdapter{

	private static final Logger logger = Logger.getLogger(TimeClientHandler.class.getName());
	
	private int counter;
	
	private byte[] req;
	
	public TimeClientHandler() {
		req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
	}
	
	//客户端和服务端TCP链路建立成功之后，NIO线程会调用channelActive方法，发送查询时间的指令给服务端
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf message = null;
		for(int i=0;i<100;i++){
			message = Unpooled.buffer(req.length);
			message.writeBytes(req);
			//将请求消息发送给服务端
			ctx.writeAndFlush(message);
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

























