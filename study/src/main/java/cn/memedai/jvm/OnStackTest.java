package cn.memedai.jvm;

import java.nio.ByteBuffer;

/**
 * 线程私有对象分配在栈上，
 * @author admin
 *
 */
public class OnStackTest {
	
	public static class User{
		public int id = 0;
		public String name = "";
	}
	
	
	public static void alloc(){
		//这里user对像约16字节，遍历了1亿次约1.5G，内存溢出，
		//但是由于是局部对象，非逃逸对象，分配在栈里面，函数调用完就销毁，所有不内存溢出
		User u = new User();
		u.id = 5;
		u.name = "gay";
		//ByteBuffer.allocateDirect(capacity)
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long b = System.currentTimeMillis();
		for(int i = 0;i<100000000;i++){
			alloc();
		}
		long e = System.currentTimeMillis();
		System.out.println(e-b);

	}

}
