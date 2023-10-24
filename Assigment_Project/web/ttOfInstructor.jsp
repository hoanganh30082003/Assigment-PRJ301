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
                    <center>
                        <c:set scope="request" var="c" value="${campus}" />
                        <label>Campus: ${c.getCampus_name()}</label>
                        
                        <c:set scope="request" var="i" value="${instructor}" />
                        <label>Lecturer: ${i.getInstructor_name()}</label>

                    </center>                                                               
                    <table class="tt">
                        <thead>
                            <tr>
                                <th rowspan="2" style="text-align: left">
                                    <label>Start time</label>
                                    <input type="date" class="btn">
                                    <br>
                                    <label>End time</label>
                                    <input type="date"  class="btn" >
                                    
                                </th>
                                <th>Mon</th>
                                <th>Tue</th>
                                <th>Wed</th>
                                <th>Thu</th>
                                <th>Fri</th>
                                <th>Sat</th>
                                <th>Sun</th>
                            </tr>
                            <tr>
                                <th>02/10</th>
                                <th>03/10</th>
                                <th>04/10</th>
                                <th>05/10</th>
                                <th>06/10</th>
                                <th>07/10</th>
                                <th>08/10</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Slot 0 </td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Slot 1 </td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Slot 2 </td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Slot 3 </td>
                                <td>-</td>
                                <td>
                                    -
                                </td>
                                <td>-</td>
                                <td>-</td>
                                <td>-
                                </td>
                                <td>-</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Slot 4 </td>
                                <td>
                                    <p>
                                        <a style="text-align: center;" href="../Schedule/ActivityDetail.aspx?id=1417314">SE1763-PRJ301<br> at BE-202</a><a> <br>(<font color="Green">attended</font>)<br>
                                            <span class="time-slot">(15:20-17:40)</span><br>
                                        </a>
                                    </p>
                                </td>
                                <td>
                                    <p>
                                        <a href="../Schedule/ActivityDetail.aspx?id=1417894">SE1763-MAS291<br> at DE-421</a>
                                        <a> <br>(<font color="Green">attended</font>)<br><span class="time-slot">(15:20-17:40)</span><br></a>
                                    </p>
                                </td>
                                <td>-</td>
                                <td>-</td>
                                <td>
                                    -
                                </td>
                                <td>-</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Slot 5 </td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                            </tr>
                            <tr>
                                <td>Slot 6 </td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td>
                                <td>-</td></tr><tr><td>Slot 7 </td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr><tr><td>Slot 8 </td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr><tr><td>Slot 9 </td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td><td>-</td></tr>
                        </tbody>
                    </table>
                    <p>
                        <br><b>More note / Chú thích thêm:</b>
                    </p>
                    <div><ul><li>(<font color="green">attended</font>): anhnhhe176236 had attended this activity / Nguyễn Hoàng Anh đã tham gia hoạt động này</li><li>(<font color="red">absent</font>): anhnhhe176236 had NOT attended this activity / Nguyễn Hoàng Anh đã vắng mặt buổi này</li> <li>(-): no data was given / chưa có dữ liệu</li> </ul></div>
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
