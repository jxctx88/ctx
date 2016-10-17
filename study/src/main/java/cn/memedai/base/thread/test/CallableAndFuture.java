package cn.memedai.base.thread.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class CallableAndFuture {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//count1();
		//count2();
		//CompletionServiceTest();
		ExecutorsServiceTest();
	}

	private static void CompletionServiceTest() {
		ExecutorService threadPool =  Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
		for(int i=0;i<100;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(20);
					return seq;
				}
			});
		}
		long startDate =  System.currentTimeMillis();
		
		for(int i=0;i<100;i++){
			try {
				System.out.println(completionService.take().get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("completionService总共花费时间："+(System.currentTimeMillis()-startDate)+"ms");
		threadPool.shutdown();
	}
	
	private static void ExecutorsServiceTest() {
		ExecutorService threadPool =  Executors.newFixedThreadPool(10);
		//CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
		List<Future> list =  new LinkedList<Future>();
		for(int i=0;i<100;i++){
			final int seq = i;
			Future<Integer> future = threadPool.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(20);
					return seq;
				}
			});
			list.add(future);
		}
		long startDate =  System.currentTimeMillis();
		
		for(int i=0;i<list.size();i++){
			try {
				System.out.println(list.get(i).get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("ExecutorsService总共花费时间："+(System.currentTimeMillis()-startDate)+"ms");
		threadPool.shutdown();
	}
	
	
	//使用阻塞容器保存每次Executor处理的结果，在后面进行统一处理  
    private static void count1() throws Exception{  
        ExecutorService exec = Executors.newCachedThreadPool();  
        BlockingQueue<Future<Integer>> queue = new LinkedBlockingQueue<Future<Integer>>();  
        for(int i=0; i<10; i++){  
            Future<Integer> future =exec.submit(getTask());  
            queue.add(future);  
        }  
        int sum = 0;  
        int queueSize = queue.size();  
        for(int i=0; i<queueSize; i++){  
            sum += queue.take().get();  
        }  
        System.out.println("总数为："+sum);  
        exec.shutdown();  
    }  
    //使用CompletionService(完成服务)保持Executor处理的结果  
    private static void count2() throws InterruptedException, ExecutionException{  
        ExecutorService exec = Executors.newFixedThreadPool(10);  
        CompletionService<Integer> execcomp = new ExecutorCompletionService<Integer>(exec);  
        for(int i=0; i<10; i++){  
            execcomp.submit(getTask());  
        }  
        int sum = 0;  
        for(int i=0; i<10; i++){  
        	//检索并移除表示下一个已完成任务的 Future，如果目前不存在这样的任务，则等待。  
            Future<Integer> future = execcomp.take();  
            sum += future.get();  
        }  
        System.out.println("总数为："+sum);  
        exec.shutdown();  
    }  
    //得到一个任务  
    public static Callable<Integer> getTask(){  
        final Random rand = new Random();  
        Callable<Integer> task = new Callable<Integer>(){  
            @Override  
            public Integer call() throws Exception {  
                int i = rand.nextInt(10);  
                int j = rand.nextInt(10);  
                int sum = i*j;  
                System.out.print(sum+"\t");  
                return sum;  
            }  
        };  
        return task;  
    } 

}
