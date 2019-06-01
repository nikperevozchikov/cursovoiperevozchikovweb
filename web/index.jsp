<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: nikit
  Date: 26.02.2019
  Time: 11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Главная страница</title>

    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style1.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div id="startMain">
    <h1 class="text-center">Система надзора, выберите пожалуйста таблицу</h1>
    <div style="text-align: center" class="start_row row">
        <%--  <button id="employee" class="button" onclick="redirect('/employeesQry.jsp')">З</button>--%>
        <br> <li> <a href="<c:url value="/OrganizationServlet"></c:url>">Организации</a></li></br>
            <br> <li> <a href="<c:url value="/EmployeeServlet"></c:url>">Сотрудники</a></li></br>
            <br><li><a href="<c:url value="/PositionServlet"></c:url>">Должности</a></li></br>
            <br><li> <a href="<c:url value="/EventServlet"></c:url>">Мероприятия</a></li></br>
            <br><li> <a href="<c:url value="/SupervisionServlet"></c:url>">Режимы надзора</a></li></br>
          <%--  <br><li> <a href="/employeesEdit?employeeId=1" class="href">Сотруднb</a></li></br>
            <br><li> <button id="employes" class="button" onclick="redirect('/employes.jsp')">Тест</button></li></br>--%>

    </div>
</div>

</div>
</body>
</html>
