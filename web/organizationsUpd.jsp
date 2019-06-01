<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Редактировать организацию</title>

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
                        <div class="page-header"  style="text-align: center; margin-top: 5px">
                            <h3>Редактировать организацию</h3>
                        </div>
                        <form action="/EditOrganizationServlet" method="POST" class="form-horizontal">
                            <input type="hidden" name="action" value="UPD"/>

                            <div class="form-group form-group-sm">
                                <label for="nameOrganization" class="control-label col-md-4">Название организации</label>
                                <div class="col-md-8">
                                    <%--   <c:forEach  items="${employees}" var="n">
                                            <option value="${n.fio}">${n.fio}</option>
                                        </c:forEach>
                                    </select>--%>
                                    <input type="text" name="nameOrganization"  class="form-control" id="nameOrganization" pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}"
                                           value="${organization.nameOrganization}" placeholder="Название Организации"
                                         required="" maxlength="30">
                                       <%-- <c:forEach  items="${organizations}" var="n">
                                            <option value="${n.nameOrganization}">${n.nameOrganization}</option>
                                        </c:forEach>
                                    </select> --%>

                                </div>
                            </div>

                            <div class="form-group form-group-sm">
                                <label for="ogrn" class="control-label col-md-4">ОГРН</label>
                                <div class="col-md-8">
                                    <input type="number" id="ogrn" pattern="[0-9]{5,12}" required
                                           title="Разрешены только цифры" class="form-control" name="ogrn"
                                           value="${organization.ogrn}"
                                           placeholder="ОГРН" required="" maxlength="12"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="dateFoundation" class="control-label col-md-4">Дата Основания</label>
                                <div class="col-md-8">
                                    <input type="date" id="dateFoundation" pattern="[0-9]{5,12}" required
                                           title="Разрешены только цифры" class="form-control" name="dateFoundation"
                                           value="${organization.dateFoundation}"
                                           placeholder="ОГРН" required="" maxlength="10"/>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="eventId" class="control-label col-md-4">Название мероприятия</label>
                                <div class="col-md-8">

                                    <select name="eventId" input type="text" class="form-control" id="eventId"
                                            required="" maxlength="30">
                                        <c:forEach items="${events}" var="i">
                                            <option value="${i.eventId}" ${organization.eventId == i.eventId ? 'selected' : ''}>
                                                    ${i.nameEvent}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group form-group-sm">
                                <label for="modeId" class="control-label col-md-4">Название режима надзора</label>
                                <div class="col-md-8">
                                    <select name="modeId" input type="text" class="form-control" id="modeId"
                                            required="" maxlength="30">
                                        <c:forEach items="${supervisions}" var="i">
                                            <option value="${i.modeId}" ${organization.modeId == i.modeId ? 'selected' : ''}>
                                                    ${i.nameMode}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <hr/>
                            <div class="form-group">
                                <div class="col-md-4 col-sm-offset-2">
                                    <button type="submit" class="btn btn-info">Ok</button>
                                </div>
                                <div class="col-md-4 col-sm-offset-2">
                                    <button type="reset" id="btnCancel1" class="btn btn-warning">Отмена</button>
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