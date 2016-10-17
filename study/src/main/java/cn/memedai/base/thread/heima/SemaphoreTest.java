package cn.memedai.base.thread.heima;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	/**
	 * Semaphore实现的功能就类似厕所有3个坑，假如有10个人要上厕所，那么同时只能有多少个人去上厕所呢？同时只能有5个人能够占用，
	 * 当3个人中 的任何一个人让开后，其中等待的另外7个人中又有一个人可以占用了。
	 * 另外等待的7个人中可以是随机获得优先机会，也可以是按照先来后到的顺序获得机会，
	 * 这取决于构造Semaphore对象时传入的参数选项。单个信号量的Semaphore对象可以实现互斥锁的功能，
	 * 并且可以是由一个线程获得了“锁”，再由另一个线程释放“锁”，这可应用于死锁恢复的一些场合。
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();//创建一个可根据需要创建新线程的线程池，但是在以前构造的线程可用时将重用它们。
		final Semaphore sp = new Semaphore(3,true);// 只能3个线程同时访问
		 //模拟10个客户端访问
		
			Runnable runnable = new Runnable(){
				@Override
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程"+ Thread.currentThread().getName()+"进入，当前已有"+(3-sp.availablePermits())+"个并发");
					try {
						Thread.sleep((long)(Math.random()*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					synchronized(SemaphoreTest.class){
					System.out.println("线程"+ Thread.currentThread().getName()+"即将离开");
					sp.release();
					//下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
					System.out.println("线程" + Thread.currentThread().getName() + 
							"已离开，当前已有" + (3-sp.availablePermits()) + "个并发");
					}
					
				}
				
			};
			for(int i=0;i<10;i++){
			service.execute(runnable);
		}
		 // 退出线程池
		service.shutdown();
	}

}
