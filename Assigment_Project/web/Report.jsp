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
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
        // Parse the JSON data from JSP
        var groupData = <c:out value="${jsonData}" />;

        // Function to display the group information
        function displayGroupInfo() {
            var table = "<table border='1'><tr><th>Group ID</th><th>Group Name</th></tr>";

            // Loop through the groupData array and populate the table
            for (var i = 0; i < groupData.length; i++) {
                var group = groupData[i];
                table += "<tr><td>" + group.group_id + "</td><td>" + group.group_name + "</td></tr>";
            }

            table += "</table>";
            document.getElementById("groupInfo").innerHTML = table;
        }
    </script>
    </head>
    <body>
        <table >
            <c:forEach items="${requestScope.subjects}" var="s">
                <tr>

                    <td><a href="javascript:void(0)" onclick="loadGroup(${s.subject_id})">${s.subject_id}-${s.subject_name}</a></td>

                </tr>
            </c:forEach>

        </table>
<div id="groupInfo_${s.subject_id}"></div>
    </body>
</html>
