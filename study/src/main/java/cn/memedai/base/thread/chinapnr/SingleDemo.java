package cn.memedai.base.thread.chinapnr;

public class SingleDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
	

}
/**
 * 恶汉模式
 * @author tongxiong.cheng_C
 *
 */
class VillianMode{
	private static  VillianMode villianMode = new VillianMode();
	private VillianMode(){}
	public static VillianMode getInstance(){
		return villianMode;
			
	}
}

/**
 * 懒汉模式
 * @author tongxiong.cheng_C
 *
 */
class LazyMode{
	private static  LazyMode lazyMode = null;
	private LazyMode(){}
	public static LazyMode getInstance(){
		if(lazyMode == null){
			synchronized(LazyMode.class){
				if(null == lazyMode){
					//-->
					lazyMode = new LazyMode();
				}
			}
		}
		return lazyMode;
	}
}

