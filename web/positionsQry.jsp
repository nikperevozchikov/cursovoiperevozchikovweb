<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Должность</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../datatable/jquery-ui.css" rel="stylesheet" type="text/css"/>
    <link href="../datatable/dataTables.jqueryui.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div id="container" style="margin:auto; width: 80%">
    <h1 class="title">Список должностей</h1>
    <th style="text-align: center"><u><a class="btn btn-warning" href="index.jsp" role="button">Назад</a></u></th>
    <table id="example" class="display table-responsive" width="100%" cellspacing="0">
        <thead>
        <tr>
            <th hidden style="text-align: center"><u>ID должности</u></th>
            <th style="text-align: center"><u>Название должности</u></th>
            <th style="text-align: center"><u>Зарплата</u></th>

            <th style="text-align: center"><u><a class="btn btn-warning" href="/PositionServlet?action=INS" role="button">Добавить</a></u>
            </th>



            <th></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${positions}" var="n">
            <tr>
                <td hidden style="text-align: center">${n.positionId}</td>
                <td style="text-align: center">${n.namePosition}</td>
                <td style="text-align: center">${n.salary}</td>


                <td style="text-align: center"><a class="btn btn-warning"
                                                  href="PositionServlet?action=DEL&positionId=${n.positionId}"
                                                  role="button">
                    <span class="glyphicon glyphicon-trash"></span></a></td>
                <td style="text-align: center"><a class="btn btn-warning"
                                                  href="PositionServlet?action=FND&positionId=${n.positionId}"
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
