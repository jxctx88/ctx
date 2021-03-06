package cn.memedai.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

import org.apache.commons.codec.Charsets;

public class ReadCompletionHander implements
		CompletionHandler<Integer, ByteBuffer> {

	private AsynchronousSocketChannel channel;
	
	public ReadCompletionHander(AsynchronousSocketChannel channel){
		if(this.channel == null)
			this.channel = channel;
	}
	
	@Override
	public void completed(Integer result, ByteBuffer attachment) {
		attachment.flip();
		byte[] body = new byte[attachment.remaining()];
		attachment.get(body);
		try {
			String req = new String(body,Charsets.UTF_8);
			System.out.println("The time server receive order : " + req);
			String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req) ? 
					new Date(System.currentTimeMillis()).toString() : 
						"BAD ORDER";
			doWrite(currentTime);
		} catch (Exception e) {
		}
		
	}

	private void doWrite(String currentTime) {
		if(currentTime != null && currentTime.trim().length() > 0){
			byte[] bytes = currentTime.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer,writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {

				@Override
				public void completed(Integer result, ByteBuffer buffer) {
					//如果没有发送完成，继续发送
					if(buffer.hasRemaining())
						channel.write(buffer,buffer,this);
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					try {
						channel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			});
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
























































