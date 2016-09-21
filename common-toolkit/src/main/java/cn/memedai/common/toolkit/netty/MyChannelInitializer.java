package cn.memedai.common.toolkit.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;


/**
 * Created by chengtx on 2016/7/29.
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //handler相当于过滤器
        ch.pipeline().addLast(new DiscardServerHandler());
    }
}
