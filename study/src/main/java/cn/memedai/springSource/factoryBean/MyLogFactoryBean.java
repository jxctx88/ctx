package cn.memedai.springSource.factoryBean;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;


public class MyLogFactoryBean implements FactoryBean<Object>, InitializingBean,  
        DisposableBean {  
    // 被代理对象实现的接口名（在使用Proxy时需要用到，用于决定生成的代理对象类型）    
    private String interfaceName;    
    
    // 被代理的对象    
    private Object target;    
    
    // 生成的代理对象    
    private Object proxyObj;    
      
    @Override  
    public void destroy() throws Exception {  
         System.out.println("distory...");    
    }  
  
    @Override  
    public void afterPropertiesSet() throws Exception {  
        proxyObj = Proxy.newProxyInstance(this.getClass().getClassLoader(),  
                new Class[] { Class.forName(interfaceName) },  
                new InvocationHandler() {  
  
                    public Object invoke(Object proxy, Method method,  
                            Object[] args) throws Throwable {  
                        System.out.println("method:" + method.getName());  
                        System.out.println("Method before...");  
                        Object result = method.invoke(target, args);  
                        System.out.println("Method after...");  
                        return result;  
                    }  
                });  
    }  
  
    @Override  
    public Object getObject() throws Exception {  
        return proxyObj;   
    }  
  
    @Override  
    public Class<?> getObjectType() {  
        return proxyObj == null ? Object.class : proxyObj.getClass();    
    }  
  
    @Override  
    public boolean isSingleton() {  
        return false;  
    }  
  
    public String getInterfaceName() {    
        return interfaceName;    
    }    
    
    public void setInterfaceName(String interfaceName) {    
        this.interfaceName = interfaceName;    
    }    
    
    public Object getTarget() {    
        return target;    
    }    
    
    public void setTarget(Object target) {    
        this.target = target;    
    }    
}  