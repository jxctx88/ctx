package cn.memedai.base.thread.heima;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 在一对线程进行数据交换，如果接受的变量类型与实际的交换来的数据类型不符，线程不报错
 * @author tongxiong.cheng_C
 *
 */
public class ExchangeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					String data1 = "zxx";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "换出去");
					Thread.sleep((long) (Math.random() * 1000));
					String data2 =  (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据为" + data2);

				} catch (Exception e) {

				}
			}
		});
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {

					String data1 = "lhm";
					System.out.println("线程" + Thread.currentThread().getName()
							+ "正在把数据" + data1 + "换出去");
					Thread.sleep((long) (Math.random() * 1000));
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程" + Thread.currentThread().getName()
							+ "换回的数据为" + data2);
				} catch (Exception e) {

				}
			}
		});
		service.shutdown();
	}

}
