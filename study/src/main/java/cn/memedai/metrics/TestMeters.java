package cn.memedai.metrics;

import java.util.concurrent.TimeUnit;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
/**
 * -- Meters ----------------------------------------------------------------------
   cn.memedai.metrics.TestMeters.request
                      总共请求参数 count = 31
                每秒请求    mean rate = 10.32 events/second
       每1分钟   1-minute rate = 0.00 events/second
       每5分钟   5-minute rate = 0.00 events/second
   每15分钟  15-minute rate = 0.00 events/second
 * @author admin
 *
 */
public class TestMeters {
	/**
     * 实例化一个registry，最核心的一个模块，相当于一个应用程序的metrics系统的容器，维护一个Map
     */
	private static final MetricRegistry metrics = new MetricRegistry();
	
	/**
     * 在控制台上打印输出
     */
	private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();

	private static final Meter requests = metrics.meter(MetricRegistry.name(TestMeters.class,"request"));

	public static void handlerRequest(){
		requests.mark();
	}
	
	public static void main(String[] args) throws Exception {
		reporter.start(3, TimeUnit.SECONDS);
		while(true){
			handlerRequest();
			Thread.sleep(100);
		}
	}
	

}
