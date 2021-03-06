package cn.memedai.io.netty.unpack.delimiterbasedframedecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import org.apache.commons.lang3.ArrayUtils;
/**
 * 粘包导致的异常案例
 * @author tongxiong.cheng
 *
 * @version 1.0
 */
public class EchoServer {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(ArrayUtils.isNotEmpty(args)){
			try {
				port = Integer.valueOf(args[0]);
			} catch (NumberFormatException e) {
				//采用默认值
			}
		}
		
		new EchoServer().bind(port);
		
	}
	
	
	public void bind(int port) throws Exception{
		//配置服务的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
				.option(ChannelOption.SO_BACKLOG, 1024)
				.childHandler(new ChildChannelHandler());
		//绑定端口，同步等待成功
		ChannelFuture f = b.bind(port).sync();
		//等待服务器监听端口关闭
		f.channel().closeFuture().sync();
	} finally {
		//优雅退出，释放线程池资源
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
		
	}
	
	private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

		@Override
		protected void initChannel(SocketChannel arg0) throws Exception {
			ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
			arg0.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
			arg0.pipeline().addLast(new StringDecoder());
			arg0.pipeline().addLast(new EchoServerHandler());
		}
		
	}
	

}








































