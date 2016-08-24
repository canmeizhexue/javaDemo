
package com.canmeizhexue.javademo.gsontest;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

/**
 * Gson测试
 * 参考教程
 * http://www.jianshu.com/p/3108f1e44155
 * @author canmeizhexue
 *
 */
public class GsonTest {
	public static void main(String[]args){
		Gson gson = new Gson();
		//基本数据类型的解析

		int i = gson.fromJson("100", int.class);              //100
		double d = gson.fromJson("\"99.99\"", double.class);  //99.99
		boolean b = gson.fromJson("true", boolean.class);     // true
		String str = gson.fromJson("String", String.class);   // String
		
		System.out.println("i = "+i+"     d="+d+"    str="+str);
		
		
		// 基本数据类型的生成
		String jsonNumber = gson.toJson(100);       // 100
		String jsonBoolean = gson.toJson(false);    // false
		String jsonString = gson.toJson("String"); //"String"
		
		System.out.println(jsonString);
		
		//POJO类的生成与解析,,,特殊地方在于不会输出emailAddress=null这个,当然这个可以配置
		User user = new User("怪盗kidou",24);
		String jsonObject = gson.toJson(user); // {"name":"怪盗kidou","age":24}
		System.out.println(jsonObject);
		
		jsonString = "{\"name\":\"怪盗kidou\",\"age\":24,\"email\":\"88888888@qq.com\"}";
		user = gson.fromJson(jsonString, User.class);
		
		System.out.println(gson.toJson(user));
		
		jsonString = "{\"name\":\"怪盗kidou\",\"age\":24,\"email_address\":\"88888888@qq.com\"}";
		user = gson.fromJson(jsonString, User.class);
		// 可以看到再次生成的json串，，属性名字是啥
		System.out.println(gson.toJson(user));
		
		// 泛型支持
		Result<User> result = new Result();
		result.code = 200;
		result.message="success";
		result.data = user;
		String response = gson.toJson(result);
		Result<User> resultData=gson.fromJson(response, new TypeToken<Result<User>>(){}.getType());
		System.out.println(gson.toJson(resultData));
		
	}
	
	public static class User {
	    //省略其它
	    public String name;
	    public int age;
	    // 这个注解可以提供属性重命名，属性别名
	    @SerializedName(value = "EmailAddress", alternate = {"email", "email_address"})
	    public String emailAddress;
		public User(String name, int age) {
			super();
			this.name = name;
			this.age = age;
		}
	    
	}
	public static class Result<T> {
	    public int code;
	    public String message;
	    public T data;
	}
}
