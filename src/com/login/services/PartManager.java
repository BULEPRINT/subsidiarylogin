package com.login.services;

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
 * describtion:零件信息管理
 * author: zgj
 * date: 2017/8/28
 */
public class PartManager extends HttpServlet{

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String partname = request.getParameter("partname");
        String dislocationCount = request.getParameter("dislocationCount");
        JSONObject jsonObj = new JSONObject();
        String msg = "";
        String flag = "false";
        if(!VTools.StringIsNullOrSpace(partname)&&!VTools.StringIsNullOrSpace(dislocationCount)){

            DBUtil util = new DBUtil();
            util.save("insert into PartInfo(partname,dislocationCount,inserttime) values(?,?,?) ",partname,dislocationCount,VTools.getSysDate("yyyy-mm-dd hh:mm:ss"));
            util.close();
            msg = "保存成功";
            flag = "true";
        }else{
            msg = "零件信息为空！";
        }
        jsonObj.put("flag",flag);
        jsonObj.put("msg",msg);
        out.write(jsonObj.toString());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doGet(request, response);
    }
}
