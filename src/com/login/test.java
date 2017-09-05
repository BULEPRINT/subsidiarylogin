package com.login;

import com.login.util.DBUtil;
import com.login.util.VTools;

import java.sql.SQLException;

/**
 * Created by ALIENWARE on 2017/9/1.
 */
public class test {
    public static void main(String args[]){
        System.out.print("ddd");
        DBUtil util = new DBUtil();
        java.sql.ResultSet rs = util.query("select * from sys_user");
        try {
            while (rs.next()){

                System.out.println(VTools.getSysDate("yyyy-mm-dd hh:mm:ss"));
                System.out.println(rs.getString("username"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
