/**
 *  ---------------www.wisdragon.com--------------
 */
package com.login.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * ��˾���Ϻ���¡��Ϣ�������޹�˾
 * ���ߣ��Ÿ߿�
 * ������ : com.wisdragon.meeting.jsb
 * �������ڣ�Sep 4, 2014   ʱ�䣺9:09:07 AM
 * �������ڣ�Sep 4, 2014   ʱ�䣺9:09:07 AM
 * ������ ���ݿ������
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
	 *  ���ߣ������ 
	 *  �������ڣ�Sep 4, 2014 9:20:10 AM
	 *  ������Ϣ��@param sql
	 *  ������Ϣ��@param objects
	 *  ������Ϣ��@return
	 *  ����ֵ��ResultSet
	 *  ��������ѯ����
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
