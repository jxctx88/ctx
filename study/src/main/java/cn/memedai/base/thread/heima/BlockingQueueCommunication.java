package cn.memedai.base.thread.heima;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 本例介绍一个特殊的队列:BlockingQueue,如果BlockQueue是空的,
 * 从BlockingQueue取东西的操作将会被阻断进入等待状态,
 * 直到BlockingQueue进了东西才会被唤醒.同样,如果BlockingQueue是满的,
 * 任何试图往里存东西的操作也会被阻断进入等待状态,直到BlockingQueue里有空间才会被唤醒继续操作.
 * 特别提示：可以适用于多生产者和消费者
 * @author tongxiong.cheng_C
 *
 */
public class BlockingQueueCommunication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		final Business business = new Business();
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					business.sub(i);
				}
			}
			
		}).start();
		for(int i=1;i<=50;i++){
			business.main(i);
		}
		
	}
	static class Business{
		//阻塞队列的应用
		BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);
		{
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		public   void sub(int i){
			try {
				queue1.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int j=1;j<=10;j++){
				System.out.println("sub thread sequence of "+j+",loop of "+i);
			}
			try {
				queue2.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		public  void main(int i){
			try {
				queue2.put(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for(int j=1;j<=100;j++){
				System.out.println("main thread sequence of "+j+",loop of "+i);
			}
			try {
				queue1.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
