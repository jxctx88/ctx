package cn.memedai.common.toolkit.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;

/**
 * Created by chengtx on 2016/7/29.
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    //@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        while (buf.isReadable()) { // (1)
            System.out.print((char) buf.readByte());
            System.out.flush();
        }

        ByteBuf respBuf = ctx.alloc().buffer().writeBytes("hello netty client?".getBytes("UTF-8"));
        ctx.writeAndFlush(respBuf);
    }

    //@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       ctx.close();
    }
}
