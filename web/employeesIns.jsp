<%@ page import="entites.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.implement.OrganizationDAO" %>
<%@ page import="dao.implement.PositionDAO" %>
<%@ page import="entites.Organization" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Добавить сотрудника</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css"/>
    <link href="../css/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<br/>
<br/>
<br/>

<div class="container">

    <div class="col-md-3"></div>
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="page-header" style="text-align: center; margin-top: 5px">
                    <h3>Добавить сотрудника</h3>
                </div>
                <form action="/EmployeeServlet" method="POST" class="form-horizontal">
                    <input type="hidden" name="action" value="INS"/>

                    <%--      <div class="form-group form-group-sm">
                            <label for="employeeId" class="control-label col-md-4">ID Сотрудника</label>
                            <div class="col-md-8">

                             <input type="number" id="employeeId" required title="Разрешены только цифры"
                                       class="form-control" name="ID сотрудника" value="${employee.employeeId}"
                                       placeholder="ID Сотрудника" required="" maxlength="30"/>
                            </div>
                        </div>--%>

                    <div class="form-group form-group-sm">
                        <label for="fio" class="control-label col-md-4">ФИО</label>
                        <div class="col-md-8">
                            <input type="text" id="fio" pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}" required
                                   title="Разрешены только буквы" class="form-control" name="fio"
                                   value="${employee.fio}"
                                   placeholder="ФИО" required="" maxlength="10"/>
                        </div>
                    </div>


                  <c:set var="val" value=""/>
                    <c:if test="${employee.seriaNumber!=-1}">
                        ${val}=${employee.seriaNumber}
                    </c:if>

                    <div class="form-group form-group-sm">
                        <label for="seriaNumber" class="control-label col-md-4">Серия Номер</label>
                        <div class="col-md-8">
                            <input type="text" id="seriaNumber" pattern="[0-9]{5,12}" required
                                   title="Разрешены только цифры" class="form-control" name="seriaNumber"
                                   <%--value="${employee.seriaNumber}"--%> value="${val}"
                                   placeholder="Серия Номер" required="" maxlength="10"/>
                        </div>
                    </div>

                    <div class="form-group form-group-sm">
                        <label for="organizationId" class="control-label col-md-4">Название Организации</label>
                        <div class="col-md-8">
                            <select name="organizationId" input type="text" class="form-control" id="organizationId"
                                    required="" maxlength="30">
                                <c:forEach var="i" items="${organizations}">
                                    <option value="${i.organizationId}">${i.nameOrganization}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label for="positionId" class="control-label col-md-4">Название Должности</label>
                        <div class="col-md-8">
                            <select name="positionId" input type="text" class="form-control" id="positionId"
                                    required="" maxlength="30">
                                <c:forEach var="i" items="${positions}">
                                    <option value="${i.positionId}">${i.namePosition}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <hr/>
                    <div class="form-group">
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="submit" class="btn btn-info">OK</button>
                        </div>
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="button" id="btnCancel" class="btn btn-warning">Отмена</button>
                        </div>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <br/>

    <div>${message}</div>

</div>

<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/bootstrap.min.js" type="text/javascript"></script>
<script src="../js/cancel.js" type="text/javascript"></script>

</body>
</html>