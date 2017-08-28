/**
 *  ---------------www.wisdragon.com--------------
 */
package com.login.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * 公司：上海智隆信息技术有限公司
 * 作者：张高俊
 * 所属包 : com.wisdragon.meeting.jsb
 * 创建日期：Jan 15, 2015   时间：4:46:51 PM
 * 更新日期：Jan 15, 2015   时间：4:46:51 PM
 * 描述： 数据库连接工厂，在一次会话中保持常连接。如果出现连接中断或者前置程序重启，则只创建一个连接
 */
public class ConnectionFactory {

	private static Connection conn = null;
	
	public static Connection getConnection() throws Exception{
		
		if(conn == null || conn.isClosed()){
			
			Class.forName(Constants.getDriver());//动态加载类
			conn = DriverManager.getConnection(Constants.getUrl(), Constants.getUser(), Constants.getPwd());
		}
		
		return conn;
	}
	
	public static void close(){
		
		if(conn != null){
			
			try{
				conn.close();
				conn = null;
			}catch(SQLException e){
				
				e.printStackTrace();
			}
		}
	}
}
