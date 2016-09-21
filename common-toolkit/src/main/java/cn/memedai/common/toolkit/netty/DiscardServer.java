package cn.memedai.common.toolkit.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by chengtx on 2016/7/29.
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port){
        this.port = port;
    }


    public void run() throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)// (3)
                    .childHandler(new MyChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }

    public static void main(String[] args) throws Exception {
        int port=8999;
        /*if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }*/
        new DiscardServer(port).run();
    }

}
