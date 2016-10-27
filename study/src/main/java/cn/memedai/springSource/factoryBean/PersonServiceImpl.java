package cn.memedai.springSource.factoryBean;

public class PersonServiceImpl implements PersonService {  
    public String name;  
  
      
    @Override  
    public String getName() {  
        return name;  
    }  
  
    @Override  
    public String sayHello() {  
        System.out.println(this.name);  
        return "sayHello";  
    }  
  
    @Override  
    public void setName(String name) {  
        this.name = name;  
          
    }  
}  