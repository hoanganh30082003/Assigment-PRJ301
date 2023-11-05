<%-- 
    Document   : Report
    Created on : Oct 30, 2023, 3:43:32 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>        
        <nav>
            <ul>
                <c:forEach items="${sessionScope.subjects}" var="subject">
                    <li>
                        <a href="group?id=${subject.subject_id}">
                            ${subject.subject_name}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </nav>

        <c:forEach items="${sessionScope.groups}" var="g">
            <c:forEach items="${sessionScope.subjects}" var="subject">
                <c:if test="${g.subject_id eq subject.subject_id}">
                    <a href="check?gid=${g.group_id}&sid=${subject.subject_id}">${g.group_id}</a>
                </c:if>
            </c:forEach>
        </c:forEach>
        <table border="1">
            <tbody>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>status</td>
                    <td>session</td>
                </tr>
                <c:forEach items="${requestScope.students}" var="ss">
                    <tr>
                        <td>${ss.student_id}</td>
                        <td>${ss.student_name}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
