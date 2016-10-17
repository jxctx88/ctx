package cn.memedai.base.thread.chinapnr;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 * 缓存
 * @author tongxiong.cheng_C
 *
 */
public class CacheData {
	private Map<String,Object> cache = new HashMap<String,Object>();

	public static void main(String[] args) {

	}
	
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			if(null == value){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				try{
					if(null ==value){
						value = "aaa";//从数据库获取值
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		}finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
