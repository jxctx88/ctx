package cn.memedai.io.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;

public class TimeClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 8080;
		if(ArrayUtils.isNotEmpty(args)){
			port = Integer.valueOf(args[0]);
		}
		Socket socket = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
		try {
			socket = new Socket("127.0.0.1", port);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			out.println("QUERY TIME ORDER");
			System.out.println("Send order 2 server succeed.");
			String resp = in.readLine();
			System.out.println("NOW is :" + resp);
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(socket);
		}
		
		
	}

}
