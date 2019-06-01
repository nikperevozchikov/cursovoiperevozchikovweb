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
    <title>Добавить должность</title>

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
                    <h3>Добавить должность</h3>
                </div>
                <form action="/PositionServlet" method="POST" class="form-horizontal">
                    <input type="hidden" name="action" value="INS"/>


                    <div class="form-group form-group-sm">
                        <label for="namePosition" class="control-label col-md-4">Название Должности</label>
                        <div class="col-md-8">
                            <%--   <c:forEach  items="${employees}" var="n">
                                    <option value="${n.fio}">${n.fio}</option>
                                </c:forEach>
                            </select>--%>
                            <input type="text" name="namePosition" class="form-control" pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}" id="namePosition"
                                   title="Разрешены только буквы"
                                   value="${position.namePosition}"
                                   placeholder="Название Должности"
                                   required="" maxlength="30">
                            <%--  <c:forEach  items="${organizations}" var="n">
                                  <option value="${n.nameOrganization}">${n.nameOrganization}</option>
                              </c:forEach>
                            </select>--%>
                        </div>
                    </div>

                    <c:set var="val" value=""/>
                    <c:if test="${position.salary!=-1}">
                        ${val}=${position.salary}
                    </c:if>

                    <div class="form-group form-group-sm">
                        <label for="salary" class="control-label col-md-4">Зарплата</label>
                        <div class="col-md-8">
                            <input type="number" id="salary" required
                                   title="Разрешены только цифры" class="form-control" name="salary"
                                   value="${val}"
                                   placeholder="Зарплата" required="" maxlength="10"/>
                        </div>
                    </div>


                    <hr/>
                    <div class="form-group">
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="submit" class="btn btn-info">Ok</button>
                        </div>
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="reset" id="btnCancel3" class="btn btn-warning">Отмена</button>
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
