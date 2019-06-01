<%@ page import="entites.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.implement.OrganizationDAO" %>
<%@ page import="dao.implement.PositionDAO" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Добавить мероприятие</title>

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
                    <h3>Добавить мероприятие</h3>
                </div>
                <form action="/EventServlet" method="POST" class="form-horizontal">
                    <input type="hidden" name="action" value="INS"/>

                    <div class="form-group form-group-sm">
                        <label for="nameEvent" class="control-label col-md-4">Название мероприятия</label>
                        <div class="col-md-8">
                            <%--   <c:forEach  items="${employees}" var="n">
                                    <option value="${n.fio}">${n.fio}</option>
                                </c:forEach>
                            </select>--%>
                            <input type="text" name="nameEvent" class="form-control" pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}"
                                   id="nameEvent"
                                   title="Разрешены только буквы" value ="${event.nameEvent}"
                                    placeholder="Название Мероприятия"
                                    required="" maxlength="30">
                                <%--  <c:forEach  items="${organizations}" var="n">
                                      <option value="${n.nameOrganization}">${n.nameOrganization}</option>
                                  </c:forEach>
                                </select>--%>
                        </div>
                    </div>

                    <c:set var="val" value=""/>
                    <c:if test="${event.dateOfEvent!='1970-01-01'}">
                        ${val}=${event.dateOfEvent}
                    </c:if>
                    <div class="form-group form-group-sm">
                        <label for="dateOfEvent" class="control-label col-md-4">Дата Мероприятия</label>
                        <div class="col-md-8">
                            <input type="date" id="dateOfEvent" required
                                   title="Разрешены только цифры" class="form-control" name="dateOfEvent"
                                   value="${val}"
                                   placeholder="Дата Мероприятия" required="" maxlength="10"/>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label for="resultOfEvent" class="control-label col-md-4">Результат Мероприятия</label>
                        <div class="col-md-8">
                            <input type="text"
                                   pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}"
                                   id="resultOfEvent"   required title="Разрешены только буквы"
                                   class="form-control" name="resultOfEvent" value="${event.resultOfEvent}"
                                   placeholder="Результат Мероприятия" required="" maxlength="30"/>
                        </div>
                    </div>

                    <hr/>
                    <div class="form-group">
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="submit" class="btn btn-info">Ok</button>
                        </div>
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="reset" id="btnCancel2" class="btn btn-warning">Отмена</button>
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