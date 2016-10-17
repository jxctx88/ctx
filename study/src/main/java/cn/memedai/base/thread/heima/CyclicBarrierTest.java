package cn.memedai.base.thread.heima;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**障碍器
 * CyclicBarrier 表示大家彼此等待，大家集合好后才开始出发，
 * 分散活动后又在i指定地点集合碰面，这就好比整个公司的人员利用周末时间集体郊游一样，
 * 先各自从家出发到公司集合后，再同时出发到公园游玩，在指定地点集合后再同时开始就餐……
 * @author tongxiong.cheng_C
 *
 */
public class CyclicBarrierTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cb = new CyclicBarrier(4); //4个线程同时到达
		 for(int i=0;i<4;i++){
			 Runnable runnaable = new Runnable(){
				 public void run(){
					 try {
						 // 返回当前在屏障处等待的参与者数目。getNumberWaiting()
						Thread.sleep((long)(Math.random()*10000));
					    System.out.println("线程"+ Thread.currentThread().getName()+"即将到达集合地点1，当前已有"+
					    		(cb.getNumberWaiting()+1)+"个已经到达，"+(cb.getNumberWaiting()==3?"都到齐了，继续走啊":"正在等候"));
					    cb.await();
					    Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点2，当前已有" + (cb.getNumberWaiting()+1) + "个已经到达，" + (cb.getNumberWaiting()==3?"都到齐了，继续走啊":"正在等候"));
						cb.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("线程" + Thread.currentThread().getName() + 
								"即将到达集合地点3，当前已有" + (cb.getNumberWaiting() + 1) + "个已经到达，" + (cb.getNumberWaiting()==3?"都到齐了，继续走啊":"正在等候"));						
						cb.await();	
					 
					 } catch (Exception e) {
						e.printStackTrace();
					}
					 
				 }
			 };
			 service.execute(runnaable);
		 }
		 service.shutdown();
		
	}

}























