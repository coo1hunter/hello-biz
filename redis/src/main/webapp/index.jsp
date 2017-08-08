<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <script type="text/javascript">
        function page_method() {
            var page = document.getElementById("pageText").value;
            if(page > ${page.totalPage}|| page < 0 ){
                document.getElementById("message").innerHTML = "错误页数，请重试"
            }else {
                window.location.href="${pageContext.request.contextPath }/studentmanager?currentPage="+page;
            }
        }
        function del(id){
            if (window.confirm("确认删除此条信息？")){
                window.location.href="${pageContext.request.contextPath }/delete?param="+id+"&currentPage=${page.currentPage}";
            }
        }
    </script>
</head>
<body>
<center><h2>学生管理系统</h2></center>
<center>
    <a href="add.jsp">新增学生</a>
<table border="1" style="width: 60%">
    <tr>
        <td>学号</td>
        <td>姓名</td>
        <td style="text-align: center">出生年月</td>
        <td>备注</td>
        <td>平均分</td>
    </tr>
    <c:forEach items="${list}" var="student">
        <tr>
            <td><c:out value="${student.id}"/></td>
            <td><c:out value="${student.name}"/></td>
            <td><c:out value="${student.birthday}"></c:out></td>
            <td><c:out value="${student.description}"/></td>
            <td><c:out value="${student.avgscore}"/></td>
            <td><a href="${request.contextPath}alter?param=${student.id}&currentPage=${page.currentPage}">修改</a></td>
            <td><a href="#" onclick="del(${student.id})">删除</a></td>
        </tr>
    </c:forEach>
</table>
    当前${page.currentPage }/${page.totalPage }页     &nbsp;&nbsp;

    <a href="${pageContext.request.contextPath }/studentmanager?currentPage=1">首页</a>
    <a href="${pageContext.request.contextPath }/studentmanager?currentPage=${page.currentPage-1}">上一页 </a>
    <a href="${pageContext.request.contextPath }/studentmanager?currentPage=${page.currentPage+1}">下一页 </a>
    <a href="${pageContext.request.contextPath }/studentmanager?currentPage=${page.totalPage}">末页</a>
    <input type="text" id="pageText" size="5"><a a href="#" onclick="page_method()">跳转</a>
    <a id="message"></a>
</center>
</body>
</html>
