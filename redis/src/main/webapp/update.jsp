<%--
  Created by IntelliJ IDEA.
  User: cool
  Date: 2017/7/19
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<html>
<head>
    <title>修改</title>
</head>
<body>
    <form action="update" method="post"  >
        学号：${student.id}<input type="hidden" value="${student.id}" name="id"><br/>
        姓名：<input type="text" value="${student.name}" name="name"><br/>
        生日：<input type="text" value="${student.birthday}" name="birthday"><br/>
        备注：<input type="text" value="${student.description}" name="description"><br/>
        平均分：<input type="text" value="${student.avgscore}" name="avgscore"><br/>
        <input type="hidden" value="${currentPage}" name="currentPage">
        <input type="hidden" value="update" name="update">
        <input type="submit" value="确定">
        <a href="studentmanager">返回</a>
    </form>
<a id="message">${message}</a>
</body>
</html>
