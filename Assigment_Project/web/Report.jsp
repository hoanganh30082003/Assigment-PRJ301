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
        <script>
            function loadGroup(subjectId) {
                $.ajax({
                    url: "/report?sid=" + subjectId, // Đường dẫn đến Servlet xử lý yêu cầu
                    method: "GET",
                    success: function (data) {
                        // Tìm phần tử cần hiển thị thông tin group
                        var groupInfoElement = document.getElementById("groupInfo_" + subjectId);
                        if (groupInfoElement) {
                            groupInfoElement.innerHTML = data;
                        }
                    },
                    error: function () {
                        alert("Không thể tải thông tin group.");
                    }
                });
            }
        </script>
    </head>
    <body>
        <table >
            <c:forEach items="${requestScope.subjects}" var="s">
                <tr>

                    <td><a href="javascript:void(0);" onclick="loadGroup(${s.subject_id});">${s.subject_id}-${s.subject_name}</a></td>

                </tr>
            </c:forEach>

        </table>

    </body>
</html>
