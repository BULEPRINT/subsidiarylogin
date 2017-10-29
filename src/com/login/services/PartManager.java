package com.login.services;

import com.login.Common.UserCommon;
import com.login.util.DBUtil;
import com.login.util.VTools;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * describtion:错误零件信息
 * author: zgj
 * date: 2017/8/28
 */
public class PartManager extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String partname = request.getParameter("partname");
        String dislocationCount = request.getParameter("dislocationCount");
        String studno = request.getParameter("studentno");
        String examTime = request.getParameter("examTime");
        String examTaskTime = request.getParameter("examTaskTime");
        JSONObject jsonObj = new JSONObject();
        String msg = "";
        String flag = "false";
        UserCommon userCommon = new UserCommon();
        if(userCommon.getUserByName(studno).size()>0){

            DBUtil util = new DBUtil();
            try {
                util.save("insert into partinfo(partname,dislocationCount,inserttime,studentno,examTime,examTaskTime,examStates) values(?,?,?,?,?,?,?) ",partname,dislocationCount,VTools.getSysDate("yyyy-mm-dd hh:mm:ss"),studno,examTime,examTaskTime,1);
                msg = "保存成功";
                flag = "true";
            }catch (RuntimeException e){
                e.printStackTrace();
                msg = "保存失败";
            }

        }else{
            msg = "系统不存在该学号用户！";
        }
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
