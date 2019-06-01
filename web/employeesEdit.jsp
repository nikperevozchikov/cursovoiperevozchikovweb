
<%@ page import="java.util.List" %>

<%@ page import="entites.Employee" %>
<%@ page import="dao.implement.OrganizationDAO" %>
<%@ page import="dao.implement.PositionDAO" %>
<%@ page import="entites.Organization" %>
<%@ page import="entites.Position" %><%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 24.03.2019
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
    <link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<main class="table">
<button type="button" onclick="location.href='employeesIns.jsp'" >+</button>

<table>
<tr>
    <th>Тип </th>
    <th>Цв</th>
    <th>Выс</th>
    <th>Да</th>


    <th> </th>
    <th> </th>
</tr>

<%
    List<Employee> employees = (List<Employee>) request.getAttribute("employees");
    OrganizationDAO organizationDAO= (OrganizationDAO) request.getAttribute("organizationId");
    PositionDAO positionDAO= (PositionDAO) request.getAttribute("positionId");
    if (employees != null) {
        for (Employee employee : employees) {
            out.print("<tr>");

            out.print("<td style=\"text-align: center\">");
            out.print(employee.getFio());
            out.print("</td>");

            out.print("<td style=\"text-align: center\">");
            out.print(employee.getSeriaNumber());
            out.print("</td>");

            out.print("<td style=\"text-align: center\">");
            Organization organization = organizationDAO.getOrganizationById(employee.getOrganizationId());
            out.print(organization.getNameOrganization());
            out.print("</td>");

            out.print("<td style=\"text-align: center\">");
            Position position = positionDAO.getPositionById(employee.getPositionId());
            out.print(position.getNamePosition());
            out.print("</td>");


            out.print("<td >");
            out.print("<button type=\"button\" onclick=\"location.href='/EditEmployeeServlet?employeeId=" + employee.getEmployeeId() + "'\" > * </button>");
            out.print("</td>");


            out.print("</tr>");
        }
    }
%>
</table>
</main>
</body>
</html>
