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
        <title>Attendance Report</title>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap-grid.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="Css/report.css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".attendance-cell").each(function () {
                    var attendanceValue = parseInt($(this).text());
                    if (attendanceValue >= 90) {
                        $(this).css("background-color", "#00CC33");
                    }
                    if (attendanceValue < 90 && attendanceValue >= 80) {
                        $(this).css("background-color", "#FFFF00");
                    }
                    if (attendanceValue < 80) {
                        $(this).css("background-color", "#FF0000");

                    }
                });
            });
        </script>
    </head>
    <body>

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
                    </tbody></table>
            </div>
        </div>
        <div class="page-container">
            <div class="infor">
                <div class="tables-container">
                    <table class="subject">
                        <tbody>

                            <tr>
                                <c:forEach items="${sessionScope.subjects}" var="subject">
                                    <td>
                                        <a href="group?id=${subject.subject_id}" class="nav-link">
                                            ${subject.subject_id}-${subject.subject_name}
                                        </a>
                                    </td>
                                </c:forEach>
                            </tr>

                        </tbody>
                    </table>
                    <table class="group">
                        <tbody>
                            <tr>
                                <c:forEach items="${sessionScope.groups}" var="g">
                                    <c:forEach items="${sessionScope.subjects}" var="subject">
                                        <c:if test="${g.subject_id eq subject.subject_id}">

                                            <td>
                                                <a href="check?gid=${g.group_id}&sid=${subject.subject_id}" class="group-link">
                                                    ${g.group_id}
                                                </a>
                                            </td>

                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <c:if test="${ not empty requestScope.students}">
                <table class="tt">
                    <tbody>

                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                                <c:forEach items="${requestScope.sessions}" var="ss">
                                <th>${ss.session_name}</th>
                                </c:forEach>
                            <th>Total Absents</th>
                            <th>Status</th>
                        </tr>
                        <c:forEach items="${requestScope.students}" var="st">
                            <tr>
                                <td>${st.student_id}</td>
                                <td>${st.student_name}</td>
                                <c:forEach items="${requestScope.sessions}" var="ss">
                                    <c:set var="statusFound" value="false" />
                                    <td>
                                        <c:forEach items="${requestScope.statuses}" var="s">
                                            <c:if test="${ss.session_id eq s.session.session_id and s.student.student_id eq st.student_id}">
                                                <c:if test="${s.status}">
                                                    <p style="color: green">P</p>
                                                </c:if>
                                                <c:if test="${!s.status}">
                                                    <p style="color: red">A</p>
                                                </c:if>
                                                <c:set var="statusFound" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not statusFound}">
                                            -
                                        </c:if>
                                    </td>
                                </c:forEach>
                                <c:set var="absentFound" value="false" />
                                <td>
                                    <c:forEach var="entry" items="${requestScope.studentAbsencesMap}">
                                        <c:if test="${entry.key eq st.student_id}">
                                            <span>${entry.value}</span>
                                            <c:set var="absentFound" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${not absentFound}">
                                        0
                                    </c:if>
                                </td>
                                <td >
                                    <c:forEach var="entry" items="${requestScope.studentAttendancePercentageMap}">
                                        <c:if test="${entry.key eq st.student_id}">
                                            <span class="btn attendance-cell">${entry.value}%</span>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
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
