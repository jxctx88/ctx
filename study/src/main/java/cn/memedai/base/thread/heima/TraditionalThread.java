package cn.memedai.base.thread.heima;

public class TraditionalThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1.创建Thread的子类来覆盖父类的方法new Thread(){};
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1"+Thread.currentThread().getName());
					System.out.println("2"+this.getName());
				}
				
			}
		};
		thread.start();
		//2、构造方法创建线程
		Thread thread2 = new Thread(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("1"+Thread.currentThread().getName());
					//System.out.println("2"+this.getName());
				}
			}
		});
		thread2.start();
		
		//运行thread的方法，因为子类的方法覆盖父类的方法，所以没有Runnable的方法
		new Thread(
			new Runnable(){
				@Override
				public void run() {
					while(true){
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("runnable:"+Thread.currentThread().getName());
						//System.out.println("2"+this.getName());
					}
				}
				
			}
		){
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread:"+Thread.currentThread().getName());
					//System.out.println("2"+this.getName());
				}
			};
		}.start();
		
	}

}
