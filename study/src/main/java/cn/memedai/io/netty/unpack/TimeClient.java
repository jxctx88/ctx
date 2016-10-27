package cn.memedai.io.netty.unpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import org.apache.commons.lang3.ArrayUtils;
/**
 * 粘包导致的异常案例
 * @author tongxiong.cheng
 *
 * @version 1.0
 */
public class TimeClient {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(ArrayUtils.isNotEmpty(args)){
			port = Integer.valueOf(args[0]);
		}
		
		new TimeClient().connect(port,"127.0.0.1");
		
		
	}

	private void connect(int port, String host) throws Exception {
		//配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			/**
			 * 为了尽可能的利用网络带宽，TCP总是希望尽可能的发送足够大的数据。
			 * 这里就涉及到一个名为Nagle的算法，该算法的目的就是为了尽可能发送大块数据，避免网络中充斥着许多小数据块。
			 * TCP_NODELAY就是用于启用或关于Nagle算法。如果要求高实时性，有数据发送时就马上发送，
			 * 就将该选项设置为true关闭Nagle算法；如果要减少发送次数减少网络交互，
			 * 就设置为false等累积一定大小后再发送。默认为false。
			 */
			.option(ChannelOption.TCP_NODELAY, true)
			.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch)
						throws Exception {
					ch.pipeline().addLast(new TimeClientHandler());
				}
			});
			
			//发送异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			
			//等待客户端链路关闭
			f.channel().closeFuture().sync();
		} finally {
			//优雅退出，释放NIO线程组
			group.shutdownGracefully();
		}
		
	}

}




















































