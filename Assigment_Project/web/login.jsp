<%-- 
    Document   : login
    Created on : Oct 22, 2023, 11:13:09 PM
    Author     : FPTSHOP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FPT University Academic Portal</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap-grid.css"/>
        <link rel="stylesheet" href="Css/Login.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.css"/>
        <link rel="stylesheet" href="Bootstrap/bootstrap-5.3.2-dist/css/bootstrap.min.css"/>
    </head>
    <body style="background-color: #414045">
        <form action="login" method="post">
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
            <div class="login-main">
                <table>
                    <tr><td id="title"><b >Giảng viên</b></td></tr>
                    <tr> 
                        <td>
                            <label style="color: white">Username:</label>
                            <input type="text" name="username" required>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label style="color: white">Password:</label>
                            <input type="password"  name="password" required>
                        </td>
                    </tr>
                    <tr class="inline">
                        <td ><select class="btn" name="campus">
                                <option selected="selected">Select Campus</option>
                                <option value="1">FU-Hà Nội</option>
                                <option value="2">FU-Hồ Chí Minh</option>
                                <option value="3">FU-Đà Nẵng</option>
                                <option value="4">FU-Cần Thơ</option>
                                <option value="5">FU-Quy Nhơn</option>
                            </select></td>
                            <td><input class="btn" type="submit" value="Login"></td>
                    </tr>
                    <tr><td style="color: red">${requestScope.error}</td></tr>
                </table>
            </div>
            <footer>
                <p >
                    © Powered by <a href="http://fpt.edu.vn" target="_blank">FPT University</a>&nbsp;|&nbsp;
                    <a href="http://cms.fpt.edu.vn/" target="_blank">CMS</a>&nbsp;|&nbsp; <a href="http://library.fpt.edu.vn" target="_blank">library</a>&nbsp;|&nbsp; <a href="http://library.books24x7.com" target="_blank">books24x7</a>
                </p>
            </footer>
        </form>
    </body>
</html>
