<%@ page import="java.util.List" %>
<%@ page import="entites.Employee" %>
<%@ page import="entites.Position" %>
<%@ page import="entites.Organization" %>
<%@ page import="dao.implement.OrganizationDAO" %>
<%@ page import="dao.implement.PositionDAO" %>
<%@ page import="entites.Organization" %>
<%@ page import="entites.Position" %><%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 17.03.2019
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список сотрудников</title>
</head>
<body>

<table>
    <tr>
        <th>айди</th>
        <th>фио</th>
        <th>серия</th>
        <th>организация</th>
        <th>должность</th>
    </tr>
    <%
        List<Employee> employees = (List<Employee>) request.getAttribute("employee");
        OrganizationDAO organizationDAO= (OrganizationDAO) request.getAttribute("organizationId");
        PositionDAO positionDAO= (PositionDAO) request.getAttribute("positionId");
        if (employees != null) {
            for (Employee employee : employees) {
            out.print("<tr>");
            out.print("<td>");
            out.print(employee.getEmployeeId());
            out.print("</td>");
            out.print("<td>");
            out.print(employee.getFio());
            out.print("</td>");

            out.print("<td>");
            out.print(employee.getSeriaNumber());
            out.print("</td>");

            out.print("<td>");
            Organization organization= organizationDAO.getOrganizationById(employee.getOrganizationId());
            out.print(organization.getOrganizationId());
            out.print("</td>");

            out.print("<td>");
            Position position = positionDAO.getPositionById(employee.getPositionId());
            out.print(position.getPositionId());
            out.print("</td>");



            out.print("</tr>");
        }}
    %>

</table>

</body>
</html>