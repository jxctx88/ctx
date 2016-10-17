package cn.memedai.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.codec.Charsets;

public class TimeClientHandle implements Runnable {
	
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel socketChannel;
	private volatile boolean stop;
	
	public TimeClientHandle(String host, int port){
		this.host = host == null ? "127.0.0.1" : host;
		this.port = port;
		try {
			selector = Selector.open();
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	

	@Override
	public void run() {

		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		while(!stop){
			try {
				selector.select(1000);
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while(it.hasNext()){
					key = it.next();
					it.remove();
					try{
						handleInput(key);
					}catch (Exception e) {
						if(key != null){
							key.cancel();
							if(key.channel() != null)
								key.channel().close();
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		//多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
		if(selector != null){
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	private void handleInput(SelectionKey key) throws IOException {
		if(key.isValid()){
			
			SocketChannel sc = (SocketChannel) key.channel();
			
			//是否已完成其套接字连接操作
			if(key.isConnectable()){
				if(sc.finishConnect()){
					//连接成功，注册到多路复用器上，监听READ操作
					sc.register(selector, SelectionKey.OP_READ);
					//发送请求信息给服务器
					doWrite(sc);
				}else{
					System.exit(1);//连接失败，进程退出
				}
			}
			
			//是否已准备好进行读取。
			if(key.isReadable()){
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if(readBytes > 0){
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes,Charsets.UTF_8);
					System.out.println("Now is : " + body);
					this.stop = true;
				}else if(readBytes < 0){
					//对端链路关闭
					key.cancel();
					sc.close();
				}else
					;//读取到0字节，忽略
			}
			
			
		}
		
	}

	/**
	 * 注册连接
	 * @throws IOException
	 */
	private void doConnect() throws IOException {
		//如果直接连接成功，则注册到多路复用器上，发送请求信息，读应答
		if(socketChannel.connect(new InetSocketAddress(host,port))){
			socketChannel.register(selector, SelectionKey.OP_READ);
			doWrite(socketChannel);
		}else
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
	}

	/**
	 * 写人数据
	 * @param sc
	 * @throws IOException
	 */
	private void doWrite(SocketChannel sc) throws IOException {
		byte[] req = "QUERY TIME ORDER".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if(!writeBuffer.hasRemaining()){
			System.out.println("Send order 2 server succeed.");
		}
	}

}







































































