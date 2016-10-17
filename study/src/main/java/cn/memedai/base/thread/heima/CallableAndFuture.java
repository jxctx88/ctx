package cn.memedai.base.thread.heima;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable和Future，它俩很有意思的，一个产生结果，一个拿到结果。
 * Callable接口类似于Runnable，从名字就可以看出来了，但是Runnable不会返回结果，
 * 并且无法抛出返回结果的异常，而Callable功能更强大一些，被线程执行后，可以返回值，
 * 这个返回值可以被Future拿到，也就是说，Future可以拿到异步执行任务的返回值，
 * @author tongxiong.cheng_C
 *
 */
public class CallableAndFuture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>(
				) {

					@Override
					public String call() throws Exception {
						Thread.sleep(2000);
						
						return "hello";
					}
		});
		System.out.println("等待结果");
		try {
			System.out.println("拿到结果："+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}*/
		
		/**
		 * 这个总体获取返回数据的速度会更快些
		 */
		ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for(int i=1;i<=10;i++){
			final int seq =i;
			completionService.submit(new Callable<Integer>() {
				
				@Override
				public Integer call() throws Exception {
							Thread.sleep(new Random().nextInt(10000));
					return seq;
				}
			});
		}
		for(int i=0;i<10;i++){
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
