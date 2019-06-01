<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Задачи</title>

</head>
<body>
<h3>tablitca</h3>
<br>
<table border="3">

    <c:forEach items="${col}" var="col">

        <tr>
            <td> ${col.employeeId} </td>
            <td> ${col.fio} </td>
            <td> ${col.seriaNumber} </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
