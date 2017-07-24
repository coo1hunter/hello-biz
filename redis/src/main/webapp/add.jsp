<%--
  Created by IntelliJ IDEA.
  User: cool
  Date: 2017/7/20
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>新增</title>
</head>
<body>
<form action="<%=request.getContextPath()+ "/update"%>" method="post" >
    学号：<input type="text" name="id"><br/>
    姓名：<input type="text"  name="name"><br/>
    生日：<input type="text"  name="birthday"><br/>
    备注：<input type="text"  name="description"><br/>
    平均分：<input type="text"  name="avgscore"><br/>
    <input type="hidden" value="add" name="prejsp">
    <input type="submit" value="确定">
    <a href="<%=request.getContextPath()+ "/studentmanager"%>">返回</a>
</form>
<a id="message">${message}</a>
</body>
</html>
