/**
 *  ---------------www.wisdragon.com--------------
 */
package com.login.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * 公司：上海智隆信息技术有限公司
 * 作者：张高俊
 * 所属包 : com.wisdragon.meeting.jsb
 * 创建日期：Sep 4, 2014   时间：9:09:07 AM
 * 更新日期：Sep 4, 2014   时间：9:09:07 AM
 * 描述： 数据库操作类
 */
public class DBUtil {

	private ResultSet rs;
	private PreparedStatement psta;
	private Connection conn;
	
	public DBUtil(){
		
		try{
			
			
			this.conn = ConnectionFactory.getConnection();
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *  作者：张其刚 
	 *  创建日期：Sep 4, 2014 9:20:10 AM
	 *  参数信息：@param sql
	 *  参数信息：@param objects
	 *  参数信息：@return
	 *  返回值：ResultSet
	 *  描述：查询方法
	 */
	public ResultSet query(String sql, Object... objects){
		
		try{
			
			this.psta = this.conn.prepareStatement(sql);
			
			if(objects != null && objects.length != 0){
				
				for(int i = 0; i < objects.length; i++){
					
					this.psta.setObject(i + 1, objects[i]);
				}
			}
			
			this.rs = this.psta.executeQuery();
		}catch(SQLException e){
			
			e.printStackTrace();
		}
		
		return this.rs;
	}
	
	public int save(String sql, Object... objects){
		
		int flag = -1;
		try{
			
			this.psta = this.conn.prepareStatement(sql);
			
			if(objects != null && objects.length != 0){
				
				for(int i = 0; i < objects.length; i++){
					
					this.psta.setObject(i + 1, objects[i]);
				}
			}
			
			
			
			flag = this.psta.executeUpdate();
			
		}catch(SQLException e){
			
			e.printStackTrace();
		}finally{
			if(this.psta != null){
				try {
					this.psta.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				this.psta = null;
			}
		}
		
		return flag;
	}
	/*public boolean insertBatch(String sql,Object... objects){
		boolean flg = true;
		try {
			this.psta = this.conn.prepareStatement(sql);
			if(objects != null && objects.length != 0){
				
				for(int i = 0; i < objects.length; i++){
					
					this.psta.setObject(i + 1, objects[i]);
					if(i%3000==0){
						this.psta.executeBatch();
						this.psta.clearBatch();
					}
				}
			}
		} catch (SQLException e) {
			flg = false;
			e.printStackTrace();
		}
		return flg;
	}*/
	public void close(){
		
		try{
			if(this.rs != null){
				
				this.rs.close();
				this.rs = null;
			}
			
			if(this.psta != null){
				
				this.psta.close();
				this.psta = null;
			}

			/*if(this.conn != null){
				
				this.conn.close();
				this.conn = null;
			}*/
		}catch(SQLException e){
			
			e.printStackTrace();
		}
	}
}
