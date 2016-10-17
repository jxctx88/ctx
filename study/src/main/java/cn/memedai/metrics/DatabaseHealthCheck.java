package cn.memedai.metrics;

import java.util.Map;
import java.util.Random;

import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;
/**
 * Health Checks，用于对Application、其子模块或者关联模块的运行是否正常做检测 <br/>
 * <p>
 * 需要重新实例化一个Metrics容器HealthCheckRegistry，<br/>
 * 待检测模块继承抽象类HealthCheck并实现check()方法即可，<br/>
 * 然后将该模块注册到HealthCheckRegistry中，判断的时候通过isHealthy()接口即可
 * @author admin
 *
 */
public class DatabaseHealthCheck extends HealthCheck {
	private final Database database;
	
	public DatabaseHealthCheck(Database database) {
        this.database = database;
    }
	
	/**
	 * 模拟database的ping方法
	 * @return 随机返回boolean值
	 * @author admin
	 *
	 */
	static class Database{
		
		public boolean ping(){
			Random random = new Random();
			return random.nextBoolean();
		}
	}
	
	@Override
	protected Result check() throws Exception {
		if(database.ping()){
			return Result.healthy();
		}
		return Result.unhealthy("Can't ping database.");
	}
	
	
	public static void main(String[] args) {
		HealthCheckRegistry registry = new HealthCheckRegistry();
		registry.register("database1", new DatabaseHealthCheck(new Database()));
		registry.register("database2", new DatabaseHealthCheck(new Database()));
		while(true){
			for(Map.Entry<String, Result> entry : registry.runHealthChecks().entrySet()){
				if(entry.getValue().isHealthy()){
					System.out.println(entry.getKey() + ": OK");
				}else{
					System.out.println(entry.getKey() + ": FAIL, error message: " + entry.getValue().getMessage());
					final Throwable e = entry.getValue().getError();
					if(e != null){
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				
			}
		}
		
	}

}
