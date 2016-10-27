package cn.memedai.io.netty.codec.msgpack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

public class Demo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		List<String> src = new ArrayList<String>();
		src.add("msgpack");
		src.add("kumofs");
		src.add("viver");
		MessagePack msgpack = new MessagePack();
		//序列化
		byte[] raw = msgpack.write(src);
		//反序列化
		List<String> dst1 = msgpack.read(raw,Templates.tList(Templates.TString));
		System.out.println(dst1.get(0));
		System.out.println(dst1.get(1));
		System.out.println(dst1.get(2));
	}

}































