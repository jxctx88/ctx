package cn.memedai.apache.commons.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/**
 * 文件修改后5秒钟后生效
 * @author admin
 *
 */
public class PropertiesConfigurationDemo {
	
	private static PropertiesConfiguration config;
	
	static {
		try {
			config = new PropertiesConfiguration("D:/ProgramFiles/Workspaces/myeclipseNew10/springSource/src/main/resources/config.properties");
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized String getProperty(String key){
		return (String)config.getProperty(key);
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		for(;;){
			System.out.println(PropertiesConfigurationDemo.getProperty("name"));
			Thread.sleep(2000);
		}

	}

}
