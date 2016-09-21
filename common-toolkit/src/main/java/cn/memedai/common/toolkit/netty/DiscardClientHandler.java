package cn.memedai.common.toolkit.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by chengtx on 2016/8/1.
 */
public class DiscardClientHandler extends ChannelHandlerAdapter {

   // @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = ctx.alloc().buffer().writeBytes("hello netty server".getBytes("UTF-8"));
        ctx.writeAndFlush(buf);
    }

    //@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        while (buf.isReadable()) { // (1)
            System.out.print((char) buf.readByte());
            System.out.flush();
        }
    }
}
