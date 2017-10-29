package com.login.services;

import com.login.Common.UserCommon;
import com.login.entity.PartsDislocation;
import com.login.util.DBUtil;
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
 * describtion:.
 * author: zgj
 * date: 2017/8/28
 */
public class PartInfo extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String studno = request.getParameter("studentno");
        JSONObject jsonObj = new JSONObject();
        String msg = "";
        String flag = "false";
        String sql = "SELECT t.*,t1.studname FROM partinfo t , sys_user t1 where t.studentno=t1.username  and t.examStates='1'";
        ResultSet rs = null;
        List<PartsDislocation> list = new ArrayList<PartsDislocation>();
        PartsDislocation partinfo = null;
        DBUtil util = new DBUtil();
        try {
            UserCommon userCommon = new UserCommon();
            if(userCommon.getUserByName(studno).size()>0){
                sql +=" and t.studentno=? ";
                sql += " order by inserttime desc";
                rs = util.query(sql,studno);
                while (rs.next()){
                    partinfo = new PartsDislocation();
                    partinfo.setPartname(rs.getString("partname")+"");
                    partinfo.setDislocationCount(rs.getInt("dislocationCount"));
                    partinfo.setExamTime(rs.getString("examTime")+"");

                    partinfo.setExamTaskTime(rs.getString("examTaskTime")+"");
                    partinfo.setStduname(rs.getString("studname"));
                    partinfo.setStudentno(rs.getString("studentno"));
                    list.add(partinfo);
                }
                jsonObj.put("partList",list);
            }else{
               msg = "系统不存在该学号用户！";
                jsonObj.put("msg",msg);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            util.close();
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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
