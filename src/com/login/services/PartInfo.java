package com.login.services;

import com.login.entity.PartsDislocation;
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
 * describtion:.
 * author: zgj
 * date: 2017/8/28
 */
public class PartInfo extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String partName = request.getParameter("partname");
        JSONObject jsonObj = new JSONObject();
        PrintWriter out = response.getWriter();
        String msg = "";
        String flag = "false";
        String sql = "SELECT * from partinfo ";
        ResultSet rs = null;
        List<PartsDislocation> list = new ArrayList<PartsDislocation>();
        PartsDislocation partinfo = null;
        DBUtil util = new DBUtil();
        try {
            if(!VTools.StringIsNullOrSpace(partName)){
                sql +=" where partname like concat('%',?,'%') ";
                sql += " order by inserttime desc";
                rs = util.query(sql,partName);
            }else{
                sql += " order by inserttime desc";
                rs = util.query(sql);
            }
            while (rs.next()){
                partinfo = new PartsDislocation();
                partinfo.setPartname(rs.getString("partname")+"");
                partinfo.setDislocationCount(rs.getInt("dislocationCount"));
                partinfo.setInserttime(rs.getString("inserttime")+"");
                list.add(partinfo);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            util.close();
        }
        jsonObj.put("partList",list);
        out.write(jsonObj.toString());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
