/**
 * @author tongxiong.cheng
 * @date 2017-1-22 下午2:22:30
 * @version 1.0
 */
package cn.memedai.guava.object;

import java.util.Date;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

/**
 * @author tongxiong.cheng
 * @date 2017-1-22 下午2:22:30
 * @version 1.0
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String ss = null;
//		String aa = "1";
//		System.out.println(ss.equals(aa));//java.lang.NullPointerException
		System.out.println(Objects.equal("a", "a"));; // returns true
		System.out.println(Objects.equal(null, "a"));; // returns false
		System.out.println(Objects.equal("a", null));; // returns false
		System.out.println(Objects.equal(null, null));; // returns true
		
		// Returns "ClassName{x=1}"
		User user  = new User();
		user.setUsername("xiong");
		user.setPassword(11111);
		user.setBirthday(new Date());
		System.out.println(user);;
		// Returns "MyObject{x=1}"
		System.out.println(Objects.toStringHelper("User").add("x", 1)
				.toString());;


	}
	

}

class User{
	private String username;
	private int password;
	private Date birthday;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPassword() {
		return password;
	}
	public void setPassword(int password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("x", 1)
				.toString();
	}
	
	//实现一个比较器[Comparator]
	class Person implements Comparable<Person> {
		  private String lastName;
		  private String firstName;
		  private int zipCode;
		 
		  public int compareTo(Person that) {
			  return ComparisonChain.start()
					  .compare(this.lastName, that.lastName)
					  .compare(this.firstName, that.firstName)
		              .compare(this.zipCode, that.zipCode, Ordering.natural().nullsLast())
		              .result();

		  }
		}

}