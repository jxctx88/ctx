package cn.memedai.springSource.impl;

import cn.memedai.springSource.Iuser;

public class UserImpl implements Iuser {
	
	public UserImpl(String username,int age){
		this.username = username;
		this.age = age;
	}
	
	/*public UserImpl(){
		
	}*/
	
	public void init(){
		System.out.println("init userImpl : " + toString());
	}
	
	private String username;
	private int age;
	private String nickname;
	private String sex;

	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void hello(String name) {
		if(null == name){
			System.out.println("hello world!");
		}else{
			System.out.println("hello " + name ); 
		}
	}

	@Override
	public String toString() {
		return "UserImpl [username=" + username + ", age=" + age
				+ ", nickname=" + nickname + ", sex=" + sex + "]";
	}
	
	

}
