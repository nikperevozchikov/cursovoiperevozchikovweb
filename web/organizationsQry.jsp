<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Организация</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../datatable/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="../datatable/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div id="container" style="margin:auto; width: 80%">
    <h1 class="title">Список организаций</h1>
    <th style="text-align: center"><u><a class="btn btn-warning" href="index.jsp" role="button">Назад</a></u></th>
    <table id="example" class="display table-responsive" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th hidden style="text-align: center"><u>ID организации</u></th>
            <th style="text-align: center"><u>Название организации</u></th>
            <th style="text-align: center"><u>ОГРН</u></th>
            <th style="text-align: center"><u>Дата основания</u></th>
            <th style="text-align: center"><u>Название мероприятия</u></th>
            <th style="text-align: center"><u>Название режима надзора</u></th>
            <th style="text-align: center"><u><a class="btn btn-warning" href="/OrganizationServlet?action=INS" role="button">Добавить</a></u>
            </th>



            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${organizations}" var="n">
            <tr>
                <td hidden style="text-align: center">${n.organizationId}</td>
                <td style="text-align: center">${n.nameOrganization}</td>
                <td style="text-align: center">${n.ogrn}</td>
                <td style="text-align: center">${n.dateFoundation}</td>
                <td style="text-align: center">
                <c:forEach items="${events}" var="e">
                    <c:if test="${n.eventId==e.eventId}">
                        <c:out value="${e.nameEvent}"/>
                    </c:if>
                </c:forEach></td>
                <td style="text-align: center">
                    <c:forEach items="${supervisions}" var="s">
                        <c:if test="${n.modeId==s.modeId}">
                            <c:out value="${s.nameMode}"/>
                        </c:if>
                    </c:forEach></td>
                <td style="text-align: center"><a class="btn btn-warning"
                                                  href="OrganizationServlet?action=DEL&organizationId=${n.organizationId}"
                                                  role="button">
                    <span class="glyphicon glyphicon-trash"></span></a></td>
                <td style="text-align: center"><a class="btn btn-warning"
                                                  href="OrganizationServlet?action=FND&organizationId=${n.organizationId}"
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
                "paginate": {
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
