package cn.memedai.base.thread.chinapnr;

public class TraditionalThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread thread = new Thread(){
			public void run(){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("1"+Thread.currentThread().getName());
				System.out.println("2"+this.getName());
			}
		};
		thread.start();
		
		//2、构造方法创建线程
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1"+Thread.currentThread().getName());
				}
			}
		});
		thread2.start();

	}

}
