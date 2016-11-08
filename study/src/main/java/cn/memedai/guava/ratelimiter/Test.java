package cn.memedai.guava.ratelimiter;

import java.util.List;
import java.util.concurrent.Executor;

import com.google.common.util.concurrent.RateLimiter;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//RateLimiter rateLimiter = RateLimiter.create(2);
		
	}
	
	/*
	//速率是每秒两个许可
	final RateLimiter rateLimiter = RateLimiter.create(2.0);
	void submitTasks(List tasks, Executor executor) {
	    for (Runnable task : tasks) {
	        rateLimiter.acquire(); // 也许需要等待
	        executor.execute(task);
	    }
	}*/
	
	
	/*
	 * 并希望以每秒5kb的速率处理它。可以通过要求每个字节代表一个许可，然后指定每秒5000个许可来完成：
	 // 每秒5000个许可
	 final RateLimiter rateLimiter = RateLimiter.create(5000.0);
	 void submitPacket(byte[] packet) {
	    rateLimiter.acquire(packet.length);
	    networkService.send(packet);
	}*/



}
