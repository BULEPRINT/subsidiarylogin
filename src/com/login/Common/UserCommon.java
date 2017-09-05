package com.login.Common;

import com.login.entity.SysUser;
import com.login.util.DBUtil;
import com.login.util.VTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张高俊 on 2017/9/3.
 */
public class UserCommon {
    public void updateUserEnable(String stduno,String enable){
        DBUtil util = new DBUtil();
        StringBuffer sqlbuffer = new StringBuffer("update sys_user set enable='").append(enable).append("'");
        try {
            if(!VTools.StringIsNullOrSpace(stduno)){
                sqlbuffer.append(" where username=? ");
                util.save(sqlbuffer.toString(),stduno);
            }else{
                util.save(sqlbuffer.toString());
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            util.close();
        }
    }
    public List<SysUser> getUserByName(String username) {

        DBUtil util = new DBUtil();
        List<SysUser> listU = new ArrayList<SysUser>();
        SysUser user = null;
        ResultSet rs = util.query("select * from sys_user where username=? ",username);
        try {
            while (rs.next()){

                user = new SysUser();
                user.setUsername(rs.getString("username")+"");
                user.setPassword(rs.getString("password")+"");
                user.setCreatetime(rs.getString("createtime"));
                user.setEnable(rs.getString("enable")+"");
                listU.add(user);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            util.close();
        }
        return listU;
    }
    public String getExamStateByStudentNo(String studentno){
        DBUtil util = new DBUtil();
        String examStates = "0";
        ResultSet rs = util.query("select examStates from partinfo where studentno=? and examStates='1' ",studentno);
        try {
            if (rs.next()){
                examStates = "1";
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return examStates;
    }

    /**
     * 更新错误信息状态根据学号
     * @param stduno
     * @param enable
     */
    public void updateUexamStatesByStudentNo(String stduno,String enable){
        DBUtil util = new DBUtil();
        StringBuffer sqlbuffer = new StringBuffer("update partinfo set examStates='").append(enable).append("'");
        try {
            if(!VTools.StringIsNullOrSpace(stduno)){
                sqlbuffer.append(" where studentno=? ");
                util.save(sqlbuffer.toString(),stduno);
            }else{
                util.save(sqlbuffer.toString());
            }
        }catch (RuntimeException e){
            e.printStackTrace();
        }finally {
            util.close();
        }
    }
}
