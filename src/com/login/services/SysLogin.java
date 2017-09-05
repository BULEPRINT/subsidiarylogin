package com.login.services;

import com.login.Common.UserCommon;
import com.login.entity.SysUser;
import com.login.util.VTools;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * describtion:用户登陆servlet
 * author: zgj
 * date: 2017/8/28
 */
public class SysLogin extends HttpServlet{

    private static final long serialVersionUID = 1L;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.doPost(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String msg = "";
        String flag = "false";
        JSONObject jsonObj = new JSONObject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String studNo = request.getParameter("studno");
        UserCommon userCommon = new UserCommon();
        if(!VTools.StringIsNullOrSpace(username)){

            if(!VTools.StringIsNullOrSpace(password)){


                List<SysUser> listUser = userCommon.getUserByName(username);
                if(listUser.size()>0){
                    if(password.equals(listUser.get(0).getPassword())){
                            flag = "true";
                            msg = "登陆成功";
                            jsonObj.put("examStates",userCommon.getExamStateByStudentNo(username));
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
        /*if("true".equals(flag)&!VTools.StringIsNullOrSpace(username)){
            //更新登录状态
            userCommon.updateUserEnable(username,"1");
        }*/
        jsonObj.put("flag",flag);
        jsonObj.put("msg",msg);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin","*");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(jsonObj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }


}
