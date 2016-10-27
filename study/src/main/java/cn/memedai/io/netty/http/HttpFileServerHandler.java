package cn.memedai.io.netty.http;

import java.io.File;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.SocketAddress;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpStatus;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import static io.netty.handler.codec.http.HttpResponseStatus.*;

public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

	private String url;
	
	public HttpFileServerHandler(String url){
		this.url = url;
	}
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx,
			FullHttpRequest request) throws Exception {
		if(!request.getDecoderResult().isSuccess()){
			sendError(ctx,BAD_REQUEST);
			return;
		}
		
		if(request.getMethod() != HttpMethod.GET){
			sendError(ctx,METHOD_NOT_ALLOWED);
			return;
		}
		final String uri = request.getUri();
		final String path = sanitizeUri(uri);
		if(path == null){
			sendError(ctx,FORBIDDEN);
			return;
		}
		
		//...
		File file = new File(url);
		if(!file.isFile()){
			sendError(ctx,NOT_FOUND);
			return;
		}
		
		RandomAccessFile randomAccessFile = null;
		try {
			randomAccessFile = new RandomAccessFile(file,"r");//以只读的方式打开文件
		} catch (Exception e) {
			sendError(ctx,NOT_FOUND);
			return;
		} 
		
		long fileLength = randomAccessFile.length();
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, OK);
		setContendLength(response,fileLength);
		setContentTypeHeader(response,file);
		if(HttpHeaders.isKeepAlive(request)){
			//response.headers().set(CONNECTION,HttpHeaders.)
			
		}

	}

	private String sanitizeUri(String uri) {
		try {
			uri = URLDecoder.decode(uri,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			try {
				uri = URLDecoder.decode(uri,"ISO-8859-1");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	private void setContendLength(HttpResponse response, long fileLength) {
		// TODO Auto-generated method stub
		
	}

	private void setContentTypeHeader(HttpResponse response, File file) {
		// TODO Auto-generated method stub
		
	}

	private boolean isKeepAlive(FullHttpRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	private void sendError(ChannelHandlerContext ctx,HttpResponseStatus status) {
		
	}

	

}


























































