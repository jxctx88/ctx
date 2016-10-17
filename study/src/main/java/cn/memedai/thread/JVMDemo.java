package cn.memedai.thread;

public class JVMDemo {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		while(true){
			byte[] bytes = new byte[100000000];
			System.out.println("hello world");
			Thread.sleep(1000);
 		}

	}

}
