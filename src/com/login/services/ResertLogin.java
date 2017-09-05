package com.login.services;

import com.login.Common.UserCommon;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by 张高俊 on 2017/9/3.
 */
public class ResertLogin extends HttpServlet{
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
        String studNo = request.getParameter("studentno");
        UserCommon userCommon = new UserCommon();
        try {
            userCommon.updateUexamStatesByStudentNo(studNo,"0");
            jsonObj.put("flag",true);
            jsonObj.put("msg","修改成功");
        }catch (RuntimeException e){
            jsonObj.put("msg","修改失败");
            e.printStackTrace();
        }

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
