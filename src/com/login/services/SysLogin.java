package com.login.services;

import com.login.entity.SysUser;
import com.login.util.DBUtil;
import com.login.util.VTools;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * describtion:用户登录servlet
 * author: zgj
 * date: 2017/8/28
 */
public class SysLogin extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String msg = "";
        String flag = "false";
        JSONObject jsonObj = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(!VTools.StringIsNullOrSpace(username)){

            if(!VTools.StringIsNullOrSpace(password)){


                    List<SysUser> listUser = getUserByName(username);
                    if(listUser.size()>0){
                        if(password.equals(listUser.get(0).getPassword())){
                            flag = "true";
                            msg = "登陆成功";
                        }else{
                            msg = "密码错误！";
                        }
                    }else {
                        msg = "用户不存在！";
                    }

            }else{
                msg = "密码不能为空！";
            }
        }else{
            msg = "用户名不能为空！";
        }
        jsonObj.put("flag",flag);
        jsonObj.put("msg",msg);
        out.write(jsonObj.toString());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
    private List<SysUser> getUserByName(String username) {

        DBUtil util = new DBUtil();
        List<SysUser> listU = new ArrayList<SysUser>();
        SysUser user = null;
        ResultSet rs = util.query("select * from sys_user where username=? ",username);
        try {
            while (rs.next()){

                user = new SysUser();
                user.setUsername(rs.getString("username")+"");
                user.setPassword(rs.getString("password")+"");
                user.setCreatetime(rs.getString("create_time"));
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
}
