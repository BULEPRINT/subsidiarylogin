<%--
  Created by IntelliJ IDEA.
  User: zgj
  Date: 2017/8/25
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页···</title>
  </head>
  <body>
  欢迎登陆
  <form action="/login" method="post">
  <table>
    <tr><td>用户名</td><td><input type="text" id="username" name="username"/></td></tr>
    <tr><td>密码</td><td><input type="password" id="password" name="password"/></td></tr>
    <tr><td><input type="submit" value="提交"></td></tr>
  </table>
  </form>
  </body>
</html>
