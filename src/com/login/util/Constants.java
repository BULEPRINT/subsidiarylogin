/**  
 * 工程: javaDemo <br>
 * 标题: Constants.java <br>
 * 描述: TODO <br>
 * 作者: 张高俊 <br>
 * 时间: Oct 29, 2014 2:03:07 PM <br>
 *
 */

package com.login.util;

import java.io.IOException;
import java.util.Properties;


/**
 * 类: Constants <br>
 * 描述: TODO <br>
 * 作者: 张高俊 <br>
 * 时间: Oct 29, 2014 2:03:07 PM
 */
public class Constants {



	private static String url;
	private static String pwd;
	private static String user;
	private static String driver;
	
	static{
		
		try{
			
			Properties properties = new Properties();
			properties.load(Constants.class.getResourceAsStream("/jdbc.properties"));

			url = properties.getProperty("jdbc.url");
			pwd = properties.getProperty("jdbc.pwd");
			user = properties.getProperty("jdbc.user");
			driver = properties.getProperty("jdbc.driver");
		}catch(IOException e){
			
			e.printStackTrace();
		}
	}
	
	public static String getDriver() {
	
		return driver;
	}

	public static String getUrl() {
	
		return url;
	}

	
	public static String getPwd() {
	
		return pwd;
	}

	
	public static String getUser() {
	
		return user;
	}
	
	

}
