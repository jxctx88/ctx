package cn.memedai.guava.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class ListenableFutureDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		
		for(int i=0;i<10;i++){
			final int j =i;
			ListenableFuture<Integer> explosion = service.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(1000));
					return j;
				}
			});
			
			Futures.addCallback(explosion, new FutureCallback<Integer>() {
				
				@Override
				public void onFailure(Throwable throwable) {
					System.out.println("失败了"+throwable.toString());
					
				}
				
				@Override
				public void onSuccess(Integer arg0) {
					
					System.out.println(Thread.currentThread().getName()+"成功返回结果为：" + arg0);
				}
			});
			
		}
	}

}
