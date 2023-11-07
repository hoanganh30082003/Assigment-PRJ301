<%-- 
    Document   : ttOfInstructor
    Created on : Oct 24, 2023, 1:23:37 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time table of instructor</title>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap-grid.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="Css/TtofLecturer.css"/>

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
        <div>
            <div class="title">
                <div>
                    <b>View Schedule</b>
                </div>
                <div class="infor">
                    <a href="logout">logout</a>
                    <label>Campus: ${sessionScope.acc.campus.campus_name}</label>
                    <label>Lecturer:${sessionScope.acc.instructor.instructor_name}</label>
                </div>
            </div>

            <form  action="schedule"method="POST">
                <center>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    <label>From</label>
                                    <input type="date" class="btn" name="from" value="${requestScope.from}">
                                </td>
                                <td>
                                    <label>To</label>
                                    <input type="date"  class="btn"  name="to" value="${requestScope.to}">
                                </td>
                                <td><input type="submit" value="View" class="btn"></td>
                            </tr>  
                        </tbody>
                    </table>
                </center>
            </form>
            <div>
                <table class="main-table">
                    <thead>
                        <tr>
                            <td></td>
                            <c:forEach items="${requestScope.dateFormat}" var="df">
                                <td>${df}</td>
                            </c:forEach>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${slots}" var="l">
                            <tr>
                                <td>Slot ${l.slot_id}</td>
                                <c:forEach items="${dates}" var="d">
                                    <td>
                                        <c:set var="sessionFound" value="false" />
                                        <c:forEach items="${session}" var="s">
                                            <c:if test="${s.date eq d and s.slot.slot_id eq l.slot_id}">
                                                <details>
                                                    <summary>
                                                        <c:if test="${s.isAtt}">
                                                        <p style="color: green">${s.subject.subject_id}</p>
                                                    </c:if>
                                                        <c:if test="${!s.isAtt}">
                                                        <p>${s.subject.subject_id}</p>
                                                    </c:if>
                                                    </summary>
                                                    <a href="attendance?id=${s.session_id}" class="no-underline">
                                                    <br>${s.group_id}-${s.room_id}
                                                    <br>(${s.slot.start_time}-${s.slot.end_time})
                                                </a>
                                                </details>
                                                
                                                <c:set var="sessionFound" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${not sessionFound}">
                                            -
                                        </c:if>
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="report">
                <a href="report" class="btn btn-dark">See Report</a>
            </div>

            <footer>
                <p>
                    © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                    <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; 
                    <a href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; 
                    <a href="http://library.books24x7.com" target="_blank">books24x7</a>
                </p>
            </footer>            
        </div>


    </body>
</html>
