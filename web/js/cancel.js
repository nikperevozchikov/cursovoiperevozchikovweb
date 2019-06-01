$('#btnCancel').on('click', function () {
    window.location = "EmployeeServlet?action=QRY";
  })
$('#btnCancel1').on('click', function () {
    window.location = "OrganizationServlet?action=QRY";
})
$('#btnCancel2').on('click', function () {
    window.location = "EventServlet?action=QRY";
})
$('#btnCancel3').on('click', function () {
    window.location = "PositionServlet?action=QRY";
})
$('#btnCancel4').on('click', function () {
    window.location = "SupervisionServlet?action=QRY";
});
