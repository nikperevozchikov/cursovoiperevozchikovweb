<%@ page import="entites.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.implement.OrganizationDAO" %>
<%@ page import="dao.implement.PositionDAO" %>
<%@ page import="entites.Organization" %>
<%@ page import="entites.Position" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Employee</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../datatable/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="../datatable/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div id="container" style="margin:auto; width: 80%">
    <h1 class="title">Список сотрудников</h1>
    <th style="text-align: center"><u><a class="btn btn-warning" href="index.jsp" role="button">Назад</a></u></th>
    <table id="example" class="display table-responsive" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th hidden style="text-align: center"><u>ID сотрудника</u></th>
            <th style="text-align: center"><u>ФИО</u></th>
            <th style="text-align: center"><u>Серия Номер паспорта</u></th>
            <th style="text-align: center"><u>Название Организации</u></th>
            <th style="text-align: center"><u>Название Должности</u></th>
            <th style="text-align: center"><u><a class="btn btn-warning" href="/EmployeeServlet?action=INS"
                                                 role="button">Добавить</a></u>
            </th>


            <th></th>
        </tr>
        </thead>
        <tbody>
        <%-- <%
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
                       Organization organization= organizationDAO.getOrganizationById(employee.getOrganizationId());
                       out.print(organization.getNameOrganization());
                       out.print("</td>");

                       out.print("<td style=\"text-align: center\">");
                       Position position = positionDAO.getPositionById(employee.getPositionId());
                       out.print(position.getNamePosition());
                       out.print("</td>");
                       out.print("<td style=\"text-align: center\">");
                      out.print(" <a class=\"btn btn-warning\" href=\"EmployeeServlet?action=DEL&employeeId=${employee.getEmployeeId}\" role=\"button\">");
                               out.print("<span class=\"glyphicon glyphicon-trash\"></span></a>");
                                       out.print("</td>");
                               out.print("</tr>");
                   }}
           %>--%>
        <c:forEach items="${employees}" var="n">
            <tr>
                <td hidden style="text-align: center">${n.employeeId}</td>
                <td style="text-align: center">${n.fio}</td>
                <td style="text-align: center">${n.seriaNumber}</td>

                              <td style="text-align: center">
                    <c:forEach items="${organizations}" var="o">
                        <c:if test="${n.organizationId==o.organizationId}">
                            <c:out value="${o.nameOrganization}"/>
                        </c:if>
                    </c:forEach></td>
                <td style="text-align: center">
                    <c:forEach items="${positions}" var="p">
                        <c:if test="${n.positionId==p.positionId}">
                            <c:out value="${p.namePosition}"/>
                        </c:if>
                    </c:forEach></td>

                <td style="text-align: center"><a class="btn btn-warning"
                                                  href="EmployeeServlet?action=DEL&employeeId=${n.employeeId}"
                                                  role="button">
                    <span class="glyphicon glyphicon-trash"></span></a></td>
                <td style="text-align: center"><a class="btn btn-warning"
                                                  href="EmployeeServlet?action=FND&employeeId=${n.employeeId}"
                                                  role="button">
                    <span class="glyphicon glyphicon-repeat"></span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<br/>

<div>${message}</div>


<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../datatable/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="../datatable/dataTables.jqueryui.min.js" type="text/javascript"></script>


<script type="text/javascript">
    $(document).ready(function () {
        $('#example').dataTable({
            "lengthMenu": [10, 15, 25, 50],
            "language": {
                "lengthMenu": 'Количество на странице <select>' +
                    '<option value="10">10</option>' +
                    '<option value="15">15</option>' +
                    '<option value="25">25</option>' +
                    '<option value="50">50</option>' +
                    '</select> ',
                "search": "Поиск:",
                "info": "Страницы",
                "pages": {
                    "previous": "Предыдущая",
                    "next": "Следующая"
                }
            }
        });
    });
</script>
<script>${modal}</script>
</body>
</html>
