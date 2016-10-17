package cn.memedai.metrics;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;
/**
 * Histograms主要使用来统计数据的分布情况，<br/>
 * 最大值、最小值、平均值、中位数，百分比（75%、90%、95%、98%、99%和99.9%）。<br/>
 * 例如，需要统计某个页面的请求响应时间分布情况，可以使用该种类型的Metrics进行统计
 * @author admin
 *
 */
public class TestHistograms {
	
	private static final MetricRegistry metrics = new MetricRegistry();
	
	private static final  ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

	private static final Histogram randomNums = metrics.histogram(MetricRegistry.name(TestHistograms.class, "random"));
	
	public static void handleRequest(double random){
		randomNums.update((int) (random*100));
	}
	
	public static void main(String[] args) throws Exception {
		reporter.start(3, TimeUnit.SECONDS);
		Random rand = new Random();
		while(true){
			handleRequest(rand.nextDouble());
			Thread.sleep(100);
		}
	}
	
}
