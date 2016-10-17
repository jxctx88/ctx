package cn.memedai.metrics;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import cn.memedai.joda.Test;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;

public class TestGauges {
	
	private static final MetricRegistry metrics = new MetricRegistry();
	
	private static Queue<String> queue = new LinkedBlockingDeque<String>();
	
	private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		reporter.start(3, TimeUnit.SECONDS); 
		
		//实例化一个Gauge
		Gauge<Integer> gauge = new Gauge<Integer>() {

			@Override
			public Integer getValue() {
				return queue.size();
			}
		};
		
		//Counter counter = new Counter();
		
		//注册到容器中
		metrics.register(MetricRegistry.name(Test.class, "pending-job","size"), gauge);
		
		//测试JMX
		JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();
		jmxReporter.start();
		
		//模拟数据
		for(int i = 0 ;i<20;i++){
			queue.add("a");
			Thread.sleep(1000);
		}
		
	}

}
