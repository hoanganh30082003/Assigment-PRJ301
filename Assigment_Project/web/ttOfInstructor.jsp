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
        <script >
            document.addEventListener("DOMContentLoaded", function () {
                // Lấy tham chiếu đến form và các trường input
                var form = document.getElementById("myForm");
                var fromDateInput = document.getElementById("fromDate");
                var toDateInput = document.getElementById("toDate");

                // Thêm sự kiện "change" cho các trường input
                fromDateInput.addEventListener("change", function () {
                    form.submit(); // Gửi form khi trường "fromDate" thay đổi
                });

                toDateInput.addEventListener("change", function () {
                    form.submit(); // Gửi form khi trường "toDate" thay đổi
                });
            });
        </script>
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
                    </tbody></table>
            </div>
        </div>
        <div class="row information">
            <div class="col-md-12">
                <form action="timetable" method="get">
                    <b>View Schedule</b>
                    <c:set scope="request" var="d" value="${data}"/>
                    <label>Campus: ${d.getCampus().getCampus_name()}</label>
                    <label>Lecturer: ${d.getInstructor().getInstructor_name()}</label>
                    <center>
                        <form id="myForm" action="schedule">
                            <label>From</label>
                            <input type="date" class="btn"id="fromDate" name="from">
                            <label>To</label>
                            <input type="date"  class="btn" id="toDate" name="to">
                            <input type="hidden" name="id" value="${d.getInstructor().getInstructor_id()}">
                            <input type="submit" value="View" class="btn">
                        </form>
                    </center>                                                               
                    <table class="tt">
                        <thead>
                            <tr>
                                

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.slot}" var="l">
                                <tr>
                                    <td>
                                        ${l.slot_id}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <p>
                        <br><b>More note / Chú thích thêm:</b>
                    </p>
                    <div>
                        <ul>
                            <li>
                                (<font color="green">attended</font>): ${d.getInstructor().getInstructor_name()} đã tham gia hoạt động này</li>
                            <li>
                                (<font color="red">absent</font>): ${d.getInstructor().getInstructor_name()} đã vắng mặt buổi này</li> 
                            <li>(-): no data was given / chưa có dữ liệu</li> 
                        </ul>
                    </div>
                    <p>
                    </p>


                    <tr>
                        <td>                                  
                    <tr>
                        <td>                     
                            <br>
                            <b>Mọi góp ý, thắc mắc xin liên hệ:</b>
                            <b>Phòng dịch vụ sinh viên</b><br>
                            <table >
                                <tbody>
                                    <tr>
                                        <th> Email:</th>
                                        <th><a href="mailto:dichvusinhvien@fe.edu.vn">dichvusinhvien@fe.edu.vn</a></th>
                                    </tr>                                           
                                    <tr>
                                        <td> Điện thoại:</td>
                                        <td><span>(024)7308.13.13 </span></td>
                                    </tr>                                                
                                </tbody>
                            </table>                                        
                            <br>

                </form>
            </div>
        </div>

        <footer>
            <p>
                © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" target="_blank">books24x7</a>

            </p>
        </footer>


    </body>
</html>
