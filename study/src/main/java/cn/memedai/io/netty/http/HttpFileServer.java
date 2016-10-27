package cn.memedai.io.netty.http;


import org.apache.commons.lang3.ArrayUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * HTTP文件服务器
 * @author tongxiong.cheng
 *
 * @version 1.0
 */
public class HttpFileServer {
	
	private static final String DEFAULT_URL = "/src/com/phei/netty";
	
	
	
	public static void main(String[] args) throws Exception {
		int port = 8080;
		if(ArrayUtils.isNotEmpty(args)){
			port = Integer.parseInt(args[0]);
		}
		String url = DEFAULT_URL;
		if(args.length > 1){
			url = args[1];
			new HttpFileServer().run(port, url);
		}
		
	}
	
	public void run(final int port ,final String url) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch)
								throws Exception {
							//解码
							ch.pipeline().addLast("http-decoder",
									new HttpRequestDecoder());
							ch.pipeline().addLast("http-aggregator",
									new HttpObjectAggregator(65536));
							//编码
							ch.pipeline().addLast("http-encoder",
									new HttpResponseEncoder());
							ch.pipeline().addLast("http-chunked",
									new ChunkedWriteHandler());
							ch.pipeline().addLast("fileServerHandler",
									new HttpFileServerHandler(url));
						}

					});

			ChannelFuture future = b.bind("127.0.0.1", port).sync();
			System.out.println("HTTP 文件目录服务器启动，网址是 : " + "http://127.0.0.1:" + port + url);
			future.channel().closeFuture().sync();
		}finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
		
	}
	
	
}











































