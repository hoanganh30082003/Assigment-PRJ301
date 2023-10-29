<%-- 
    Document   : Attendance
    Created on : Oct 28, 2023, 8:31:38 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap-grid.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="Css/Attendance.css"/>
        <title>Attendance</title>
    </head>
    <body style="background-color: #414045">
        <div class="row header">
            <div class="col-md">
                <h1>FPT University Academic Portal</h1>
            </div>
            <div class="col-md-4">
                <table>
                    <tbody>
                        <tr>
                            <td colspan="2"> <h5 class="mid-title">FAP mobile app (myFAP) is ready at</h5></td>
                        </tr>
                        <tr>
                            <td><a href="https://apps.apple.com/app/id1527723314">
                                    <img src="https://fap.fpt.edu.vn/images/app-store.png" style="width: 120px; height: 40px" alt="apple store"></a></td>
                            <td><a href="https://play.google.com/store/apps/details?id=com.fuct">
                                    <img src="https://fap.fpt.edu.vn/images/play-store.png" style="width: 120px; height: 40px" alt="google store"></a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <b>Single activity attendance</b>
        <div class="col-md-12">
            <form action="attendance" method="POST">
                <table class="table">
                    <thead>
                        <tr>
                            <th>CODE</th>
                            <th>NAME</th>
                            <th>IMAGE</th>
                            <th>STATUS</th>
                            <th>COMMENT</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.status}" var="s">
                            <tr>
                        <input type="hidden" name="stuid" value="${s.student.student_id}">
                                <td>${s.student.student_id}</td>
                                <td>${s.student.student_name}</td>
                                <td><img src="src" alt="image"/></td>
                                <td>
                                    <input type="radio"  
                                           <c:if test="${!s.status}">
                                               checked="checked" 
                                           </c:if>
                                           name="status${s.student.student_id}" value="absent" /> absent
                                    <input type="radio" 
                                           <c:if test="${s.status}">
                                               checked="checked" 
                                           </c:if>
                                           name="status${s.student.student_id}" value="present" /> present
                                </td>
                                <td><input type="text" value="${s.comment}" name="comment${s.student.student_id}"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <input type="hidden" name="sesid" value="${requestScope.status[0].session.session_id}">
                <input type="submit" class="btn" value="Save"/>
                <span>${requestScope.alert}</span>
            </form>
        </div>
        <footer>
            <p>
                © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; 
                <a href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; 
                <a href="http://library.books24x7.com" target="_blank">books24x7</a>               
            </p>
        </footer>
    </body>
</html>
