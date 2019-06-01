<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Редактировать режим надзора</title>
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
                    <h3>Редактировать режим надзора</h3>
                </div>
                <form action="/EditSupervisionServlet" method="POST" class="form-horizontal">
                    <input type="hidden" name="action" value="Upd"/>

                    <%--    <div class="form-group form-group-sm">
                            <label for="modeId" class="control-label col-md-4">ID Режима надзора</label>
                            <div class="col-md-8">

                                <input type="number" id="modeId" required title="Разрешены только цифры"
                                       class="form-control" name="modeId" value="${supervision.modeId}"
                                       placeholder="ID Режима надзора" required="" maxlength="30"/>
                            </div>
                        </div>--%>

                    <div class="form-group form-group-sm">
                        <label for="nameMode" class="control-label col-md-4">Название Режима надзора</label>
                        <div class="col-md-8">
                            <%--   <c:forEach  items="${employees}" var="n">
                                    <option value="${n.fio}">${n.fio}</option>
                                </c:forEach>
                            </select>--%>
                            <input type="text" name="nameMode" class="form-control" pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}"
                                   id="nameMode"
                                   title="Разрешены только буквы" value="${supervision.nameMode}"
                                   placeholder="Название Режима надзора"
                                   required="" maxlength="30">
                            <%--  <c:forEach  items="${organizations}" var="n">
                                  <option value="${n.nameOrganization}">${n.nameOrganization}</option>
                              </c:forEach>
                            </select>--%>
                        </div>
                    </div>
                    <div class="form-group form-group-sm">
                        <label for="resultSupervision" class="control-label col-md-4">Результат надзора</label>
                        <div class="col-md-8">
                            <input type="text" pattern="[A-Za-z-а-яА-ЯёЁ\s]{1,30}"
                                   id="resultSupervision" required
                                   title="Разрешены только буквы" class="form-control" name="resultSupervision"
                                   value="${supervision.resultSupervision}"
                                   placeholder="Результат надзора" required="" maxlength="20"/>
                        </div>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="submit" class="btn btn-info">Ok</button>
                        </div>
                        <div class="col-md-4 col-sm-offset-2">
                            <button type="reset" id="btnCancel4" class="btn btn-warning">Отмена</button>
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